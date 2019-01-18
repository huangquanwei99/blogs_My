package com.aidi.boke.domain;

import java.io.Serializable;

/**
 * Created by huangquanwei on 2019/1/13 14:00
 * 用户文件表
 */
public class ImgBD implements Serializable {


    private Integer id; // '主键',
    private String imgName; //  '图片原始名称',
    private String imgAddress; //    '图片存储地址',
    private String username; //   '图片属于哪个用户的',
    private Integer sort; //    '图片排序',
    private Integer imgClass; // '图片分类',
    private Integer state; //状态 1有效 0无效

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public ImgBD(Integer id, String imgName, String imgAddress, String username, Integer sort, Integer imgClass , Integer state) {
        this.id = id;
        this.imgName = imgName;
        this.imgAddress = imgAddress;
        this.username = username;
        this.sort = sort;
        this.imgClass = imgClass;
        this.state = state;
    }

    public ImgBD() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImgName() {
        return imgName;
    }

    public void setImgName(String imgName) {
        this.imgName = imgName;
    }

    public String getImgAddress() {
        return imgAddress;
    }

    public void setImgAddress(String imgAddress) {
        this.imgAddress = imgAddress;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Integer getImgClass() {
        return imgClass;
    }

    public void setImgClass(Integer imgClass) {
        this.imgClass = imgClass;
    }
}
