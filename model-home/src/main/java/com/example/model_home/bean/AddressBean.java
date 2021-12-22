package com.example.model_home.bean;

import java.util.List;

/**
 * _ ██╗   ██╗   █████╗   ███╗    ██╗   ██████╗
 * __╚██╗ ██╔╝  ██╔══██╗  ████╗   ██║  ██╔════╝
 * ___╚████╔╝   ███████║  ██╔██╗  ██║  ██║  ███╗
 * ____╚██╔╝    ██╔══██║  ██║╚██╗ ██║  ██║   ██║
 * _____██║     ██║  ██║  ██║ ╚████║   ╚██████╔╝
 * _____╚═╝     ╚═╝  ╚═╝  ╚═╝  ╚═══╝    ╚═════╝
 * Date:2021/12/21
 * Time:11:23
 * author:YangHaoYang
 * Package com.example.model_home.bean
 */
public class AddressBean {
    private Integer code;
    private List<DataBean> data;
    private String msg;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static class DataBean {
        private Integer id;
        private String displaytime;
        private String description;
        private String goodscode;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getDisplaytime() {
            return displaytime;
        }

        public void setDisplaytime(String displaytime) {
            this.displaytime = displaytime;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getGoodscode() {
            return goodscode;
        }

        public void setGoodscode(String goodscode) {
            this.goodscode = goodscode;
        }
    }
}
