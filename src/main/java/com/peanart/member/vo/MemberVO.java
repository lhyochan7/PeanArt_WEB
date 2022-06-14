package com.peanart.member.vo;


public class MemberVO {
    private int usrSeq;
    private String usrId;
    private String usrPw;
    private String usrNickname;
    private String usrName;
    private String usrAdrs;
    private String usrPhone;
    private String roleName;

    private int active;

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public int getUsrSeq() {
        return usrSeq;
    }

    public void setUsrSeq(int usrSeq) {
        this.usrSeq = usrSeq;
    }

    public String getUsrId() {
        return usrId;
    }

    public void setUsrId(String usrId) {
        this.usrId = usrId;
    }

    public String getUsrPw() {
        return usrPw;
    }

    public void setUsrPw(String usrPw) {
        this.usrPw = usrPw;
    }

    public String getUsrNickname() {
        return usrNickname;
    }

    public void setUsrNickname(String usrNickname) {
        this.usrNickname = usrNickname;
    }

    public String getUsrName() {
        return usrName;
    }

    public void setUsrName(String usrName) {
        this.usrName = usrName;
    }

    public String getUsrAdrs() {
        return usrAdrs;
    }

    public void setUsrAdrs(String usrAdrs) {
        this.usrAdrs = usrAdrs;
    }

    public String getUsrPhone() {
        return usrPhone;
    }

    public void setUsrPhone(String usrPhone) {
        this.usrPhone = usrPhone;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @Override
    public String toString() {
        return "MemberVO{" +
                "usrSeq=" + usrSeq +
                ", usrId='" + usrId + '\'' +
                ", usrPw='" + usrPw + '\'' +
                ", usrNickname='" + usrNickname + '\'' +
                ", usrName='" + usrName + '\'' +
                ", usrAdrs='" + usrAdrs + '\'' +
                ", usrPhone='" + usrPhone + '\'' +
                ", roleName='" + roleName + '\'' +
                '}';
    }
}
