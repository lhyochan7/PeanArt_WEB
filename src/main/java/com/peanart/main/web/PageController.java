package com.peanart.main.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping("/main")
    public String goMain (){
        return "index";
    }

    @GetMapping("/login")
    public String goLogin (){
        return "index";
    }

    @GetMapping("/register")
    public String goRegister (){
        return "index";
    }

    @GetMapping("/exhib/{id}/detail")
    public String goExhibDetail (){
        return "index";
    }

    @GetMapping("/exhib/{id}/modify")
    public String goExhibModify (){
        return "index";
    }

    @GetMapping("/exhib/list")
    public String goExhibList (){
        return "index";
    }

    @GetMapping("/exhib/register")
    public String goExhibRegister (){
        return "index";
    }

    @GetMapping("/myPage")
    public String goMypage (){
        return "index";
    }

    @GetMapping("/airecommend")
    public String goAIRecommend (){
        return "index";
    }
}
