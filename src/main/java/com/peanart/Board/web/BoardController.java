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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

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

    @GetMapping("/detailA")
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

    @PostMapping("/reviewRegister.do")
    public ResponseEntity<String> regReview( HttpSession session, @RequestParam String revContent){
        ReviewVO reviewVO = new ReviewVO();

        reviewVO.setUsrSeq((int)session.getAttribute("usrSeq"));
        reviewVO.setExhibSeq((int)session.getAttribute("exhibSeq"));
        reviewVO.setRevContent(revContent);
        boardService.regReview(reviewVO);

        return ResponseEntity.status(HttpStatus.OK).body("Ajax 해줘"); //페이지 전체 요청보단 ajax로 리뷰 영역만 갱신 해야함
    }

    @PostMapping("/detailModifiy---------------") //게시글 수정
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

    @GetMapping("exhibDelete")
    public ResponseEntity exhibDelete(@RequestParam("exhibSeq") Integer exhibSeq){

        List<FileVO> removeFileList= boardService.getFile(exhibSeq);
        String dirName = removeFileList.get(0).getFileDirName();
        File file = new File(path+"/" +dirName);
        deleteFilesRecursively(file);

        boardService.deleteExhib(exhibSeq);

        // AI 모델 업데이트 실행 (flask 서버)
        String uri = "http://localhost:5000/updateModel";
        RestTemplate rt = new RestTemplate();
        rt.getForObject(uri, String.class);

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
