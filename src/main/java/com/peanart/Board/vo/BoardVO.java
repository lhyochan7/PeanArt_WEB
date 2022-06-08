package com.peanart.Board.vo;

public class BoardVO {

    int exhibSeq;
    String exhibTitle;
    String exhibSimpleExp;
    String exhibStartDate;
    String exhibLocation;
    String fileDirName;
    String fileName;

    public int getExhibSeq() {
        return exhibSeq;
    }

    public void setExhibSeq(int exhibSeq) {
        this.exhibSeq = exhibSeq;
    }

    public String getExhibTitle() {
        return exhibTitle;
    }

    public void setExhibTitle(String exhibTitle) {
        this.exhibTitle = exhibTitle;
    }

    public String getExhibSimpleExp() {
        return exhibSimpleExp;
    }

    public void setExhibSimpleExp(String exhibSimpleExp) {
        this.exhibSimpleExp = exhibSimpleExp;
    }

    public String getExhibStartDate() {
        return exhibStartDate;
    }

    public void setExhibStartDate(String exhibStartDate) {
        this.exhibStartDate = exhibStartDate;
    }

    public String getExhibLocation() {
        return exhibLocation;
    }

    public void setExhibLocation(String exhibLocation) {
        this.exhibLocation = exhibLocation;
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
        return "BoardVO{" +
                "exhibSeq=" + exhibSeq +
                ", exhibTitle='" + exhibTitle + '\'' +
                ", exhibSimpleExp='" + exhibSimpleExp + '\'' +
                ", exhibStartDate='" + exhibStartDate + '\'' +
                ", exhibLocation='" + exhibLocation + '\'' +
                ", fileDirName='" + fileDirName + '\'' +
                ", fileName='" + fileName + '\'' +
                '}';
    }
}
