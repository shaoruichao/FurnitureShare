package com.example.FurnitureShare.entry;

import java.io.Serializable;

/**
 * 芝麻信用认证信息
 * Created by src on 2017/7/28.
 */

public class Ifsesame implements Serializable {

    /**
     * code : 200
     * data : {"aliname":"800","creattime":"1501236542","edittime":"0","id":"4","score":"2147483647","status":"1","thumb":"http://rutu.9fat.com/uploadfile/sesameinfo/2/1/sesame_src.jpg","userid":"10010"}
     * msg : success
     */

    private int code;
    private DataBean data;
    private String msg;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static class DataBean {
        /**
         * aliname : 800
         * creattime : 1501236542
         * edittime : 0
         * id : 4
         * score : 2147483647
         * status : 1
         * thumb : http://rutu.9fat.com/uploadfile/sesameinfo/2/1/sesame_src.jpg
         * userid : 10010
         */

        private String aliname;
        private String creattime;
        private String edittime;
        private String id;
        private String score;
        private String status;
        private String thumb;
        private String userid;

        public String getAliname() {
            return aliname;
        }

        public void setAliname(String aliname) {
            this.aliname = aliname;
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

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getScore() {
            return score;
        }

        public void setScore(String score) {
            this.score = score;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getThumb() {
            return thumb;
        }

        public void setThumb(String thumb) {
            this.thumb = thumb;
        }

        public String getUserid() {
            return userid;
        }

        public void setUserid(String userid) {
            this.userid = userid;
        }
    }
}
