package com.sujiakeji.merchant.dto.common;

public class PageRequestDto {

    private Integer page;

    private Integer size;

    private Integer offset;

    private String order;

    public PageRequestDto(Integer page, Integer size, String order) {
        if (page == null || page < 0) {
            this.page = 0;
        } else {
            this.page = page;
        }
        if (size == null || size < 0) {
            this.size = 10;
        } else if (size > 100) {
            this.size = 100;
        } else {
            this.size = size;
        }
        this.offset = this.page * this.size;
        this.order = order;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    @Override
    public String toString() {
        return "PageRequestDto{" +
                "page=" + page +
                ", size=" + size +
                ", offset=" + offset +
                ", order='" + order + '\'' +
                '}';
    }
}
