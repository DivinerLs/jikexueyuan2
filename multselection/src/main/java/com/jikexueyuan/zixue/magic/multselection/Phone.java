package com.jikexueyuan.zixue.magic.multselection;

/**
 * 作者：Magic on 2017/7/28 17:42
 * 邮箱：bonian1852@163.com
 */

public class Phone {

    //手机的品牌
    private String brand;
    //手机的型号
    private String modle;

    public Phone(String brand, String modle) {
        this.brand = brand;
        this.modle = modle;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setModle(String modle) {
        this.modle = modle;
    }

    public String getBrand() {
        return brand;
    }

    public String getModle() {
        return modle;
    }
}
