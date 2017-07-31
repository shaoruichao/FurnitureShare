package com.example.FurnitureShare.entry;

import java.io.Serializable;

/**
 * 认证列表
 * Created by src on 2017/7/28.
 */

public class CreditList implements Serializable{

    /**
     * code : 200
     * msg : success
     * data : {"authenticationstatus":"2","sesamestatus":0}
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
         * authenticationstatus : 2
         * sesamestatus : 0
         */

        private String authenticationstatus;
        private int sesamestatus;

        public String getAuthenticationstatus() {
            return authenticationstatus;
        }

        public void setAuthenticationstatus(String authenticationstatus) {
            this.authenticationstatus = authenticationstatus;
        }

        public int getSesamestatus() {
            return sesamestatus;
        }

        public void setSesamestatus(int sesamestatus) {
            this.sesamestatus = sesamestatus;
        }
    }
}
