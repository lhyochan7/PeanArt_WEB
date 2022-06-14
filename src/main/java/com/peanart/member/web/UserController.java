package com.peanart.member.web;

import com.peanart.member.service.impl.UserService;
import com.peanart.member.vo.MemberVO;
import com.peanart.member.vo.User;
import com.peanart.member.vo.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    // root, login url 요청 시 페이지 이동
    @GetMapping(value = {"/", "login"})
    public ModelAndView getLoginPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("test");
        return modelAndView;
    }

    // registration url 요청 시 페이지 반환
    @GetMapping("registration")
    public ModelAndView getRegistrationPage() {
        ModelAndView modelAndView = new ModelAndView();
        User user = new User();
        modelAndView.addObject("user", user);
        modelAndView.setViewName("signup");

        return modelAndView;
    }

    // 회원가입 버튼 클릭시 실행
    @PostMapping("registration")
    public ModelAndView createNewUser(MemberVO memberVO, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();

        MemberVO userExists = userService.findUserByLoginId(memberVO.getUsrId());
        if (userExists != null) {
            bindingResult
                    .rejectValue("usrId", "error.usrId",
                            "There is already a user registered with the loginId provided");
        }

        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("registration");
        } else {
            userService.saveUser(memberVO);
            modelAndView.addObject("successMessage", "User has been registered successfully");
            modelAndView.addObject("memberVO", new MemberVO());
            modelAndView.setViewName("registration");
        }

        return modelAndView;
    }

    @GetMapping("home")
    public ModelAndView home(){
        ModelAndView modelAndView = new ModelAndView();

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserPrincipal userPrincipal = (UserPrincipal) auth.getPrincipal();

        System.out.println(userPrincipal.toString());

        modelAndView.addObject("userName", "Welcome " + userPrincipal.getName() + " (" + userPrincipal.getId() + ")");
        modelAndView.addObject("adminMessage","Content Available Only for Users with Admin Role");
        modelAndView.setViewName("home");
        return modelAndView;
    }

    @GetMapping("exception")
    public ModelAndView getUserPermissionExceptionPage() {
        ModelAndView mv = new ModelAndView();

        mv.setViewName("access-denied");

        return mv;
    }

}