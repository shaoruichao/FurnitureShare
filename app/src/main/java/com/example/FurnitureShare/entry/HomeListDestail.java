package com.example.FurnitureShare.entry;

import java.io.Serializable;
import java.util.List;

/**
 * 首页 详情
 * Created by src on 2017/7/12.
 */

public class HomeListDestail implements Serializable {

    /**
     * code : 200
     * data : [{"catid":"10","id":"94","inputtime":"1499404397","listorder":"94","per":"2000","style":"","thumb":"http://rutu.9fat.com/uploadfile/2017/0707/20170707011350469.jpg","title":"无题","url":"http://rutu.9fat.com/index.php?m=content&c=index&a=show&catid=10&id=94"},{"catid":"10","id":"93","inputtime":"1499404293","listorder":"93","per":"2000","style":"","thumb":"http://rutu.9fat.com/uploadfile/2017/0707/20170707011231493.jpg","title":"无题","url":"http://rutu.9fat.com/index.php?m=content&c=index&a=show&catid=10&id=93"},{"catid":"10","id":"68","inputtime":"1499397436","listorder":"68","per":"2000","style":"","thumb":"http://rutu.9fat.com/uploadfile/2017/0707/20170707111802813.jpg","title":"问花","url":"http://rutu.9fat.com/index.php?m=content&c=index&a=show&catid=10&id=68"},{"catid":"10","id":"66","inputtime":"1499397133","listorder":"66","per":"2000","style":"","thumb":"http://rutu.9fat.com/uploadfile/2017/0707/20170707111238594.jpg","title":"西子湖畔","url":"http://rutu.9fat.com/index.php?m=content&c=index&a=show&catid=10&id=66"}]
     * msg : 场景条目列表
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
         * catid : 10
         * id : 94
         * inputtime : 1499404397
         * listorder : 94
         * per : 2000
         * style :
         * thumb : http://rutu.9fat.com/uploadfile/2017/0707/20170707011350469.jpg
         * title : 无题
         * url : http://rutu.9fat.com/index.php?m=content&c=index&a=show&catid=10&id=94
         */

        private String catid;
        private String id;
        private String inputtime;
        private String listorder;
        private String per;
        private String style;
        private String thumb;
        private String title;
        private String url;

        public String getCatid() {
            return catid;
        }

        public void setCatid(String catid) {
            this.catid = catid;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getInputtime() {
            return inputtime;
        }

        public void setInputtime(String inputtime) {
            this.inputtime = inputtime;
        }

        public String getListorder() {
            return listorder;
        }

        public void setListorder(String listorder) {
            this.listorder = listorder;
        }

        public String getPer() {
            return per;
        }

        public void setPer(String per) {
            this.per = per;
        }

        public String getStyle() {
            return style;
        }

        public void setStyle(String style) {
            this.style = style;
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

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
