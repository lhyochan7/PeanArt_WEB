package com.peanart.mypage.service.impl;

import com.peanart.member.vo.MemberVO;
import com.peanart.mypage.dao.MyPageDAO;
import com.peanart.mypage.service.MyPageService;
import com.peanart.mypage.vo.MyPageVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class MyPageServicecImpl implements MyPageService {

    @Resource
    MyPageDAO myPageDAO;

    @Override
    public MyPageVO getUserInfo(int usrSeq) {
        return myPageDAO.getUserInfo(usrSeq);
    }
}
