package com.peanart.main.vo;

public class FileVO {

    private int exhibSeq;
    private String uuid;
    private String fileDirName;
    private String fileName;

    public String getFileDirName() {
        return fileDirName;
    }
    public void setFileDirName(String fileDirName) {
        this.fileDirName = fileDirName;
    }

    private String contentType;



    public FileVO() {}

    public FileVO(String uuid, String fileName, String contentType) {
        this.uuid = uuid;
        this.fileName = fileName;
        this.contentType = contentType;
        System.out.println(contentType);
    }

    @Override
    public String toString() {
        return "FileVO{" +
                "uuid='" + uuid + '\'' +
                ", fileName='" + fileName + '\'' +
                ", contentType='" + contentType + '\'' +
                '}';
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public int getExhibSeq() {
        return exhibSeq;
    }

    public void setExhibSeq(int exhibSeq) {
        this.exhibSeq = exhibSeq;
    }
}
