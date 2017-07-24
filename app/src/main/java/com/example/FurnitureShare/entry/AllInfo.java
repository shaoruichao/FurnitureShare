package com.example.FurnitureShare.entry;

import java.io.Serializable;
import java.util.List;

/**
 * Created by src on 2017/7/13.
 */

public class AllInfo implements Serializable {


    /**
     * code : 200
     * data : [{"goodsinfo":[{"count":"1","foregift":"27648","gid":"4","id":"65","leasedays":"6","leaseprice":"19752","pay_way":"","per":"79000","selected":"0","shopname":"rutu","status":"1","subhead":"","thumb":"http://rutu.9fat.com/uploadfile/2017/0704/20170704111603890.jpg","title":"云","uid":"10010"},{"count":"1","foregift":"27648","gid":"5","id":"64","leasedays":"6","leaseprice":"19752","pay_way":"","per":"79000","selected":"0","shopname":"rutu","status":"1","subhead":"樱花","thumb":"http://rutu.9fat.com/uploadfile/2017/0704/20170704112232193.jpg","title":"樱花盛开","uid":"10010"}],"selected":0,"shopname":"rutu"}]
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

    public static class DataBean {
        /**
         * goodsinfo : [{"count":"1","foregift":"27648","gid":"4","id":"65","leasedays":"6","leaseprice":"19752","pay_way":"","per":"79000","selected":"0","shopname":"rutu","status":"1","subhead":"","thumb":"http://rutu.9fat.com/uploadfile/2017/0704/20170704111603890.jpg","title":"云","uid":"10010"},{"count":"1","foregift":"27648","gid":"5","id":"64","leasedays":"6","leaseprice":"19752","pay_way":"","per":"79000","selected":"0","shopname":"rutu","status":"1","subhead":"樱花","thumb":"http://rutu.9fat.com/uploadfile/2017/0704/20170704112232193.jpg","title":"樱花盛开","uid":"10010"}]
         * selected : 0
         * shopname : rutu
         */

        private int selected;
        private String shopname;
        private List<GoodsinfoBean> goodsinfo;

        public int getSelected() {
            return selected;
        }

        public void setSelected(int selected) {
            this.selected = selected;
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

        public static class GoodsinfoBean {
            /**
             * count : 1
             * foregift : 27648
             * gid : 4
             * id : 65
             * leasedays : 6
             * leaseprice : 19752
             * pay_way :
             * per : 79000
             * selected : 0
             * shopname : rutu
             * status : 1
             * subhead :
             * thumb : http://rutu.9fat.com/uploadfile/2017/0704/20170704111603890.jpg
             * title : 云
             * uid : 10010
             */

            private String count;
            private String foregift;
            private String gid;
            private String id;
            private String leasedays;
            private String leaseprice;
            private String pay_way;
            private String per;
            private String selected;
            private String shopname;
            private String status;
            private String subhead;
            private String thumb;
            private String title;
            private String uid;

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

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getLeasedays() {
                return leasedays;
            }

            public void setLeasedays(String leasedays) {
                this.leasedays = leasedays;
            }

            public String getLeaseprice() {
                return leaseprice;
            }

            public void setLeaseprice(String leaseprice) {
                this.leaseprice = leaseprice;
            }

            public String getPay_way() {
                return pay_way;
            }

            public void setPay_way(String pay_way) {
                this.pay_way = pay_way;
            }

            public String getPer() {
                return per;
            }

            public void setPer(String per) {
                this.per = per;
            }

            public String getSelected() {
                return selected;
            }

            public void setSelected(String selected) {
                this.selected = selected;
            }

            public String getShopname() {
                return shopname;
            }

            public void setShopname(String shopname) {
                this.shopname = shopname;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getSubhead() {
                return subhead;
            }

            public void setSubhead(String subhead) {
                this.subhead = subhead;
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

            public String getUid() {
                return uid;
            }

            public void setUid(String uid) {
                this.uid = uid;
            }
        }
    }
}
