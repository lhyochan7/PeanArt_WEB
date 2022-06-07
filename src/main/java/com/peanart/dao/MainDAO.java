package com.peanart.dao;

import com.peanart.vo.UserVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface MainDAO {
    UserVO getTest();
}
