package com.example.FurnitureShare.entry;

import java.io.Serializable;

/**
 * 注册
 * Created by src on 2017/7/12.
 */

public class Register implements Serializable {

    /**
     * code : 200
     * data : {"amount":0,"avatar":"","birthday":"","connectid":"","email":"15114781478@9fat.com","encrypt":"9jmYxR","from":"","groupid":2,"htmlavatar":"","lastdate":1499911161,"mobile":"15114781478","modelid":10,"nickname":"s1499911161412","password":"0ecc4e91caee9f0d55482888c3a5d4ea","phpssouid":"10012","point":0,"regdate":1499911161,"regip":"114.244.148.242","sex":0,"siteid":1,"uid":"10012","userid":"10012","username":"15114781478"}
     * msg : 恭喜您15114781478，注册成功！
     * status : 1
     */

    private int code;
    private DataBean data;
    private String msg;
    private int status;

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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public static class DataBean {
        /**
         * amount : 0
         * avatar :
         * birthday :
         * connectid :
         * email : 15114781478@9fat.com
         * encrypt : 9jmYxR
         * from :
         * groupid : 2
         * htmlavatar :
         * lastdate : 1499911161
         * mobile : 15114781478
         * modelid : 10
         * nickname : s1499911161412
         * password : 0ecc4e91caee9f0d55482888c3a5d4ea
         * phpssouid : 10012
         * point : 0
         * regdate : 1499911161
         * regip : 114.244.148.242
         * sex : 0
         * siteid : 1
         * uid : 10012
         * userid : 10012
         * username : 15114781478
         */

        private int amount;
        private String avatar;
        private String birthday;
        private String connectid;
        private String email;
        private String encrypt;
        private String from;
        private int groupid;
        private String htmlavatar;
        private int lastdate;
        private String mobile;
        private int modelid;
        private String nickname;
        private String password;
        private String phpssouid;
        private int point;
        private int regdate;
        private String regip;
        private int sex;
        private int siteid;
        private String uid;
        private String userid;
        private String username;

        public int getAmount() {
            return amount;
        }

        public void setAmount(int amount) {
            this.amount = amount;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public String getBirthday() {
            return birthday;
        }

        public void setBirthday(String birthday) {
            this.birthday = birthday;
        }

        public String getConnectid() {
            return connectid;
        }

        public void setConnectid(String connectid) {
            this.connectid = connectid;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getEncrypt() {
            return encrypt;
        }

        public void setEncrypt(String encrypt) {
            this.encrypt = encrypt;
        }

        public String getFrom() {
            return from;
        }

        public void setFrom(String from) {
            this.from = from;
        }

        public int getGroupid() {
            return groupid;
        }

        public void setGroupid(int groupid) {
            this.groupid = groupid;
        }

        public String getHtmlavatar() {
            return htmlavatar;
        }

        public void setHtmlavatar(String htmlavatar) {
            this.htmlavatar = htmlavatar;
        }

        public int getLastdate() {
            return lastdate;
        }

        public void setLastdate(int lastdate) {
            this.lastdate = lastdate;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public int getModelid() {
            return modelid;
        }

        public void setModelid(int modelid) {
            this.modelid = modelid;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getPhpssouid() {
            return phpssouid;
        }

        public void setPhpssouid(String phpssouid) {
            this.phpssouid = phpssouid;
        }

        public int getPoint() {
            return point;
        }

        public void setPoint(int point) {
            this.point = point;
        }

        public int getRegdate() {
            return regdate;
        }

        public void setRegdate(int regdate) {
            this.regdate = regdate;
        }

        public String getRegip() {
            return regip;
        }

        public void setRegip(String regip) {
            this.regip = regip;
        }

        public int getSex() {
            return sex;
        }

        public void setSex(int sex) {
            this.sex = sex;
        }

        public int getSiteid() {
            return siteid;
        }

        public void setSiteid(int siteid) {
            this.siteid = siteid;
        }

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public String getUserid() {
            return userid;
        }

        public void setUserid(String userid) {
            this.userid = userid;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }
    }
}
