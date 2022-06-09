package com.peanart.mypage.service;

import com.peanart.member.vo.MemberVO;
import com.peanart.mypage.vo.MyPageVO;

public interface MyPageService {
    MyPageVO getUserInfo(int usrSeq);
}
