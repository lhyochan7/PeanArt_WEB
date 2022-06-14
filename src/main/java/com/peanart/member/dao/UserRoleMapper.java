package com.peanart.member.dao;

import com.peanart.member.vo.UserRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface UserRoleMapper {

    void setUserRoleInfo(@Param("param") UserRole param);

}