package com.peanart.member.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {
    private int id;
    private String usrPw;
    private String passwordConfirm;
    private String usrId;
    private String usrName;
    private int active;
}