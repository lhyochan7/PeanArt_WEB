package com.peanart.main.web;

import com.peanart.main.service.MainService;
import com.peanart.main.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MainController {
    @Autowired
    private MainService mainService;

    @GetMapping("/index")
    public List<UserVO> getTest(String id){
        return mainService.getTest();
    }

}
