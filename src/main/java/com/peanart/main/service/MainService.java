package com.peanart.main.service;

import com.peanart.Board.vo.BoardVO;
import com.peanart.ExhibitRegisteration.vo.ExhibitRegisterVO;
import com.peanart.main.vo.UserVO;
import com.peanart.main.vo.VisitedExhibVO;
import com.peanart.mypage.vo.MyPageVO;

import java.util.HashMap;
import java.util.List;


public interface MainService {
    public List<UserVO> getTest();

    List<BoardVO> getExibList();

    List<BoardVO> getFiveExhib();

    List<ExhibitRegisterVO> getSearchList(HashMap<String, String> map);

    List<VisitedExhibVO> getMyPageVisitedExhib(Integer userSeq);
}
