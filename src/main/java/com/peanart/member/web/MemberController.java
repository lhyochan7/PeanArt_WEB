package com.peanart.member.web;

import com.peanart.member.service.impl.MemberServiceImpl;
import com.peanart.member.vo.LoginForm;
import com.peanart.member.vo.MemberVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
public class MemberController {

    @Autowired
    MemberServiceImpl memberService;

    //Login 페이지 이동
    @GetMapping("/login")
    public ResponseEntity goLogin(HttpServletRequest req, HttpSession session, ModelMap model){
        try {
            return ResponseEntity.ok().build();
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }


    //Login 체크하기 / 로그인 성공 : HttpStatus = ok, 실패 : HttpStatus = bad request, 그 외 : not found
    @PostMapping("/loginCheck")
    public ResponseEntity<MemberVO> checkLogin(HttpServletRequest req, HttpSession session,  @RequestBody LoginForm loginForm){
        System.out.println("loginForm" + loginForm);
        try {
            MemberVO memberVO = new MemberVO();
            memberVO.setUsrId(loginForm.getUsrId());
            memberVO.setUsrPw(loginForm.getUsrPw());
            MemberVO user = memberService.loginCheck(memberVO);
            System.out.println("user" + user);
            if(user==null){
                return ResponseEntity.badRequest().build();
            }
            // session에 Id, Seq, name 넘겨주기
            session.setAttribute("usrId", user.getUsrId());
            session.setAttribute("usrSeq", user.getUsrSeq());
            session.setAttribute("usrName", user.getUsrName());

            // body에 MemberVO user 리턴
            return ResponseEntity.ok()
                    .body(user);
        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }

    }


    // 회원가입 / 성공 : HttpStatus = created, 실패 : HttpStatus = bad request
    @PostMapping("/join")
    public ResponseEntity join(HttpServletRequest req, HttpSession session, ModelMap model, MemberVO memberVO){

        try {
            memberService.join(memberVO);
            // 회원가입 후 처리할 URI 넣기기
            return ResponseEntity.created(URI.create("/login")).build();
        }catch (Exception e){
            // 오류 발생 시
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }


    // 중복 확인  / 중복 없음 : HttpStatus = ok, 중복 있음 : HttpStatus = bad request, 그 외 : not found
    @GetMapping("/duple-check")
    public ResponseEntity<Map<String, Object>> idCheck(HttpServletRequest req, HttpSession session, ModelMap model, @RequestParam(value="email", required = false) String email, @RequestParam(value="nickname", required = false) String nickname){
        Map<String, Object> rtn = new HashMap<>();
        try {
            if(email != null){
                rtn.put("type", "email");
                if(memberService.idCheck(email) != null){
                    return ResponseEntity.badRequest()
                            .body(rtn);
                }
                rtn.put("duplicated", false);
                return ResponseEntity.ok()
                        .body(rtn);
            } else if (nickname != null){
                rtn.put("type", "nickname");
                if(memberService.nicknameCheck(nickname) != null) {
                    return ResponseEntity.badRequest()
                            .body(rtn);
                }
                rtn.put("duplicated", false);
                return ResponseEntity.ok()
                        .body(rtn);
            }
            return ResponseEntity.notFound().build();
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }
}
