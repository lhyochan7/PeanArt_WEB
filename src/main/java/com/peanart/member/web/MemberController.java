package com.peanart.member.web;

import com.peanart.member.service.impl.MemberServiceImpl;
import com.peanart.member.vo.LoginForm;
import com.peanart.member.vo.MemberVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
public class MemberController {

    @Autowired
    MemberServiceImpl memberService;

//    //Login 페이지 이동
//
//    @GetMapping("/login.do")
//    public String goLogin(HttpServletRequest req, HttpSession session, ModelMap model){
//        if(session.getAttribute("usrSeq") == null){
//            return "redirect:/join.do";
//        }
//        return "/main.html";
//    }

    //Login 체크하기

    @PostMapping("/loginCheck.do")
    public String checkLogin(HttpServletRequest req, HttpSession session, ModelMap model, @RequestBody LoginForm loginForm){
        System.out.println("loginForm" + loginForm);
        MemberVO memberVO = new MemberVO();
        memberVO.setUsrId(loginForm.getUsrId());
        memberVO.setUsrPw(loginForm.getUsrPw());
        MemberVO user = memberService.loginCheck(memberVO);
        System.out.println("user" + user);
        if(user==null){
            model.addAttribute("check", "false");
            model.addAttribute("loginForm", loginForm);
            return "login.html";
        }

        //key user로 MemberVO 객체 넘겨주기
        model.addAttribute("user", user);

        session.setAttribute("usrId", user.getUsrId());
        session.setAttribute("usrSeq", user.getUsrSeq());
        session.setAttribute("usrName", user.getUsrName());
        return "";
    }

    @PostMapping("/join.do")
    public String join(HttpServletRequest req, HttpSession session, ModelMap model, MemberVO memberVO){
        System.out.println(memberVO);
        MemberVO user = new MemberVO();

        user.setUsrId(memberVO.getUsrId());
        user.setUsrPw(memberVO.getUsrPw());
        user.setUsrAdrs(memberVO.getUsrAdrs());
        user.setUsrName(memberVO.getUsrName());
        user.setUsrNickname(memberVO.getUsrNickname());

        memberService.join(user);

        return "";
    }

    @PostMapping("/idCheck.do")
    public String idCheck(HttpServletRequest req, HttpSession session, ModelMap model, String usrId){
        Boolean isIdDuplicate = false;
        if(memberService.idCheck(usrId) != null){
            isIdDuplicate = true;
        }
        model.addAttribute("isIdDuplicate", isIdDuplicate);
        return "";
    }

    @PostMapping("/nicknameCheck.do")
    public String nicknameCheck(HttpServletRequest req, HttpSession session, ModelMap model, String usrNickname){
        Boolean isNickDuplicate = false;
        if(memberService.nicknameCheck(usrNickname) != null){
            isNickDuplicate = true;
        }
        model.addAttribute("isNickDuplicate", isNickDuplicate);
        return "";
    }

}
