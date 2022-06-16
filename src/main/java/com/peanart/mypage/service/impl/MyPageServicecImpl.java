package com.peanart.mypage.service.impl;

import com.peanart.mypage.dao.MyPageDAO;
import com.peanart.mypage.service.MyPageService;
import com.peanart.mypage.vo.MyPageExhibVO;
import com.peanart.mypage.vo.MyPageFileVO;
import com.peanart.mypage.vo.MyPageFollowVO;
import com.peanart.mypage.vo.MyPageVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class MyPageServicecImpl implements MyPageService {

    @Resource
    MyPageDAO myPageDAO;

    @Override
    public MyPageVO getUserInfo(int usrSeq) {
        return myPageDAO.getUserInfo(usrSeq);
    }

    @Override
    public void setProfileImg(MyPageFileVO myPageFileVO) {
        myPageDAO.setProfileImg(myPageFileVO);
    }

    @Override
    public void updateProfileImg(MyPageFileVO myPageFileVO) {
        myPageDAO.updateProfileImg(myPageFileVO);
    }

    @Override
    public int isThereImg(int usrSeq) {
        return myPageDAO.isThereImg(usrSeq);
    }

    @Override
    public MyPageFileVO getProfileImg(int usrSeq) {
        return myPageDAO.getProfileImg(usrSeq);
    }

    @Override
    public List<MyPageFollowVO> getFollowList(int usrSeq) {
        return myPageDAO.getFollowList(usrSeq);
    }

    @Override
    public List<MyPageExhibVO> getExhibList(int usrSeq) {
        return myPageDAO.getExhibList(usrSeq);
    }
}
