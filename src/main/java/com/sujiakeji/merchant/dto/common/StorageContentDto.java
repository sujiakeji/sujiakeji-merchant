package com.sujiakeji.merchant.dto.common;

public class StorageContentDto {

    private String contentType;
    private int length;
    private byte[] data;

    public StorageContentDto() {
    }

    public StorageContentDto(String contentType, int length, byte[] data) {
        this.contentType = contentType;
        this.length = length;
        this.data = data;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }
}
