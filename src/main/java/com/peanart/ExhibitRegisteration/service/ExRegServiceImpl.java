package com.peanart.ExhibitRegisteration.service;

import com.peanart.ExhibitRegisteration.dao.ExRegDAO;
import com.peanart.ExhibitRegisteration.vo.ExhibitRegisterVO;
import com.peanart.main.vo.FileVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExRegServiceImpl implements ExRegService {
    @Autowired
    private ExRegDAO exRegDAO;

    @Override
    public void insertExhib(ExhibitRegisterVO exhibitRegisterVO) {

        exRegDAO.insertExhib(exhibitRegisterVO);
    }

    @Override
    public int getLastID() {
        return exRegDAO.getLastID();
    }

    @Override
    public void insertExExhibFile(FileVO fileVO) {
        exRegDAO.insertExExhibFile(fileVO);
    }
}
