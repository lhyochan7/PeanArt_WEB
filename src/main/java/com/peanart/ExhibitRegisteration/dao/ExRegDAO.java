package com.peanart.ExhibitRegisteration.dao;

import com.peanart.ExhibitRegisteration.vo.ExhibitRegisterVO;
import com.peanart.main.vo.FileVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface ExRegDAO {
    void insertExhib(ExhibitRegisterVO exhibitRegisterVO);
    int getLastID();
    void insertExExhibFile(FileVO fileVO);

}
