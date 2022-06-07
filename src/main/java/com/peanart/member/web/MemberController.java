package com.peanart.member.web;

import com.peanart.member.service.impl.MemberServiceImpl;
import com.peanart.member.vo.LoginForm;
import com.peanart.member.vo.MemberVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
public class MemberController {

    @Autowired
    MemberServiceImpl memberService;

    @GetMapping("/login.do")
    public String goLogin(HttpServletRequest req, HttpSession session, ModelMap model){
        if(session.getAttribute("usrSeq") == null){
            return "redirect:/join.do";
        }
        return "/main.html";
    }

    @PostMapping("/login.do")
    public String checkLogin(HttpServletRequest req, HttpSession session, ModelMap model, LoginForm loginForm){
        System.out.println(loginForm);
        MemberVO memberVO = new MemberVO();
        memberVO.setUsrId(loginForm.getUsrId());
        memberVO.setUsrPw(loginForm.getUsrPw());

        MemberVO member = memberService.loginCheck(memberVO);

        if(member==null){
            model.addAttribute("check", "false");
            model.addAttribute("loginForm", loginForm);
            return "login.html";
        }
        session.setAttribute("usrId", member.getUsrId());
        session.setAttribute("usrSeq", member.getUsrSeq());
        session.setAttribute("usrName", member.getUsrName());
        return "main.html";
    }
}
