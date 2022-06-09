package com.peanart.mypage.dao;

import com.peanart.member.vo.MemberVO;
import com.peanart.mypage.vo.MyPageVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface MyPageDAO {
    public MyPageVO getUserInfo(int usrSeq);
}
