package com.example.FurnitureShare.entry;

import java.io.Serializable;

/**
 * 实名认证信息
 * Created by src on 2017/7/20.
 */

public class Ifcer implements Serializable {

    /**
     * code : 200
     * msg : success
     * data : {"id":"4","userid":"10010","name":"嗯呀","id_number":"嗯呀","fp":"http://rutu.9fat.com/uploadfile/userinfo/2/1/150045230644/0_src.jpg","bp":"","hp":"","creattime":"1500452310","edittime":"0","status":"1"}
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
         * id : 4
         * userid : 10010
         * name : 嗯呀
         * id_number : 嗯呀
         * fp : http://rutu.9fat.com/uploadfile/userinfo/2/1/150045230644/0_src.jpg
         * bp :
         * hp :
         * creattime : 1500452310
         * edittime : 0
         * status : 1
         */

        private String id;
        private String userid;
        private String name;
        private String id_number;
        private String fp;
        private String bp;
        private String hp;
        private String creattime;
        private String edittime;
        private String status;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUserid() {
            return userid;
        }

        public void setUserid(String userid) {
            this.userid = userid;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getId_number() {
            return id_number;
        }

        public void setId_number(String id_number) {
            this.id_number = id_number;
        }

        public String getFp() {
            return fp;
        }

        public void setFp(String fp) {
            this.fp = fp;
        }

        public String getBp() {
            return bp;
        }

        public void setBp(String bp) {
            this.bp = bp;
        }

        public String getHp() {
            return hp;
        }

        public void setHp(String hp) {
            this.hp = hp;
        }

        public String getCreattime() {
            return creattime;
        }

        public void setCreattime(String creattime) {
            this.creattime = creattime;
        }

        public String getEdittime() {
            return edittime;
        }

        public void setEdittime(String edittime) {
            this.edittime = edittime;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }
}
