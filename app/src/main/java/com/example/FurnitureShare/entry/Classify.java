package com.example.FurnitureShare.entry;

import java.io.Serializable;
import java.util.List;

/**
 * 首页精选分类
 */

public class Classify implements Serializable{


    /**
     * code : 200
     * msg : 精品分类
     * data : [{"image":"","catname":"摄影打印","catid":"15"},{"image":"","catname":"实木","catid":"14"},{"image":"","catname":"国画书法","catid":"10"},{"image":"","catname":"原画板画","catid":"9"},{"image":"","catname":"布面油画","catid":"8"}]
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
         * image :
         * catname : 摄影打印
         * catid : 15
         */

        private String image;
        private String catname;
        private String catid;

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getCatname() {
            return catname;
        }

        public void setCatname(String catname) {
            this.catname = catname;
        }

        public String getCatid() {
            return catid;
        }

        public void setCatid(String catid) {
            this.catid = catid;
        }
    }
}
