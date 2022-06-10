package com.peanart.mypage.vo;

public class MyPageFileVO {
    private int usrSeq;
    private String fileDirName;
    private String fileName;

    public int getUsrSeq() {
        return usrSeq;
    }

    public void setUsrSeq(int usrSeq) {
        this.usrSeq = usrSeq;
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
        return "MyPageFileVO{" +
                "usrSeq=" + usrSeq +
                ", fileDirName='" + fileDirName + '\'' +
                ", fileName='" + fileName + '\'' +
                '}';
    }
}
