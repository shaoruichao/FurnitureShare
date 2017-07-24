package com.example.FurnitureShare.entry;

import java.io.Serializable;

/**
 * 商品价格
 * Created by src on 2017/7/18.
 */

public class LeasePrice implements Serializable {

    /**
     * code : 200
     * msg : success
     * data : {"leaseprice":49380,"foregift":500}
     */

    private int code;
    private String msg;
    private DataBean data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * leaseprice : 49380
         * foregift : 500
         */

        private int leaseprice;
        private int foregift;

        public int getLeaseprice() {
            return leaseprice;
        }

        public void setLeaseprice(int leaseprice) {
            this.leaseprice = leaseprice;
        }

        public int getForegift() {
            return foregift;
        }

        public void setForegift(int foregift) {
            this.foregift = foregift;
        }
    }
}
