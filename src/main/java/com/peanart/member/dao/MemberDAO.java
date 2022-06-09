package com.peanart.member.dao;

import com.peanart.member.vo.MemberVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface MemberDAO {
    MemberVO loginCheck(MemberVO memberVO);
    void join(MemberVO memberVO);

    MemberVO idCheck(String usrId);

    MemberVO nicknameCheck(String usrNickname);
}
