package com.example.FurnitureShare.entry;

import java.io.Serializable;
import java.util.List;

/**
 * 我的订单-全部
 * Created by src on 2017/7/14.
 */

public class MyOrderAll implements Serializable {


    /**
     * code : 200
     * data : [{"creattime":"1500018165","goodsinfo":[{"count":"2","foregift":"9","gid":"16","leasedays":"500","per":"600","thumb":"http://rutu.9fat.com/uploadfile/2017/0705/20170705113618783.jpg","title":"北欧简约边几角几小茶几客厅沙发边桌边柜实木创意电话几"}],"orderid":"28","ordernumber":"RT150001816599622","orderstatus":"1"},{"creattime":"1500015917","goodsinfo":[{"count":"2","foregift":"9","gid":"16","leasedays":"500","per":"600","thumb":"http://rutu.9fat.com/uploadfile/2017/0705/20170705113618783.jpg","title":"北欧简约边几角几小茶几客厅沙发边桌边柜实木创意电话几"}],"orderid":"20","ordernumber":"RT150001591741744","orderstatus":"1"},{"creattime":"1500008386","goodsinfo":[{"count":"2","foregift":"9","gid":"16","leasedays":"500","per":"600","thumb":"http://rutu.9fat.com/uploadfile/2017/0705/20170705113618783.jpg","title":"北欧简约边几角几小茶几客厅沙发边桌边柜实木创意电话几"},{"count":"2","foregift":"9","gid":"22","leasedays":"500","per":"2800","thumb":"http://rutu.9fat.com/uploadfile/2017/0706/20170706104853883.jpg","title":"椅子现代简约北欧创意休闲椅实木单人沙发椅皮椅书房办公"}],"orderid":"19","ordernumber":"RT150000838637223","orderstatus":"1"},{"creattime":"1500008374","goodsinfo":[{"count":"2","foregift":"9","gid":"16","leasedays":"500","per":"600","thumb":"http://rutu.9fat.com/uploadfile/2017/0705/20170705113618783.jpg","title":"北欧简约边几角几小茶几客厅沙发边桌边柜实木创意电话几"}],"orderid":"18","ordernumber":"RT150000837438918","orderstatus":"1"}]
     * msg : success
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
         * creattime : 1500018165
         * goodsinfo : [{"count":"2","foregift":"9","gid":"16","leasedays":"500","per":"600","thumb":"http://rutu.9fat.com/uploadfile/2017/0705/20170705113618783.jpg","title":"北欧简约边几角几小茶几客厅沙发边桌边柜实木创意电话几"}]
         * orderid : 28
         * ordernumber : RT150001816599622
         * orderstatus : 1
         */

        private String creattime;
        private String orderid;
        private String ordernumber;
        private String orderstatus;
        private List<GoodsinfoBean> goodsinfo;

        public String getCreattime() {
            return creattime;
        }

        public void setCreattime(String creattime) {
            this.creattime = creattime;
        }

        public String getOrderid() {
            return orderid;
        }

        public void setOrderid(String orderid) {
            this.orderid = orderid;
        }

        public String getOrdernumber() {
            return ordernumber;
        }

        public void setOrdernumber(String ordernumber) {
            this.ordernumber = ordernumber;
        }

        public String getOrderstatus() {
            return orderstatus;
        }

        public void setOrderstatus(String orderstatus) {
            this.orderstatus = orderstatus;
        }

        public List<GoodsinfoBean> getGoodsinfo() {
            return goodsinfo;
        }

        public void setGoodsinfo(List<GoodsinfoBean> goodsinfo) {
            this.goodsinfo = goodsinfo;
        }

        public static class GoodsinfoBean implements Serializable{
            /**
             * count : 2
             * foregift : 9
             * gid : 16
             * leasedays : 500
             * per : 600
             * thumb : http://rutu.9fat.com/uploadfile/2017/0705/20170705113618783.jpg
             * title : 北欧简约边几角几小茶几客厅沙发边桌边柜实木创意电话几
             */

            private String count;
            private String foregift;
            private String gid;
            private String leasedays;
            private String per;
            private String thumb;
            private String title;

            public String getCount() {
                return count;
            }

            public void setCount(String count) {
                this.count = count;
            }

            public String getForegift() {
                return foregift;
            }

            public void setForegift(String foregift) {
                this.foregift = foregift;
            }

            public String getGid() {
                return gid;
            }

            public void setGid(String gid) {
                this.gid = gid;
            }

            public String getLeasedays() {
                return leasedays;
            }

            public void setLeasedays(String leasedays) {
                this.leasedays = leasedays;
            }

            public String getPer() {
                return per;
            }

            public void setPer(String per) {
                this.per = per;
            }

            public String getThumb() {
                return thumb;
            }

            public void setThumb(String thumb) {
                this.thumb = thumb;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }
        }
    }
}
