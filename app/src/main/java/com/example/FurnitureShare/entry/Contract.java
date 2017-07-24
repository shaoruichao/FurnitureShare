package com.example.FurnitureShare.entry;

import java.io.Serializable;
import java.util.List;

/**
 * 我的合同列表
 * Created by src on 2017/7/20.
 */

public class Contract implements Serializable{


    /**
     * code : 200
     * msg : success
     * data : [{"id":"18","number":"HT20170719688","uid":"10010","gid":"2","title":"牵手","thumb":"http://rutu.9fat.com/uploadfile/2017/0704/20170704105507516.jpg","price":"0.01","leasedays":"2","creattime":"1500457034","status":"1","begintime":"2017/07/19","endtime":"2017/09/19"},{"id":"19","number":"HT20170719991","uid":"10010","gid":"1","title":"女孩","thumb":"http://rutu.9fat.com/uploadfile/2017/0630/20170630021614380.jpg","price":"0.01","leasedays":"1","creattime":"1500457034","status":"1","begintime":"2017/07/19","endtime":"2017/08/19"},{"id":"20","number":"HT20170719540","uid":"10010","gid":"9","title":"现代简约实木茶几北欧小户型客厅沙发茶桌圆桌个性创意家","thumb":"http://rutu.9fat.com/uploadfile/2017/0704/20170704103121158.jpg","price":"0.01","leasedays":"9","creattime":"1500457034","status":"1","begintime":"2017/07/19","endtime":"2018/04/19"},{"id":"21","number":"HT20170719510","uid":"10010","gid":"10800","title":"","thumb":"","price":"0.01","leasedays":"10800","creattime":"1500457034","status":"1","begintime":"2017/07/19","endtime":"2917/07/19"},{"id":"10","number":"HT2017071863797","uid":"10010","gid":"63","title":"风景～绿山","thumb":"http://rutu.9fat.com/uploadfile/2017/0707/20170707110110895.jpg","price":"5016","leasedays":"24","creattime":"1500379075","status":"1","begintime":"2017/07/18","endtime":"2019/07/18"},{"id":"11","number":"HT2017071862267","uid":"10010","gid":"62","title":"徐悲鸿的马","thumb":"http://rutu.9fat.com/uploadfile/2017/0707/20170707105907163.jpg","price":"5004","leasedays":"6","creattime":"1500379075","status":"1","begintime":"2017/07/18","endtime":"2018/01/18"},{"id":"12","number":"HT2017071859929","uid":"10010","gid":"59","title":"命理十二生肖羊","thumb":"http://rutu.9fat.com/uploadfile/2017/0707/20170707105215161.jpg","price":"750","leasedays":"6","creattime":"1500379075","status":"1","begintime":"2017/07/18","endtime":"2018/01/18"},{"id":"8","number":"HT2017071849714","uid":"10010","gid":"49","title":"简约现代布艺沙发小户型北欧客厅家具可拆洗三人转角沙发","thumb":"http://rutu.9fat.com/uploadfile/2017/0707/20170707100249928.jpg","price":"3456","leasedays":"6","creattime":"1500377406","status":"1","begintime":"2017/07/18","endtime":"2018/01/18"},{"id":"9","number":"HT2017071845776","uid":"10010","gid":"45","title":"双人床真皮床主卧1.8米北欧现代简约婚床1.5米储物床皮艺床","thumb":"http://rutu.9fat.com/uploadfile/2017/0706/20170706095436375.jpg","price":"10128","leasedays":"24","creattime":"1500377406","status":"1","begintime":"2017/07/18","endtime":"2019/07/18"}]
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
