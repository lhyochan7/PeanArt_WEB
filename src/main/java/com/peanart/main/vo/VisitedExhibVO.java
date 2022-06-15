package com.peanart.main.vo;

public class VisitedExhibVO {

    private int usrSeq;
    private int exhibSeq;
    private String exhibTitle;
    private String fileDirName;
    private String fileName;

    public int getUsrSeq() {
        return usrSeq;
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

    public String getExhibTitle() {
        return exhibTitle;
    }

    public void setExhibTitle(String exhibTitle) {
        this.exhibTitle = exhibTitle;
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
        return "VisitedExhibVO{" +
                "usrSeq=" + usrSeq +
                ", exhibSeq=" + exhibSeq +
                ", exhibTitle='" + exhibTitle + '\'' +
                ", fileDirName='" + fileDirName + '\'' +
                ", fileName='" + fileName + '\'' +
                '}';
    }
}

