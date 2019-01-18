package com.aidi.boke.domain;

import java.io.Serializable;

/**
 * Created by huangquanwei on 2019/1/2 16:21
 * 单行条主体类
 */
public class Cephalosome implements Serializable,Comparable<Cephalosome> {


    private Integer id; // 主键id

    private String name; // 名称

    private Integer redundant1; //冗余字段

    private String redundant2; //冗余字段

    private String redundant3; //冗余字段


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getRedundant1() {
        return redundant1;
    }

    public void setRedundant1(Integer redundant1) {
        this.redundant1 = redundant1;
    }

    public String getRedundant2() {
        return redundant2;
    }

    public void setRedundant2(String redundant2) {
        this.redundant2 = redundant2;
    }

    public String getRedundant3() {
        return redundant3;
    }

    public void setRedundant3(String redundant3) {
        this.redundant3 = redundant3;
    }

    // 排序
    // 实现Comparable接口重写compareTo方法进行复杂对象排序
     //return o.getLiveCount() - this.liveCount; // 根据liveCount的值进行降序排序 升序则调换位置
    @Override
    public int compareTo(Cephalosome o) {
        return o.getRedundant1() - this.redundant1 ; // 根据liveCount的值进行降序排序 升序则调换位置
    }
}
