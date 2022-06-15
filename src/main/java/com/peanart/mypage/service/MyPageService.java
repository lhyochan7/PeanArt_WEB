package com.peanart.mypage.service;

import com.peanart.member.vo.MemberVO;
import com.peanart.mypage.vo.MyPageExhibForm;
import com.peanart.mypage.vo.MyPageFileVO;
import com.peanart.mypage.vo.MyPageFollowForm;
import com.peanart.mypage.vo.MyPageVO;

import java.util.List;

public interface MyPageService {
    MyPageVO getUserInfo(int usrSeq);

    void setProfileImg(MyPageFileVO myPageFileVO);

    void updateProfileImg(MyPageFileVO myPageFileVO);

    int isThereImg(int usrSeq);

    MyPageFileVO getProfileImg(int usrSeq);

    List<MyPageFollowForm> getFollowList(int usrSeq);

    List<MyPageExhibForm> getExhibList(int usrSeq);
}
