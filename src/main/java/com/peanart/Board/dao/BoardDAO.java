package com.peanart.Board.dao;

import com.peanart.Board.vo.BoardVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import java.util.List;

@Mapper
@Repository
public interface BoardDAO {
    List<BoardVO> getBoardList();
}
