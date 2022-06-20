package com.peanart.main.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter@Setter@ToString
public class FileVO {

    private int exhibSeq;
    private String fileUuid;
    private String fileDirName;
    private String contentType;

    public FileVO(String file_uuid,  String contentType) {
        this.fileUuid = file_uuid;
        this.contentType = contentType;
        System.out.println(contentType);
    }

}
