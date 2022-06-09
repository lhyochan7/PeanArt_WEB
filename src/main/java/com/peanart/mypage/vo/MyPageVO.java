package com.peanart.mypage.vo;

public class MyPageVO {
    private int usrSeq;
    private String usrId;
    private String usrPhone;
    private String usrNickname;
    private String usrName;
    private String usrAdrs;
    private String fileDirName;
    private String fileName;

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

    public String getUsrPhone() {
        return usrPhone;
    }

    public void setUsrPhone(String usrPhone) {
        this.usrPhone = usrPhone;
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

    public String getFileDirName() {
        return fileDirName;
    }

    public void setFileDirName(String fileDirName) {
        this.fileDirName = fileDirName;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public String toString() {
        return "MyPageVO{" +
                "usrSeq=" + usrSeq +
                ", usrId='" + usrId + '\'' +
                ", usrPhone='" + usrPhone + '\'' +
                ", usrNickname='" + usrNickname + '\'' +
                ", usrName='" + usrName + '\'' +
                ", usrAdrs='" + usrAdrs + '\'' +
                ", fileDirName='" + fileDirName + '\'' +
                ", fileName='" + fileName + '\'' +
                '}';
    }
}
