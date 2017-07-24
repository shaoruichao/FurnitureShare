package com.example.FurnitureShare.entry;

import java.io.Serializable;
import java.util.List;

/**
 * Created by src on 2017/5/24.
 */

public class AddressList implements Serializable {


    /**
     * code : 200
     * msg : success
     * data : [{"id":"2","uid":"10010","province":"山东","city":"菏泽","country":"巨野","detail":"南关居委会","linkman":"马振忠","telnumber":"18601265245","status":"2"}]
     */

    private int code;
    private String msg;
    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean implements Serializable{
        /**
         * id : 2
         * uid : 10010
         * province : 山东
         * city : 菏泽
         * country : 巨野
         * detail : 南关居委会
         * linkman : 马振忠
         * telnumber : 18601265245
         * status : 2
         */

        private String id;
        private String uid;
        private String province;
        private String city;
        private String country;
        private String detail;
        private String linkman;
        private String telnumber;
        private String status;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public String getDetail() {
            return detail;
        }

        public void setDetail(String detail) {
            this.detail = detail;
        }

        public String getLinkman() {
            return linkman;
        }

        public void setLinkman(String linkman) {
            this.linkman = linkman;
        }

        public String getTelnumber() {
            return telnumber;
        }

        public void setTelnumber(String telnumber) {
            this.telnumber = telnumber;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }
}
