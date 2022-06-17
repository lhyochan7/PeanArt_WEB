package com.peanart.main.vo;

public class FileVO {

    private int exhibSeq;
    private String fileUuid;
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

    public FileVO(String file_uuid, String fileName, String contentType) {
        this.fileUuid = file_uuid;
        this.fileName = fileName;
        this.contentType = contentType;
        System.out.println(contentType);
    }

    @Override
    public String toString() {
        return "FileVO{" +
                "uuid='" + fileUuid + '\'' +
                ", fileName='" + fileName + '\'' +
                ", contentType='" + contentType + '\'' +
                '}';
    }

    public String getFileUuid() {
        return fileUuid;
    }

    public void setfile_Uuid(String uuid) {
        this.fileUuid = uuid;
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
