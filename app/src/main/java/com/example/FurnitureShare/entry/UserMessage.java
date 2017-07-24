package com.example.FurnitureShare.entry;

import java.io.Serializable;
import java.util.List;

/**
 * 用户信息cookie接口
 * Created by src on 2017/7/13.
 */

public class UserMessage implements Serializable {

    /**
     * status : 1
     * data : {"uid":"10010","userid":"10010","userName":"15111112222","nickname":"s1499856175783","mobile":"15111112222","point":"0","sex":"","age":"","birthday":"","realy":"1","email":"15111112222@9fat.com","avatar":"http://rutu.9fat.com/phpsso_server/uploadfile/avatar/2/1/10010/src.jpg?_=1500522501","htmlavatar":"http://rutu.9fat.com/phpsso_server/uploadfile/avatar/2/1/10010/src.jpg","address":[{"id":"8","uid":"10010","province":"北京市","city":"北京市","country":"东城区","detail":"新中西里","linkman":"蜗蜗","telnumber":"15111112222","status":"2"}]}
     */

    private int status;
    private DataBean data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean implements Serializable{
        /**
         * uid : 10010
         * userid : 10010
         * userName : 15111112222
         * nickname : s1499856175783
         * mobile : 15111112222
         * point : 0
         * sex :
         * age :
         * birthday :
         * realy : 1
         * email : 15111112222@9fat.com
         * avatar : http://rutu.9fat.com/phpsso_server/uploadfile/avatar/2/1/10010/src.jpg?_=1500522501
         * htmlavatar : http://rutu.9fat.com/phpsso_server/uploadfile/avatar/2/1/10010/src.jpg
         * address : [{"id":"8","uid":"10010","province":"北京市","city":"北京市","country":"东城区","detail":"新中西里","linkman":"蜗蜗","telnumber":"15111112222","status":"2"}]
         */

        private String uid;
        private String userid;
        private String userName;
        private String nickname;
        private String mobile;
        private String point;
        private String sex;
        private String age;
        private String birthday;
        private String realy;
        private String email;
        private String avatar;
        private String htmlavatar;
        private List<AddressBean> address;

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

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getPoint() {
            return point;
        }

        public void setPoint(String point) {
            this.point = point;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getAge() {
            return age;
        }

        public void setAge(String age) {
            this.age = age;
        }

        public String getBirthday() {
            return birthday;
        }

        public void setBirthday(String birthday) {
            this.birthday = birthday;
        }

        public String getRealy() {
            return realy;
        }

        public void setRealy(String realy) {
            this.realy = realy;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public String getHtmlavatar() {
            return htmlavatar;
        }

        public void setHtmlavatar(String htmlavatar) {
            this.htmlavatar = htmlavatar;
        }

        public List<AddressBean> getAddress() {
            return address;
        }

        public void setAddress(List<AddressBean> address) {
            this.address = address;
        }

        public static class AddressBean implements Serializable{
            /**
             * id : 8
             * uid : 10010
             * province : 北京市
             * city : 北京市
             * country : 东城区
             * detail : 新中西里
             * linkman : 蜗蜗
             * telnumber : 15111112222
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
}
