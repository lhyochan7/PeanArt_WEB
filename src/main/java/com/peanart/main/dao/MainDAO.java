package com.peanart.main.dao;

import com.peanart.Board.vo.BoardVO;
import com.peanart.ExhibitRegisteration.vo.ExhibitRegisterVO;
import com.peanart.main.vo.UserVO;
import com.peanart.main.vo.VisitedExhibVO;
import com.peanart.mypage.vo.MyPageVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Mapper
@Repository
public interface MainDAO {
    List<UserVO> getTest();

    List<BoardVO> getExibList();

    List<BoardVO> getFiveExhib();

    List<BoardVO> getSearchList(HashMap<String, String> map);

    List<VisitedExhibVO> getMyPageVisitedExhib(Integer userSeq);
}
