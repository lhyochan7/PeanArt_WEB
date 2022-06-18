package com.peanart.mypage.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter@Setter@ToString
public class MyPageFollowForm {
    private String usrId;
    private String usrNickname;
    private String followedImgUrl;
}
