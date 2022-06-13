package com.peanart.Board.web;

import com.peanart.Board.service.BoardService;
import com.peanart.Board.vo.BoardVO;
import com.peanart.Board.vo.ReviewVO;
import com.peanart.ExhibitRegisteration.vo.ExhibitRegisterVO;
import com.peanart.main.vo.FileVO;
import com.peanart.mypage.vo.MyPageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;

@Controller
public class BoardController {
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
}
