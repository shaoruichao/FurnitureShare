package com.example.FurnitureShare.entry;

import java.io.Serializable;
import java.util.List;

/**
 * 轮播图
 */

public class Banner implements Serializable{


    /**
     * code : 200
     * msg : 首页轮播图
     * data : [{"title":"甜猫","thumb":"http://rutu.9fat.com/uploadfile/2017/0707/20170707010959535.jpg","inputtime":"1499404142","style":"","url":"http://rutu.9fat.com/index.php?m=content&amp;c=index&amp;a=show&amp;catid=9&amp;id=92","id":"92","catid":"9","listorder":"92"},{"title":"爱马士","thumb":"http://rutu.9fat.com/uploadfile/2017/0707/20170707010815126.jpg","inputtime":"1499404048","style":"","url":"http://rutu.9fat.com/index.php?m=content&amp;c=index&amp;a=show&amp;catid=9&amp;id=91","id":"91","catid":"9","listorder":"91"},{"title":"太湖鹅群","description":"吴冠中（1919&amp;mdash;2010），1919年出生于江苏省宜兴县。1942年毕业于国立艺术专科学校，1946年考取教育部公费留学，1947年到巴黎国立高级美术学","thumb":"http://rutu.9fat.com/uploadfile/2017/0707/20170707010642268.jpg","inputtime":"1499403869","style":"","url":"http://rutu.9fat.com/index.php?m=content&amp;c=index&amp;a=show&amp;catid=9&amp;id=90","id":"90","catid":"9","listorder":"90"},{"title":"家乡","thumb":"http://rutu.9fat.com/uploadfile/2017/0707/20170707010316294.jpg","inputtime":"1499403743","style":"","url":"http://rutu.9fat.com/index.php?m=content&amp;c=index&amp;a=show&amp;catid=9&amp;id=89","id":"89","catid":"9","listorder":"89"},{"title":"鸟","thumb":"http://rutu.9fat.com/uploadfile/2017/0707/20170707010203913.jpg","inputtime":"1499403640","style":"","url":"http://rutu.9fat.com/index.php?m=content&amp;c=index&amp;a=show&amp;catid=9&amp;id=88","id":"88","catid":"9","listorder":"88"},{"title":"瑰丽深秋","description":"刘庆广，字若水,号三千，山东夏津人；师从宋成海，石齐，卢禹舜等诸先生，现为国家一级美术师，山东省美术家协会会员，国家画院卢禹舜工作","thumb":"http://rutu.9fat.com/uploadfile/2017/0707/20170707125848301.jpg","inputtime":"1499403502","style":"","url":"http://rutu.9fat.com/index.php?m=content&amp;c=index&amp;a=show&amp;catid=9&amp;id=87","id":"87","catid":"9","listorder":"87"},{"title":"天马探花","description":"苏云龙以白马、变色龙、太湖石为主要创作对象，并借用赵佶《祥龙石图》的艺术概念，创作出了《天马祥龙系列》作品。他作品中的白马和变色龙","thumb":"http://rutu.9fat.com/uploadfile/2017/0707/20170707125752995.jpg","inputtime":"1499403401","style":"","url":"http://rutu.9fat.com/index.php?m=content&amp;c=index&amp;a=show&amp;catid=9&amp;id=86","id":"86","catid":"9","listorder":"86"}]
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
         * title : 甜猫
         * thumb : http://rutu.9fat.com/uploadfile/2017/0707/20170707010959535.jpg
         * inputtime : 1499404142
         * style :
         * url : http://rutu.9fat.com/index.php?m=content&amp;c=index&amp;a=show&amp;catid=9&amp;id=92
         * id : 92
         * catid : 9
         * listorder : 92
         * description : 吴冠中（1919&amp;mdash;2010），1919年出生于江苏省宜兴县。1942年毕业于国立艺术专科学校，1946年考取教育部公费留学，1947年到巴黎国立高级美术学
         */

        private String title;
        private String thumb;
        private String inputtime;
        private String style;
        private String url;
        private String id;
        private String catid;
        private String listorder;
        private String description;

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

        public String getInputtime() {
            return inputtime;
        }

        public void setInputtime(String inputtime) {
            this.inputtime = inputtime;
        }

        public String getStyle() {
            return style;
        }

        public void setStyle(String style) {
            this.style = style;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getCatid() {
            return catid;
        }

        public void setCatid(String catid) {
            this.catid = catid;
        }

        public String getListorder() {
            return listorder;
        }

        public void setListorder(String listorder) {
            this.listorder = listorder;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }
    }
}
