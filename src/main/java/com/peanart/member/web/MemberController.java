package com.peanart.member.web;

import com.peanart.member.service.impl.MemberServiceImpl;
import com.peanart.member.vo.LoginForm;
import com.peanart.member.vo.MemberVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

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
    @GetMapping("/duplicheck")
    public boolean checkDuplicate(HttpServletRequest req, HttpSession session, ModelMap model){
        return true;
    }
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
    public String join(HttpServletRequest req, HttpSession session, ModelMap model, @RequestBody MemberVO memberVO){
        System.out.println(memberVO);
//        MemberVO user = new MemberVO();
//
//        user.setUsrId(memberVO.getUsrId());
//        user.setUsrPw(memberVO.getUsrPw());
//        user.setUsrAdrs(memberVO.getUsrAdrs());
//        user.setUsrName(memberVO.getUsrName());
//        user.setUsrNickname(memberVO.getUsrNickname());
//        user.setUsrPhone(memberVO.getUsrPhone());
        memberService.join(memberVO);

        return "";
    }

    @PostMapping("/duplicheck.do")
    public Map<String, Object> idCheck(HttpServletRequest req, HttpSession session, ModelMap model, @RequestParam(value="email", required = false) String email, @RequestParam(value="nickname", required = false) String nickname){
        Map<String, Object> rtn = new HashMap<>();
        if(email != null){
            if(memberService.idCheck(email) != null){
                rtn.put("type", "email");
                rtn.put("duplicated", false);
                rtn.put("status", true);
                return rtn;
            }
        } else if (nickname != null){
            if(memberService.nicknameCheck(nickname) != null){
                rtn.put("type", "nickname");
                rtn.put("duplicated", false);
                rtn.put("status", true);
                return rtn;
            }
        }
        rtn.put("status", false);
        return rtn;
    }
}
