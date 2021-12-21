package com.example.model_home.paymentdb;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.StringJoiner;

/**
 * _ ██╗   ██╗   █████╗   ███╗    ██╗   ██████╗
 * __╚██╗ ██╔╝  ██╔══██╗  ████╗   ██║  ██╔════╝
 * ___╚████╔╝   ███████║  ██╔██╗  ██║  ██║  ███╗
 * ____╚██╔╝    ██╔══██║  ██║╚██╗ ██║  ██║   ██║
 * _____██║     ██║  ██║  ██║ ╚████║   ╚██████╔╝
 * _____╚═╝     ╚═╝  ╚═╝  ╚═╝  ╚═══╝    ╚═════╝
 * Date:2021/12/20
 * Time:8:58
 * author:YangHaoYang
 * Package com.example.library_community.payment
 */
@Entity
public class PaymentBean {
    @PrimaryKey(autoGenerate = true)
    private Long id;
    private String pic;
    private String title;
    private String price;
    private boolean Evaluation;
    private boolean Receipt;

    public PaymentBean(Long id, String pic, String title, String price, boolean evaluation, boolean receipt) {
        this.id = id;
        this.pic = pic;
        this.title = title;
        this.price = price;
        Evaluation = evaluation;
        Receipt = receipt;
    }

    public PaymentBean() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public boolean isEvaluation() {
        return Evaluation;
    }

    public void setEvaluation(boolean evaluation) {
        Evaluation = evaluation;
    }

    public boolean isReceipt() {
        return Receipt;
    }

    public void setReceipt(boolean receipt) {
        Receipt = receipt;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", PaymentBean.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("pic='" + pic + "'")
                .add("title='" + title + "'")
                .add("price='" + price + "'")
                .add("Evaluation=" + Evaluation)
                .add("Receipt=" + Receipt)
                .toString();
    }
}
