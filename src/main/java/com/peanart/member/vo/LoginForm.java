package com.peanart.member.vo;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter@Setter@ToString@Data
public class LoginForm{
    private String usrId;
    private String usrPw;
}
