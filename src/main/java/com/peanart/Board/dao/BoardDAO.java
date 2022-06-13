package com.peanart.Board.dao;

import com.peanart.Board.vo.BoardVO;
import com.peanart.Board.vo.ReviewVO;
import com.peanart.ExhibitRegisteration.vo.ExhibitRegisterVO;
import com.peanart.main.vo.FileVO;
import com.peanart.mypage.vo.MyPageVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import java.util.List;

@Mapper
@Repository
public interface BoardDAO {
    List<BoardVO> getBoardList();
    ExhibitRegisterVO getExhibInfo(int ExhibSeq);
    MyPageVO getUserInfo (int usrSeq);
    List<FileVO> getfile(int exhibSeq);
    List<ReviewVO> getReview(int exhibSeq);
}
