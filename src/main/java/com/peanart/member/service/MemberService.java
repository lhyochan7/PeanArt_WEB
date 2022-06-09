package com.peanart.member.service;

import com.peanart.member.vo.MemberVO;

public interface MemberService {
    MemberVO loginCheck(MemberVO memberVO);
    void join(MemberVO memberVO);

    MemberVO idCheck(String usrId);

    MemberVO nicknameCheck(String usrNickname);
}
