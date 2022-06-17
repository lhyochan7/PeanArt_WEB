package com.peanart.Board.vo;

public class ReviewVO {
    int revSeq;
    int usrSeq;
    int exhibSeq;
    String revContent;
    String revCretDate;
    String revModDate;
    String usrNickname;
    String fileDirName;
    String fileName;

    public int getUsrSeq() {
        return usrSeq;
    }

    public int getRevSeq() {
        return revSeq;
    }

    public void setRevSeq(int revSeq) {
        this.revSeq = revSeq;
    }

    public void setUsrSeq(int usrSeq) {
        this.usrSeq = usrSeq;
    }

    public int getExhibSeq() {
        return exhibSeq;
    }

    public void setExhibSeq(int exhibSeq) {
        this.exhibSeq = exhibSeq;
    }

    public String getRevContent() {
        return revContent;
    }

    public void setRevContent(String revContent) {
        this.revContent = revContent;
    }

    public String getRevCretDate() {
        return revCretDate;
    }

    public void setRevCretDate(String revCretDate) {
        this.revCretDate = revCretDate;
    }

    public String getRevModDate() {
        return revModDate;
    }

    public void setRevModDate(String revModDate) {
        this.revModDate = revModDate;
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

    public String getUsrNickname() {
        return usrNickname;
    }

    public void setUsrNickname(String usrNickname) {
        this.usrNickname = usrNickname;
    }
}
