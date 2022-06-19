package com.peanart.main.service;

import com.peanart.Board.vo.BoardVO;
import com.peanart.ExhibitRegisteration.vo.ExhibitRegisterVO;
import com.peanart.main.dao.MainDAO;
import com.peanart.main.vo.UserVO;
import com.peanart.main.vo.VisitedExhibVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class MainServiceImpl implements MainService {

    @Autowired
    private MainDAO mainDAO;

    @Override
    public List<UserVO> getTest() {
        return mainDAO.getTest();
    }

    @Override
    public List<BoardVO> getExibList() {
        return mainDAO.getExibList();
    }

    @Override
    public List<BoardVO> getFiveExhib() {
        return mainDAO.getFiveExhib();
    }

    @Override
    public List<BoardVO> getSearchList(HashMap<String, String> map) {
        return mainDAO.getSearchList(map);
    }

    @Override
    public List<VisitedExhibVO> getMyPageVisitedExhib(Integer userSeq) {
        return mainDAO.getMyPageVisitedExhib(userSeq);
    }
}
