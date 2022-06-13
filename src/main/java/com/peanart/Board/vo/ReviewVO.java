package com.peanart.Board.vo;

public class ReviewVO {
    int revSeq;
    int usrSeq;
    int exhibSeq;
    String revContent;
    String revCretDate;
    String revModDate;

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
}
