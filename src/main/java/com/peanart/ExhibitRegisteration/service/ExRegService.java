package com.peanart.ExhibitRegisteration.service;

import com.peanart.ExhibitRegisteration.vo.ExhibitRegisterVO;
import com.peanart.main.vo.FileVO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


public interface ExRegService {
    void insertExhib(ExhibitRegisterVO exhibitRegisterVO);
    int getLastID();
    void insertExExhibFile(FileVO fileVO);
}
