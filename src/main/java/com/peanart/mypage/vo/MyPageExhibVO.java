package com.peanart.mypage.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter@Setter@ToString
public class MyPageExhibVO {
    private int usrSeq;
    private int exhibSeq;
    private String exhibTitle;
    private String fileDirName;
    private String fileName;
}
