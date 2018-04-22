package com.imooc.o2o.entity;

import java.util.Date;

public class WechatAuth {
    private Long WechatAuthId;
    private String openId;
    private Date createtime;
    private PersonInfo personInfo;

    public PersonInfo getPersonInfo() {
        return personInfo;
    }

    public void setPersonInfo(PersonInfo personInfo) {
        this.personInfo = personInfo;
    }

    public Long getWechatAuthId() {
        return WechatAuthId;
    }

    public void setWechatAuthId(Long wechatAuthId) {
        WechatAuthId = wechatAuthId;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }
}
