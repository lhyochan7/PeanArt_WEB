package com.peanart.web;

import com.peanart.main.service.MainService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

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