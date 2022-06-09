package com.peanart.mypage.web;

import com.peanart.mypage.service.MyPageService;
import com.peanart.mypage.vo.MyPageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class MyPageController {

    @Autowired
    MyPageService myPageService;

    @RequestMapping("/myPage")
    public String getUserInfo(HttpServletRequest req, HttpSession session, ModelMap model){
        //MyPage User Info
        int usrSeq =  1; //Integer.parseInt(session.getAttribute("usrSeq").toString());
        MyPageVO user = myPageService.getUserInfo(usrSeq);
        model.addAttribute("userInfo", user);

        String folderName = user.getFileDirName();
        String fileName = user.getFileName();

        String imgSrc = "http://localhost:8080/imagePath/" + folderName + "/" + fileName;

        model.addAttribute("path", imgSrc);
        System.out.println("imgSrc : " + imgSrc);
        return "result";
    }

}
