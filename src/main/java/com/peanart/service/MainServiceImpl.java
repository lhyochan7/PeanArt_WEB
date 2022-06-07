package com.peanart.service;

import com.peanart.dao.MainDAO;
import com.peanart.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MainServiceImpl implements MainService {

    @Autowired
    private MainDAO testRepository;

    @Override
    public UserVO getTest() {
        return testRepository.getTest();
    }
}
