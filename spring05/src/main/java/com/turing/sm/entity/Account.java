package com.turing.sm.entity;

import java.io.Serializable;

public class Account implements Serializable {
    private Integer aid;

    private String aname;

    private Integer amoney;

    private static final long serialVersionUID = 1L;

    public Integer getAid() {
        return aid;
    }

    public void setAid(Integer aid) {
        this.aid = aid;
    }

    public String getAname() {
        return aname;
    }

    public void setAname(String aname) {
        this.aname = aname == null ? null : aname.trim();
    }

    public Integer getAmoney() {
        return amoney;
    }

    public void setAmoney(Integer amoney) {
        this.amoney = amoney;
    }
}