package com.peanart.mypage.service;

import com.peanart.member.vo.MemberVO;
import com.peanart.mypage.vo.MyPageFileVO;
import com.peanart.mypage.vo.MyPageVO;

public interface MyPageService {
    MyPageVO getUserInfo(int usrSeq);

    void setProfileImg(MyPageFileVO myPageFileVO);

    void updateProfileImg(MyPageFileVO myPageFileVO);

    int isThereImg(int usrSeq);

    MyPageFileVO getProfileImg(int usrSeq);
}
