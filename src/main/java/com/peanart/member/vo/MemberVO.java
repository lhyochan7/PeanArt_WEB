package com.peanart.member.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter@Setter@ToString
public class MemberVO {
    private int usrSeq;
    private String usrId;
    private String usrPw;
    private String usrNickname;
    private String usrName;
    private String usrAdrs;
    private String usrPhone;
    private String roleId;
}
