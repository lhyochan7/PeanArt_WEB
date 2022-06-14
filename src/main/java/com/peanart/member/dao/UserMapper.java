package com.peanart.member.dao;

import com.peanart.member.vo.MemberVO;
import com.peanart.member.vo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface UserMapper {

    MemberVO findUserByLoginId(@Param("usrId") String usrId);
    int setUserInfo(MemberVO memberVO);

}