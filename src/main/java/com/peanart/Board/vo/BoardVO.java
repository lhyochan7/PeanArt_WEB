package com.peanart.Board.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BoardVO {
    int exhibSeq;
    String exhibTitle;
    String exhibSimpleExp;
    String exhibStartDate;
    String exhibEndDate;
    String exhibLocation;
    String fileDirName;
    String fileName;
}
