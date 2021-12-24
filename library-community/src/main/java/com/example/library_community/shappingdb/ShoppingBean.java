package com.example.library_community.shappingdb;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.StringJoiner;

/**
 * _ ██╗   ██╗   █████╗   ███╗    ██╗   ██████╗
 * __╚██╗ ██╔╝  ██╔══██╗  ████╗   ██║  ██╔════╝
 * ___╚████╔╝   ███████║  ██╔██╗  ██║  ██║  ███╗
 * ____╚██╔╝    ██╔══██║  ██║╚██╗ ██║  ██║   ██║
 * _____██║     ██║  ██║  ██║ ╚████║   ╚██████╔╝
 * _____╚═╝     ╚═╝  ╚═╝  ╚═╝  ╚═══╝    ╚═════╝
 * Date:2021/12/15
 * Time:19:24
 * author:YangHaoYang
 * Package com.example.library_community.bean
 */
@Entity
public class ShoppingBean implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private long id;
    private String title;
    private String money;
    private String picUrl;
    private boolean estimate = false;
    private int number =1;

    public ShoppingBean(long id, String title, String money, String picUrl, boolean estimate, int number) {
        this.id = id;
        this.title = title;
        this.money = money;
        this.picUrl = picUrl;
        this.estimate = estimate;
        this.number = number;
    }
    @Ignore
    public ShoppingBean() {
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", ShoppingBean.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("title='" + title + "'")
                .add("money=" + money)
                .add("picUrl='" + picUrl + "'")
                .add("estimate=" + estimate)
                .add("number=" + number)
                .toString();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public boolean isEstimate() {
        return estimate;
    }

    public void setEstimate(boolean estimate) {
        this.estimate = estimate;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
