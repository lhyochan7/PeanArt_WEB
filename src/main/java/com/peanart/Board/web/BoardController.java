package com.peanart.Board.web;

import com.peanart.Board.service.BoardService;
import com.peanart.Board.vo.BoardVO;
import com.peanart.Board.vo.ReviewVO;
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

import javax.servlet.http.HttpSession;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;

@Controller
public class BoardController {
    @Value("${spring.servlet.multipart.location}")
    String path;

    @Autowired
    private BoardService boardService;

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

        model.addAttribute("map", map);

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
//    @PostMapping("/detailModifiy.do") //게시글 수정
//    public ResponseEntity<String> moeDetail(HttpSession session,ExhibitRegisterVO exhibitRegisterVO,FileVO fileVO){
//
//        // session.usrSeq == exhib.usrSeq true 면 sql 실행 아니면 fail 반환 하자
//        int sessionUsrSeq = (int)session.getAttribute("usrSeq");
//        int currentExhibUsrSeq = exhibitRegisterVO.getUsrSeq();
//
//        if (sessionUsrSeq == currentExhibUsrSeq){
//            //service
//
//        }else{
//            return ResponseEntity.status(HttpStatus.OK).body("다른 유저에용");
//        }
//        return ResponseEntity.status(HttpStatus.OK).body("수정 해줘");
//    }

    @GetMapping("exhibDelete")
    public ResponseEntity exhibDelete(@RequestParam("exhibSeq") Integer exhibSeq){

        List<FileVO> removeFileList= boardService.getFile(exhibSeq);
        String dirName = removeFileList.get(0).getFileDirName();
        File file = new File(path+"/" +dirName);
        deleteFilesRecursively(file);

        boardService.deleteExhib(exhibSeq);
        return ResponseEntity.status(HttpStatus.OK).body("fuck you");
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
