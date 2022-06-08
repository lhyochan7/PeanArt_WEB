package com.peanart.member.service.impl;

import com.peanart.member.dao.MemberDAO;
import com.peanart.member.service.MemberService;
import com.peanart.member.vo.MemberVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberDAO memberDAO;


    @Override
    public MemberVO loginCheck(MemberVO memberVO) {
        System.out.println("serviceTest" + memberVO);
        return memberDAO.loginCheck(memberVO);
    }

    @Override
    public void join(MemberVO memberVO) {
        memberDAO.join();
    }
}
