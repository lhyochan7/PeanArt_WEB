package com.peanart.web;

import com.peanart.service.MainService;
import com.peanart.vo.UserVO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

import static org.junit.jupiter.api.Assertions.*;

class MainControllerTest {

    @Autowired
    private MainService mainService;

    @Test
    @DisplayName("DB 연결 확인")
    void Test1() {

        System.out.println("Test1 Started");

        mainService.getTest();

        System.out.println("Test1 Ended");


    }

}