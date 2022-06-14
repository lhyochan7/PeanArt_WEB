package com.peanart.member.dao;

import com.peanart.member.vo.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface RoleMapper {
    Role getRoleInfo(@Param("role") String role);
}