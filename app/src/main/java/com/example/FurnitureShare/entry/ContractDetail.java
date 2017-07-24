package com.example.FurnitureShare.entry;

import java.io.Serializable;

/**
 * 合同详情
 * Created by src on 2017/7/20.
 */

public class ContractDetail implements Serializable {

    /**
     * code : 200
     * msg : success
     * data : {"id":"18","number":"HT20170719688","uid":"10010","gid":"2","title":"牵手","thumb":"http://rutu.9fat.com/uploadfile/2017/0704/20170704105507516.jpg","price":"0.01","leasedays":"2","creattime":"1500457034","status":"1","begintime":"2017/07/19","endtime":"2017/09/19"}
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
         * id : 18
         * number : HT20170719688
         * uid : 10010
         * gid : 2
         * title : 牵手
         * thumb : http://rutu.9fat.com/uploadfile/2017/0704/20170704105507516.jpg
         * price : 0.01
         * leasedays : 2
         * creattime : 1500457034
         * status : 1
         * begintime : 2017/07/19
         * endtime : 2017/09/19
         */

        private String id;
        private String number;
        private String uid;
        private String gid;
        private String title;
        private String thumb;
        private String price;
        private String leasedays;
        private String creattime;
        private String status;
        private String begintime;
        private String endtime;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getNumber() {
            return number;
        }

        public void setNumber(String number) {
            this.number = number;
        }

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public String getGid() {
            return gid;
        }

        public void setGid(String gid) {
            this.gid = gid;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getThumb() {
            return thumb;
        }

        public void setThumb(String thumb) {
            this.thumb = thumb;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getLeasedays() {
            return leasedays;
        }

        public void setLeasedays(String leasedays) {
            this.leasedays = leasedays;
        }

        public String getCreattime() {
            return creattime;
        }

        public void setCreattime(String creattime) {
            this.creattime = creattime;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getBegintime() {
            return begintime;
        }

        public void setBegintime(String begintime) {
            this.begintime = begintime;
        }

        public String getEndtime() {
            return endtime;
        }

        public void setEndtime(String endtime) {
            this.endtime = endtime;
        }
    }
}
