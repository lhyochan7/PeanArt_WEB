package com.peanart.main.service;

import com.peanart.Board.vo.BoardVO;
import com.peanart.main.vo.UserVO;
import java.util.List;


public interface MainService {
    public List<UserVO> getTest();

    List<BoardVO> getExibList();
}
