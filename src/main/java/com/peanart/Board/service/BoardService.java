package com.peanart.Board.service;

import com.peanart.Board.vo.BoardVO;
import com.peanart.Board.vo.ReviewVO;
import com.peanart.ExhibitRegisteration.vo.ExhibitRegisterVO;
import com.peanart.main.vo.FileVO;
import com.peanart.mypage.vo.MyPageVO;

import java.io.File;
import java.util.List;

public interface BoardService {
    List<BoardVO> getBoardList();
    ExhibitRegisterVO getExhibInfo(int exhibSeq);

    MyPageVO getUserInfo (int usrSeq);
    List<FileVO> getFile(int exhibSeq);

    List<ReviewVO> getReview(int exhibSeq);
    void regReview(ReviewVO reviewVO);

    void deleteExhib(int exhibseq);
    void deleteFiles(FileVO fileVO);
    void modExhib(ExhibitRegisterVO exhibitRegisterVO);

}
