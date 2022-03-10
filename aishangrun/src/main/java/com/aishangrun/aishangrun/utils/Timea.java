package com.aishangrun.aishangrun.utils;
/*
* cxy
*
*
* */
public class Timea {
     /*小时*/
    private Integer hh;
    private Integer mm;
    private Integer ss;

    public Integer getHh(String hh) {
        return this.hh;
    }

    public void setHh(Integer hh) {
        this.hh = hh;
    }

    public Integer getMm(String mm) {
        return this.mm;
    }

    public void setMm(Integer mm) {
        this.mm = mm;
    }

    public Integer getSs(String ss) {
        return this.ss;
    }

    public void setSs(Integer ss) {
        this.ss = ss;
    }

    @Override
    public String toString() {
        return "Timea{" +
                "hh=" + hh +
                ", mm=" + mm +
                ", ss=" + ss +
                '}';
    }
}
