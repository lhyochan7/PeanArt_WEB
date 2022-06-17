package com.peanart.main.web;

import com.peanart.Board.service.BoardService;
import com.peanart.Board.vo.BoardVO;
import com.peanart.Board.vo.ReviewVO;
import com.peanart.ExhibitRegisteration.service.ExRegService;
import com.peanart.ExhibitRegisteration.vo.ExhibitRegisterVO;
import com.peanart.main.service.MainService;
import com.peanart.main.vo.FileVO;
import com.peanart.main.vo.UserVO;
import com.peanart.mypage.vo.MyPageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@RestController
public class MainController {
    @Autowired
    private MainService mainService;
    @Autowired
    private BoardService boardService;
    @Autowired
    private ExRegService exRegService;
    @Value("${spring.servlet.multipart.location}")
    String path;

    @ResponseBody
    @RequestMapping("/mainPoster")
    public List<BoardVO> getFiveExhib(ModelMap model){
        model.addAttribute("mainPoster", mainService.getFiveExhib());
        return mainService.getFiveExhib();
    }

    @RequestMapping(value = "/ExhibList")
    public ResponseEntity<List<BoardVO>> getExibList(Model model) {
        System.out.println("getPoster");

        List<BoardVO> exhibList = mainService.getExibList();
        System.out.println(exhibList);


        return new ResponseEntity<>(exhibList, HttpStatus.OK);
    }

    @PostMapping("/upload")
    public String upload(@RequestParam MultipartFile[] uploadfile, @RequestParam String exhibTitle, Model model) throws IllegalStateException, IOException {

        // 업로드 이미지들
        List<FileVO> files = new ArrayList<>();

        // 고유 폴더 이름 만들기 ( UUID_전시회이름 )
        String dirUuid = UUID.randomUUID().toString();
        String folderName = dirUuid + "_" + exhibTitle;
        System.out.println(folderName);

        File directory = new File(path + "/" + folderName);
        if (!directory.exists()) {
            directory.mkdir();
        }

        for (MultipartFile file : uploadfile) {
            if (!file.isEmpty()) {
                FileVO fvo = new FileVO(UUID.randomUUID().toString(), file.getOriginalFilename(), file.getContentType());
                files.add(fvo);

                File newFileName = new File(path + "/" +folderName + "/" + fvo.getFileUuid() + "_" + fvo.getFileName());

                file.transferTo(newFileName);
            }
        }


        //String path = "http://localhost:8080/imagePath/" + fvo.getUuid() + '_' + fvo.getFileName();
        model.addAttribute("files", files);
        //model.addAttribute("path", path);

        System.out.println(path);
        System.out.println("FILES FOUND");
        return "result";
    }

    @Value("${spring.servlet.multipart.location}")
    String filePath;

    @GetMapping("/download")
    public ResponseEntity<Resource> download(@ModelAttribute FileVO fvo) throws IOException {
        System.out.println(fvo.getFileName());

        Path path = Paths.get(filePath + '/' + fvo.getFileUuid()+ '_' + fvo.getFileName());

        System.out.println(path.toString());
        //Path path = Paths.get(fileDirName + fileName);
//        String contentType = Files.probeContentType(path);
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentDisposition(ContentDisposition.builder("attachment").filename(fvo.getFileName(), StandardCharsets.UTF_8).build());
//        headers.add(HttpHeaders.CONTENT_TYPE, contentType);
//
//        Resource resource = new InputStreamResource(Files.newInputStream(path));
//
//        return new ResponseEntity<>(resource, headers, HttpStatus.OK);
        return null;
    }



    @GetMapping("/detail")
    public String getExhibInfo (HttpSession session, @RequestParam("exhibSeq") Integer exhibSeq, Model model) {

        HashMap<String, Object> map = new HashMap<>();

        ExhibitRegisterVO exhibitRegisterVO = boardService.getExhibInfo(exhibSeq);
        List<FileVO> fileList = boardService.getFile(exhibSeq);
        MyPageVO myPageVO =  boardService.getUserInfo(exhibitRegisterVO.getUsrSeq());
        List<ReviewVO> reviewVO= boardService.getReview(exhibSeq);

        map.put("exhib", exhibitRegisterVO);
        map.put("fileList",fileList);
        map.put("userInfo", myPageVO);
        map.put("reviewList", reviewVO);

        String imgPath = "http://localhost:8080/imagePath/";

        model.addAttribute("imgPath",imgPath);
        model.addAttribute("map",map);

        session.setAttribute("exhibSeq", exhibSeq);
        session.setAttribute("usrSeq", exhibitRegisterVO.getUsrSeq());
        return "modExhib";
    }


    @PostMapping("/detailModifiy") //게시글 수정
    public ResponseEntity<String> modDetail(@RequestParam MultipartFile[] uploadFile, @RequestParam MultipartFile posterFile,
                                            HttpSession session, ExhibitRegisterVO exhibitRegisterVO) throws IOException {


        List<FileVO> originFile = boardService.getFile((int) session.getAttribute("exhinseq"));
        // session.usrSeq == exhib.usrSeq true 면 sql 실행 아니면 fail 반환 하자
        int sessionUsrSeq = (int) session.getAttribute("usrSeq");
        int currentExhibUsrSeq = exhibitRegisterVO.getUsrSeq();

        if (sessionUsrSeq == currentExhibUsrSeq) {
            modFiles(uploadFile,originFile,exhibitRegisterVO,posterFile);
            boardService.modExhib(exhibitRegisterVO);
        } else {
            return ResponseEntity.status(HttpStatus.OK).body("다른 유저에용");
        }
        return ResponseEntity.status(HttpStatus.OK).body("수정 끝나면 아마 boardList 페이지로 가야할듯 ?");

    }


    void modFiles(MultipartFile[] uploadFile, List<FileVO> originFileList, ExhibitRegisterVO exhibitRegisterVO, MultipartFile posterFile)throws IOException{
        try {
            exhibitRegisterVO.setFileName(exhibitRegisterVO.getFileDirName() + "_" + posterFile.getOriginalFilename());
            File poster = new File(path + "/" + exhibitRegisterVO.getFileDirName() + "/"
                    + exhibitRegisterVO.getFileDirName() + "_" + posterFile.getOriginalFilename());
            posterFile.transferTo(poster);


            List<FileVO> modFileList = new ArrayList<>();

            for (MultipartFile file : uploadFile) {
                if (!file.isEmpty()) {
                    int fileIndex = 1;
                    FileVO fvo = new FileVO(UUID.randomUUID().toString(), file.getOriginalFilename(), file.getContentType());
                    modFileList.add(fvo);

                    File newFileName = new File(path + "/" +originFileList.get(0).getFileDirName() + "/" + fvo.getFileUuid() + "_" + fvo.getFileName());

                    fvo.setFileDirName(originFileList.get(0).getFileDirName());
                    fvo.setExhibSeq(originFileList.get(0).getExhibSeq());
                    file.transferTo(newFileName);
                }
            }

            for(FileVO mod : modFileList){
                for(FileVO  origin : originFileList){
                    if(mod.getFileUuid() == origin.getFileUuid()){
                        originFileList.remove(origin);
                        modFileList.remove(mod);
                    }
                }
            } //반복이 끝나면 originFile 에는 삭제될 데이터, fileVo에는 삽입될 데이터가 남는다.

            for(FileVO removeFile : originFileList){
                boardService.deleteFiles(removeFile);
            }
            for (FileVO modFile : modFileList){
                exRegService.insertExExhibFile(modFile);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    @GetMapping("/test11")
    public String test11() {
        String uri = "http://localhost:5000/updateModel";
        RestTemplate rt = new RestTemplate();
        rt.getForObject(uri, String.class);

        return "SUCCESS";
    }


    @GetMapping("/search")
    public ResponseEntity<List<BoardVO>> searchExhib (@RequestParam (value = "kind", required = false) Integer kind,
                                                                @RequestParam (value="searchTxt", required = false) String searchTxt,
                                                                @RequestParam (value="item", required = false) Integer searchOpt)
    {

        HashMap<String, String> map = new HashMap<>();

        if(searchTxt != null) {
            System.out.println("searchTxt " + searchTxt);
            map.put("searchTxt", searchTxt);
        }
        if (kind != null) {
            System.out.println("kind " + kind);
            map.put("kind", String.valueOf(kind));
        }
        if (searchOpt!= null) {
            System.out.println("searchOpt (item) " + searchOpt);
            map.put("searchOpt", String.valueOf(searchOpt));
        }

        System.out.println(map.get("searchTxt"));
        List<BoardVO> searchList = mainService.getSearchList(map);

        System.out.println(searchList);


        return new ResponseEntity<List<BoardVO>>(searchList, HttpStatus.OK);
    }



}
