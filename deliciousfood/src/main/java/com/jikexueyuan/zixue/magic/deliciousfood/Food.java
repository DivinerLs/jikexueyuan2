package com.jikexueyuan.zixue.magic.deliciousfood;

import android.support.annotation.DrawableRes;

/**
 * 作者：Magic on 2017/8/18 15:21
 * 邮箱：bonian1852@163.com
 */

public class Food {
    // TODO: 2017/8/18  private int type的 三个静态常量
    public static final int CHINESE_FOOD = 1;
    public static final int FAST_FOOD = 2;
    public static final int DESSERT_FOOD = 3;
    // TODO: 2017/8/18 psfi 可以快速生成 public static final int,psfs 可以快速生成 public static final String

    /**
     * 美食的名字
     */
    private String name;

    /**
     * 美食的图片资源ID
     * // TODO: 2017/8/18  @DrawableRes 这个注解表明的是 这是一个图片资源
     */
    @DrawableRes
    private int imgResId;

    /**
     * 美食的价格
     */
    private int price;

    /**
     * 美食的类型 需要设置3个静态常量  [在最上面]
     * 可接受的类型:
     * CHINESE_FOOD
     * FAST_FOOD
     * DESSERT_FOOD
     */
    private int type;

    /**
     * 美食的辣度 是否为辣
     */

    private boolean isSpicy;

    /**
     * 美食的评分
     */
    private float rating;

    /**
     * 美食的简介
     */
    private String  description;


    // TODO: 2017/8/18  创建构造方法


    public Food(String name, int imgResId, int price, int type, boolean isSpicy, float rating, String description) {
        this.name = name;
        this.imgResId = imgResId;
        this.price = price;
        this.type = type;
        this.isSpicy = isSpicy;
        this.rating = rating;
        this.description = description;
    }

    // TODO: 2017/8/18 为这几个属性创建get和set方法
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImgResId() {
        return imgResId;
    }

    public void setImgResId(int imgResId) {
        this.imgResId = imgResId;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isSpicy() {
        return isSpicy;
    }

    public void setSpicy(boolean spicy) {
        isSpicy = spicy;
    }

    @Override
    public String toString() {
        return name;
    }
}
