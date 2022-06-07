package com.peanart.web;

import com.peanart.service.MainService;
import com.peanart.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {
    @Autowired
    private MainService testService;

    @GetMapping("/index")
    public UserVO getTest(){
        return testService.getTest();
    }

}
