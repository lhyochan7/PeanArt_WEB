package com.peanart.main.dao;

import com.peanart.main.vo.UserVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface MainDAO {
    List<UserVO> getTest();

}