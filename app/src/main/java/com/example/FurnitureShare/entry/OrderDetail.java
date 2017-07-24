package com.example.FurnitureShare.entry;

import java.io.Serializable;
import java.util.List;

/**
 * 订单详情
 * Created by src on 2017/7/14.
 */

public class OrderDetail implements Serializable {

    /**
     * code : 200
     * msg : success
     * data : {"orderinfo":{"id":"28","number":"RT150001816599622","status":"5","price":"25000","freight":"","payment":"","total_fee":"25000","creattime":"1500018165"},"addressinfo":{"id":"2","uid":"10010","province":"北京市","city":"北京市","country":"东城区","detail":"南关居委会","linkman":"马振忠","telnumber":"18601265245","status":"1"},"shopname":"rutu","goodsinfo":[{"id":"16","title":"北欧简约边几角几小茶几客厅沙发边桌边柜实木创意电话几","thumb":"http://rutu.9fat.com/uploadfile/2017/0705/20170705113618783.jpg","per":"600","count":"2","leasedays":"500","foregift":"9"}]}
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
         * orderinfo : {"id":"28","number":"RT150001816599622","status":"5","price":"25000","freight":"","payment":"","total_fee":"25000","creattime":"1500018165"}
         * addressinfo : {"id":"2","uid":"10010","province":"北京市","city":"北京市","country":"东城区","detail":"南关居委会","linkman":"马振忠","telnumber":"18601265245","status":"1"}
         * shopname : rutu
         * goodsinfo : [{"id":"16","title":"北欧简约边几角几小茶几客厅沙发边桌边柜实木创意电话几","thumb":"http://rutu.9fat.com/uploadfile/2017/0705/20170705113618783.jpg","per":"600","count":"2","leasedays":"500","foregift":"9"}]
         */

        private OrderinfoBean orderinfo;
        private AddressinfoBean addressinfo;
        private String shopname;
        private List<GoodsinfoBean> goodsinfo;

        public OrderinfoBean getOrderinfo() {
            return orderinfo;
        }

        public void setOrderinfo(OrderinfoBean orderinfo) {
            this.orderinfo = orderinfo;
        }

        public AddressinfoBean getAddressinfo() {
            return addressinfo;
        }

        public void setAddressinfo(AddressinfoBean addressinfo) {
            this.addressinfo = addressinfo;
        }

        public String getShopname() {
            return shopname;
        }

        public void setShopname(String shopname) {
            this.shopname = shopname;
        }

        public List<GoodsinfoBean> getGoodsinfo() {
            return goodsinfo;
        }

        public void setGoodsinfo(List<GoodsinfoBean> goodsinfo) {
            this.goodsinfo = goodsinfo;
        }

        public static class OrderinfoBean {
            /**
             * id : 28
             * number : RT150001816599622
             * status : 5
             * price : 25000
             * freight :
             * payment :
             * total_fee : 25000
             * creattime : 1500018165
             */

            private String id;
            private String number;
            private String status;
            private String price;
            private String freight;
            private String payment;
            private String total_fee;
            private String creattime;

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

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public String getFreight() {
                return freight;
            }

            public void setFreight(String freight) {
                this.freight = freight;
            }

            public String getPayment() {
                return payment;
            }

            public void setPayment(String payment) {
                this.payment = payment;
            }

            public String getTotal_fee() {
                return total_fee;
            }

            public void setTotal_fee(String total_fee) {
                this.total_fee = total_fee;
            }

            public String getCreattime() {
                return creattime;
            }

            public void setCreattime(String creattime) {
                this.creattime = creattime;
            }
        }

        public static class AddressinfoBean {
            /**
             * id : 2
             * uid : 10010
             * province : 北京市
             * city : 北京市
             * country : 东城区
             * detail : 南关居委会
             * linkman : 马振忠
             * telnumber : 18601265245
             * status : 1
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

        public static class GoodsinfoBean {
            /**
             * id : 16
             * title : 北欧简约边几角几小茶几客厅沙发边桌边柜实木创意电话几
             * thumb : http://rutu.9fat.com/uploadfile/2017/0705/20170705113618783.jpg
             * per : 600
             * count : 2
             * leasedays : 500
             * foregift : 9
             */

            private String id;
            private String title;
            private String thumb;
            private String per;
            private String count;
            private String leasedays;
            private String foregift;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
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

            public String getPer() {
                return per;
            }

            public void setPer(String per) {
                this.per = per;
            }

            public String getCount() {
                return count;
            }

            public void setCount(String count) {
                this.count = count;
            }

            public String getLeasedays() {
                return leasedays;
            }

            public void setLeasedays(String leasedays) {
                this.leasedays = leasedays;
            }

            public String getForegift() {
                return foregift;
            }

            public void setForegift(String foregift) {
                this.foregift = foregift;
            }
        }
    }
}
