package com.sujiakeji.merchant.domain;

import org.joda.time.DateTime;

import java.io.Serializable;

public class Merchant implements Serializable {

    //主键
    private Long id;

    //商户编号
    private String merNum;

    //商户全称
    private String merName;

    //商户简称
    private String merShortName;

    //状态
    private String status;

    //商户管理员ID
    private Long adminId;

    //管理员授权函
    private String adminAuthLetter;

    //商户属性（对公/对私）
    private String merAttr;

    //创建时间
    private DateTime createDate;

    //创建人UserID
    private Long createBy;

    //修改时间
    private DateTime updateDate;

    //修改人UserID
    private Long updateBy;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMerNum() {
        return merNum;
    }

    public void setMerNum(String merNum) {
        this.merNum = merNum;
    }

    public String getMerName() {
        return merName;
    }

    public void setMerName(String merName) {
        this.merName = merName;
    }

    public String getMerShortName() {
        return merShortName;
    }

    public void setMerShortName(String merShortName) {
        this.merShortName = merShortName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getAdminId() {
        return adminId;
    }

    public void setAdminId(Long adminId) {
        this.adminId = adminId;
    }

    public String getAdminAuthLetter() {
        return adminAuthLetter;
    }

    public void setAdminAuthLetter(String adminAuthLetter) {
        this.adminAuthLetter = adminAuthLetter;
    }

    public String getMerAttr() {
        return merAttr;
    }

    public void setMerAttr(String merAttr) {
        this.merAttr = merAttr;
    }

    public DateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(DateTime createDate) {
        this.createDate = createDate;
    }

    public Long getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Long createBy) {
        this.createBy = createBy;
    }

    public DateTime getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(DateTime updateDate) {
        this.updateDate = updateDate;
    }

    public Long getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(Long updateBy) {
        this.updateBy = updateBy;
    }

    @Override
    public String toString() {
        return "Merchant{" +
                "id=" + id +
                ", merNum='" + merNum + '\'' +
                ", merName='" + merName + '\'' +
                ", merShortName='" + merShortName + '\'' +
                ", status='" + status + '\'' +
                ", adminId=" + adminId +
                ", adminAuthLetter='" + adminAuthLetter + '\'' +
                ", merAttr='" + merAttr + '\'' +
                ", createDate=" + createDate +
                ", createBy=" + createBy +
                ", updateDate=" + updateDate +
                ", updateBy=" + updateBy +
                '}';
    }
}