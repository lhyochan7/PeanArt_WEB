package com.peanart.ExhibitRegisteration.vo;

import com.peanart.main.vo.FileVO;

import java.util.List;

public class ExhibitRegisterVO {
    String exhibTitle;
    int exhibKind;
    String exhibTheme;
    String exhibSimpleExp;
    String exhibDetailExp;
    String exhibStartDate;
    String exhibEndDate;
    String exhibLocation;
    String exhibUri;
    int usrSeq;
    String exhibCretDate;
    String exhibModDate;
    String fileDirName;
    String fileName;
    int goodsAllow;

    public String getExhibCretDate() {
        return exhibCretDate;
    }

    public void setExhibCretDate(String exhibCretDate) {
        this.exhibCretDate = exhibCretDate;
    }

    public String getExhibModDate() {
        return exhibModDate;
    }

    public void setExhibModDate(String exhibModDate) {
        this.exhibModDate = exhibModDate;
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


    //file area

    public int getExhibKind() {
        return exhibKind;
    }

    public void setExhibKind(int exhibKind) {
        this.exhibKind = exhibKind;
    }

    public int getUsrSeq() {
        return usrSeq;
    }

    public void setUsrSeq(int usrSeq) {
        this.usrSeq = usrSeq;
    }



    public String getExhibTitle() {
        return exhibTitle;
    }

    public void setExhibTitle(String exhibTitle) {
        this.exhibTitle = exhibTitle;
    }


    public String getExhibTheme() {
        return exhibTheme;
    }

    public void setExhibTheme(String exhibTheme) {
        this.exhibTheme = exhibTheme;
    }

    public String getExhibSimpleExp() {
        return exhibSimpleExp;
    }

    public void setExhibSimpleExp(String exhibSimpleExp) {
        this.exhibSimpleExp = exhibSimpleExp;
    }

    public String getExhibDetailExp() {
        return exhibDetailExp;
    }

    public void setExhibDetailExp(String exhibDetailExp) {
        this.exhibDetailExp = exhibDetailExp;
    }

    public String getExhibStartDate() {
        return exhibStartDate;
    }

    public void setExhibStartDate(String exhibStartDate) {
        this.exhibStartDate = exhibStartDate;
    }

    public String getExhibEndDate() {
        return exhibEndDate;
    }

    public void setExhibEndDate(String exhibEndDate) {
        this.exhibEndDate = exhibEndDate;
    }

    public String getExhibLocation() {
        return exhibLocation;
    }

    public void setExhibLocation(String exhibLocation) {
        this.exhibLocation = exhibLocation;
    }

    public String getExhibUri() {
        return exhibUri;
    }

    public void setExhibUri(String exhibUri) {
        this.exhibUri = exhibUri;
    }

    @Override
    public String toString() {
        return "ExhibitRegisterVO{" +
                "exhibTitle='" + exhibTitle + '\'' +
                ", exhibKind=" + exhibKind +
                ", exhibTheme='" + exhibTheme + '\'' +
                ", exhibSimpleExp='" + exhibSimpleExp + '\'' +
                ", exhibDetailExp='" + exhibDetailExp + '\'' +
                ", exhibStartDate='" + exhibStartDate + '\'' +
                ", exhibEndDate='" + exhibEndDate + '\'' +
                ", exhibLocation='" + exhibLocation + '\'' +
                ", exhibUri='" + exhibUri + '\'' +
                ", usrSeq=" + usrSeq +
                ", exhibCretDate='" + exhibCretDate + '\'' +
                ", exhibModDate='" + exhibModDate + '\'' +
                ", fileDirName='" + fileDirName + '\'' +
                ", fileName='" + fileName + '\'' +
                ", goodsAllow=" + goodsAllow +
                '}';
    }

    public int getGoodsAllow() {
        return goodsAllow;
    }

    public void setGoodsAllow(int goodsAllow) {
        this.goodsAllow = goodsAllow;
    }

}
