package com.peanart.mypage.dao;

import com.peanart.mypage.vo.MyPageFileVO;
import com.peanart.mypage.vo.MyPageVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface MyPageDAO {
    public MyPageVO getUserInfo(int usrSeq);

    void setProfileImg(MyPageFileVO myPageFileVO);

    void updateProfileImg(MyPageFileVO myPageFileVO);

    int isThereImg(int usrSeq);

    MyPageFileVO getProfileImg(int usrSeq);
}
