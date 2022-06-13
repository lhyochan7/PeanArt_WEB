package com.peanart.Board.service;

import com.peanart.Board.dao.BoardDAO;
import com.peanart.Board.vo.BoardVO;
import com.peanart.Board.vo.ReviewVO;
import com.peanart.ExhibitRegisteration.vo.ExhibitRegisterVO;
import com.peanart.main.vo.FileVO;
import com.peanart.mypage.vo.MyPageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardServiceImpl implements BoardService{

    @Autowired
    private BoardDAO boardDAO;


    @Override
    public List<BoardVO> getBoardList() {
        return boardDAO.getBoardList();

    }

    @Override
    public ExhibitRegisterVO getExhibInfo(int exhibSeq) {
        return boardDAO.getExhibInfo(exhibSeq);
    }
    @Override
    public MyPageVO getUserInfo(int UsrSeq){
        return  boardDAO.getUserInfo(UsrSeq) ;
    }

    @Override
    public List<FileVO> getfile(int exhibSeq) {
        return boardDAO.getfile(exhibSeq);
    }

    @Override
    public List<ReviewVO> getReview(int exhibSeq) {
        return boardDAO.getReview(exhibSeq);
    }
}
