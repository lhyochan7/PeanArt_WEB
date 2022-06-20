package com.peanart.Board.web;

import com.peanart.Board.service.BoardService;
import com.peanart.Board.vo.BoardVO;
import com.peanart.Board.vo.ReviewVO;
import com.peanart.ExhibitRegisteration.service.ExRegService;
import com.peanart.ExhibitRegisteration.vo.ExhibitRegisterVO;
import com.peanart.main.vo.FileVO;
import com.peanart.mypage.vo.MyPageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@RestController
public class BoardController {
    @Value("${spring.servlet.multipart.location}")
    String path;

    @Autowired
    private BoardService boardService;
    private ExRegService exRegService;
    @GetMapping("/BoardList")
    public ResponseEntity<List<BoardVO>> getBoardList() {
        List<BoardVO> list = boardService.getBoardList();
        System.out.println(list);
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }

    @GetMapping("/detail")
    public ResponseEntity<HashMap<String, Object>> getExhibInfo (HttpSession session, @RequestParam("exhibSeq") Integer exhibSeq, Model model) {

        HashMap<String, Object> map = new HashMap<>();

        ExhibitRegisterVO exhibitRegisterVO = boardService.getExhibInfo(exhibSeq);
        List<FileVO> fileList = boardService.getFile(exhibSeq);
        MyPageVO myPageVO =  boardService.getUserInfo(exhibitRegisterVO.getUsrSeq());
        List<ReviewVO> reviewVO= boardService.getReview(exhibSeq);

        map.put("exhib", exhibitRegisterVO);
        map.put("fileList",fileList);
        map.put("userInfo", myPageVO);
        map.put("reviewList", reviewVO);

        session.setAttribute("exhibSeq", exhibSeq);
        session.setAttribute("usrSeq", exhibitRegisterVO.getUsrSeq());
        return ResponseEntity.status(HttpStatus.OK).body(map);
    }

    @PostMapping("/reviewRegister")
    public ResponseEntity<String> regReview( HttpSession session, @RequestParam String revContent){
        ReviewVO reviewVO = new ReviewVO();

        reviewVO.setUsrSeq((int)session.getAttribute("usrSeq"));
        reviewVO.setExhibSeq((int)session.getAttribute("exhibSeq"));
        reviewVO.setRevContent(revContent);
        boardService.regReview(reviewVO);

        return ResponseEntity.status(HttpStatus.OK).body("Ajax 해줘"); //페이지 전체 요청보단 ajax로 리뷰 영역만 갱신 해야함
    }

    @PostMapping("/detailModifiy") //게시글 수정
    public ResponseEntity<String> modDetail(@RequestParam MultipartFile[] uploadFile, @RequestParam MultipartFile posterFile,
                                            HttpSession session, ExhibitRegisterVO exhibitRegisterVO) throws IOException {
        for (MultipartFile file : uploadFile) {
            System.out.println(file.getOriginalFilename());
        }
        System.out.println(posterFile);
        System.out.println(exhibitRegisterVO);


        // (1) ============ 기존 이미지들 전체 삭제 ============
        int exhibSeq = (int) session.getAttribute("exhibSeq");
        List<FileVO> removeFileList= boardService.getFile(exhibSeq);
        String dirName = removeFileList.get(0).getFileDirName();
        File file1 = new File(path+"/" +dirName);
//        for(int i =0; i< removeFileList.size(); i++){
//            boardService.deleteFiles(removeFileList.get(i));
//        }

        deleteFilesRecursively(file1);


        // (2) =============== 포스터 사진, 전시회 사진들 다시 생성 ===================

        Map<String, Object> rtn = new HashMap<>();

        //고유 폴더 이름 만들기 ( UUID_전시회이름 )
        String dirUuid = UUID.randomUUID().toString();
        String folderName = dirUuid; // + "_" + exhibitRegisterVO.getExhibTitle();
        String posterFileName = "poster" + "." + posterFile.getOriginalFilename().split("\\.")[1].toLowerCase();

        exhibitRegisterVO.setFileDirName(folderName);
        exhibitRegisterVO.setFileName(dirUuid + "_" +posterFileName);

        File directory = new File(path + "/" + folderName);
        if (!directory.exists()) {
            directory.mkdir();
        }

        File poster = new File(path + "/" +folderName + "/" + dirUuid + "_" +posterFileName);//posterFile.getOriginalFilename());
        posterFile.transferTo(poster);

        // rtn에 포스터 값 전달, Multipart type
        rtn.put("poster", posterFile);

        // 업로드 이미지들
        List<FileVO> files = new ArrayList<>();


        //exRegService.insertExhib(exhibitRegisterVO);
        System.out.println(exhibSeq);
        for (MultipartFile file : uploadFile) {
            if (!file.isEmpty()) {
                int fileIndex = 1;
                String fileExtension = file.getOriginalFilename().split("\\.")[1].toLowerCase();

                FileVO fvo = new FileVO(UUID.randomUUID().toString(), file.getContentType());
                files.add(fvo);

                File newFileName = new File(path + "/" +folderName + "/" + fvo.getFileUuid() + "." + fileExtension);

                fvo.setFileDirName(folderName);
                fvo.setExhibSeq(exhibSeq);
                fvo.setFileUuid(fvo.getFileUuid() + "." + fileExtension);
                exRegService.insertExExhibFile(fvo);
                file.transferTo(newFileName);
                // rtn에 key : 파일+순서(1부터) value : 파일 multipart type
                rtn.put("fileIndex" + fileIndex, file);
            }
        }


        // (3) ================ 전시회 정보 수정 =======================
        boardService.modExhib(exhibitRegisterVO);
        System.out.println(exhibitRegisterVO);


        int usrSeq = 2;
        //int usrSeq = Integer.parseInt(session.getAttribute("usrSeq").toString());
        return null;

    }

    @GetMapping("/exhibDelete")
    public ResponseEntity exhibDelete(@RequestParam("exhibSeq") Integer exhibSeq){

        List<FileVO> removeFileList= boardService.getFile(exhibSeq);
        String dirName = removeFileList.get(0).getFileDirName();
        File file = new File(path+"/" +dirName);
        deleteFilesRecursively(file);

        boardService.deleteExhib(exhibSeq);
//
//        // AI 모델 업데이트 실행 (flask 서버)
//        String uri = "http://15.164.142.253:5000/updateModel";
//        RestTemplate rt = new RestTemplate();
//        rt.getForObject(uri, String.class);

        return ResponseEntity.status(HttpStatus.OK).body("ok");
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
                    FileVO fvo = new FileVO(UUID.randomUUID().toString(), file.getContentType());
                    modFileList.add(fvo);

                    File newFileName = new File(path + "/" +originFileList.get(0).getFileDirName() + "/" + fvo.getFileUuid());

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

     boolean deleteFilesRecursively(File rootFile) { //해당 폴더를 재귀적으로 다삭제하는 함수
        File[] allFiles = rootFile.listFiles();
        if (allFiles != null) {
            for (File file : allFiles) {
                deleteFilesRecursively(file);
            }
        }
        System.out.println("Remove file: " + rootFile.getPath());
        return rootFile.delete();
    }

}
