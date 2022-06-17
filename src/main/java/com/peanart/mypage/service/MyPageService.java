package com.peanart.mypage.service;

import com.peanart.mypage.vo.MyPageExhibVO;
import com.peanart.mypage.vo.MyPageFileVO;
import com.peanart.mypage.vo.MyPageFollowVO;
import com.peanart.mypage.vo.MyPageVO;

import java.util.List;

public interface MyPageService {
    MyPageVO getUserInfo(int usrSeq);

    void setProfileImg(MyPageFileVO myPageFileVO);

    void updateProfileImg(MyPageFileVO myPageFileVO);

    int isThereImg(int usrSeq);

    MyPageFileVO getProfileImg(int usrSeq);

    List<MyPageFollowVO> getFollowList(int usrSeq);

    List<MyPageExhibVO> getExhibList(int usrSeq);
}
