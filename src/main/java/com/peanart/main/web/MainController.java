package com.peanart.main.web;

import com.peanart.Board.vo.BoardVO;
import com.peanart.ExhibitRegisteration.vo.ExhibitRegisterVO;
import com.peanart.main.service.MainService;
import com.peanart.main.vo.FileVO;
import com.peanart.main.vo.UserVO;
import com.peanart.main.vo.VisitedExhibVO;
import com.peanart.mypage.vo.MyPageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
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

                File newFileName = new File(path + "/" +folderName + "/" + fvo.getfile_Uuid() + "_" + fvo.getFileName());

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

        Path path = Paths.get(filePath + '/' + fvo.getfile_Uuid()+ '_' + fvo.getFileName());

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


    @GetMapping("/search")
    public ResponseEntity<List<ExhibitRegisterVO>> searchExhib (@RequestParam (value = "kind", required = false) Integer kind,
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
        List<ExhibitRegisterVO> searchList = mainService.getSearchList(map);

        System.out.println(searchList);


        return new ResponseEntity<List<ExhibitRegisterVO>>(searchList, HttpStatus.OK);
    }

    @RequestMapping("/myPageVisitedExhib")
    public ResponseEntity<List<VisitedExhibVO>> myPageVisitedExhib (HttpSession session)
    {
        //Integer userSeq = session.getAttribute("userSeq");

        Integer userSeq = 1;

        List<VisitedExhibVO> visitedList = mainService.getMyPageVisitedExhib(userSeq);

       return new ResponseEntity<List<VisitedExhibVO>>(visitedList, HttpStatus.OK);
    }


}
