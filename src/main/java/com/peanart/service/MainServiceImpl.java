package com.peanart.service;

import com.peanart.dao.MainDAO;
import com.peanart.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MainServiceImpl implements MainService {

    @Autowired
    private MainDAO mainDAO;

    @Override
    public List<UserVO> getTest() {
        return mainDAO.getTest();
    }
}
