package com.peanart.mypage.vo;

public class MyPageFollowForm {
   private String fileDirName;
   private String fileName;
   private String usrId;
   private String usrNickname;

    public String getFiledDirName() {
        return fileDirName;
    }

    public void setFiledDirName(String filedDirName) {
        this.fileDirName = fileDirName;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getUsrId() {
        return usrId;
    }

    public void setUsrId(String usrId) {
        this.usrId = usrId;
    }

    public String getUsrNickname() {
        return usrNickname;
    }

    public void setUsrNickname(String usrNickname) {
        this.usrNickname = usrNickname;
    }

    @Override
    public String toString() {
        return "MyPageFollowForm{" +
                "filedDirName='" + fileDirName + '\'' +
                ", fileName='" + fileName + '\'' +
                ", usrId='" + usrId + '\'' +
                ", usrNickname='" + usrNickname + '\'' +
                '}';
    }
}
