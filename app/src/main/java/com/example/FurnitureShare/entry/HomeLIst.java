package com.example.FurnitureShare.entry;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 首页列表
 * Created by src on 2017/7/10.
 */

public class HomeLIst implements Serializable {


    /**
     * code : 200
     * msg : 首页场景信息
     * data : [{"posid":"18","modelid":"12","catid":"6","name":"民居公寓","des":"幸福生活　如图打造","maxnum":"20","extention":"{per}","listorder":"0","siteid":"1","thumb":"http://rutu.9fat.com/uploadfile/2017/0706/20170706062936492.jpg","data":[{"per":"79000","title":"樱花盛开","description":"樱花","thumb":"http://rutu.9fat.com/uploadfile/2017/0704/20170704112232193.jpg","inputtime":"1499138350","style":"","url":"http://rutu.9fat.com/index.php?m=content&amp;c=index&amp;a=show&amp;catid=8&amp;id=5","id":"5","catid":"8","listorder":"5"},{"per":"79000","title":"云","thumb":"http://rutu.9fat.com/uploadfile/2017/0704/20170704111603890.jpg","inputtime":"1499138114","style":"","url":"http://rutu.9fat.com/index.php?m=content&amp;c=index&amp;a=show&amp;catid=8&amp;id=4","id":"4","catid":"8","listorder":"4","description":""},{"per":"48000","title":"牵手","thumb":"http://rutu.9fat.com/uploadfile/2017/0704/20170704105507516.jpg","inputtime":"1499136791","style":"","url":"http://rutu.9fat.com/index.php?m=content&amp;c=index&amp;a=show&amp;catid=8&amp;id=2","id":"2","catid":"8","listorder":"2","description":""},{"per":"5000","title":"女孩","description":"或用枯叶灰杏红基、若花样百出工","thumb":"http://rutu.9fat.com/uploadfile/2017/0630/20170630021614380.jpg","inputtime":"1498803103","style":"","url":"http://rutu.9fat.com/index.php?m=content&amp;c=index&amp;a=show&amp;catid=8&amp;id=1","id":"1","catid":"8","listorder":"1"}]},{"posid":"19","modelid":"12","catid":"6","name":"快捷酒店","des":"温馨如家　快乐出行","maxnum":"20","extention":"{per}","listorder":"0","siteid":"1","thumb":"http://rutu.9fat.com/uploadfile/2017/0706/20170706042617690.jpg","data":[{"per":"2000","title":"无题","thumb":"http://rutu.9fat.com/uploadfile/2017/0707/20170707011350469.jpg","inputtime":"1499404397","style":"","url":"http://rutu.9fat.com/index.php?m=content&amp;c=index&amp;a=show&amp;catid=10&amp;id=94","id":"94","catid":"10","listorder":"94","description":""},{"per":"2000","title":"无题","thumb":"http://rutu.9fat.com/uploadfile/2017/0707/20170707011231493.jpg","inputtime":"1499404293","style":"","url":"http://rutu.9fat.com/index.php?m=content&amp;c=index&amp;a=show&amp;catid=10&amp;id=93","id":"93","catid":"10","listorder":"93","description":""},{"per":"2000","title":"问花","thumb":"http://rutu.9fat.com/uploadfile/2017/0707/20170707111802813.jpg","inputtime":"1499397436","style":"","url":"http://rutu.9fat.com/index.php?m=content&amp;c=index&amp;a=show&amp;catid=10&amp;id=68","id":"68","catid":"10","listorder":"68","description":""},{"per":"2000","title":"西子湖畔","thumb":"http://rutu.9fat.com/uploadfile/2017/0707/20170707111238594.jpg","inputtime":"1499397133","style":"","url":"http://rutu.9fat.com/index.php?m=content&amp;c=index&amp;a=show&amp;catid=10&amp;id=66","id":"66","catid":"10","listorder":"66","description":""}]}]
     */

    private int code;
    private String msg;
    private ArrayList<DataBeanX> data;

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

    public ArrayList<DataBeanX> getData() {
        return data;
    }

    public void setData(ArrayList<DataBeanX> data) {
        this.data = data;
    }

    public static class DataBeanX {
        /**
         * posid : 18
         * modelid : 12
         * catid : 6
         * name : 民居公寓
         * des : 幸福生活　如图打造
         * maxnum : 20
         * extention : {per}
         * listorder : 0
         * siteid : 1
         * thumb : http://rutu.9fat.com/uploadfile/2017/0706/20170706062936492.jpg
         * data : [{"per":"79000","title":"樱花盛开","description":"樱花","thumb":"http://rutu.9fat.com/uploadfile/2017/0704/20170704112232193.jpg","inputtime":"1499138350","style":"","url":"http://rutu.9fat.com/index.php?m=content&amp;c=index&amp;a=show&amp;catid=8&amp;id=5","id":"5","catid":"8","listorder":"5"},{"per":"79000","title":"云","thumb":"http://rutu.9fat.com/uploadfile/2017/0704/20170704111603890.jpg","inputtime":"1499138114","style":"","url":"http://rutu.9fat.com/index.php?m=content&amp;c=index&amp;a=show&amp;catid=8&amp;id=4","id":"4","catid":"8","listorder":"4","description":""},{"per":"48000","title":"牵手","thumb":"http://rutu.9fat.com/uploadfile/2017/0704/20170704105507516.jpg","inputtime":"1499136791","style":"","url":"http://rutu.9fat.com/index.php?m=content&amp;c=index&amp;a=show&amp;catid=8&amp;id=2","id":"2","catid":"8","listorder":"2","description":""},{"per":"5000","title":"女孩","description":"或用枯叶灰杏红基、若花样百出工","thumb":"http://rutu.9fat.com/uploadfile/2017/0630/20170630021614380.jpg","inputtime":"1498803103","style":"","url":"http://rutu.9fat.com/index.php?m=content&amp;c=index&amp;a=show&amp;catid=8&amp;id=1","id":"1","catid":"8","listorder":"1"}]
         */

        private String posid;
        private String modelid;
        private String catid;
        private String name;
        private String des;
        private String maxnum;
        private String extention;
        private String listorder;
        private String siteid;
        private String thumb;
        private List<DataBean> data;

        public String getPosid() {
            return posid;
        }

        public void setPosid(String posid) {
            this.posid = posid;
        }

        public String getModelid() {
            return modelid;
        }

        public void setModelid(String modelid) {
            this.modelid = modelid;
        }

        public String getCatid() {
            return catid;
        }

        public void setCatid(String catid) {
            this.catid = catid;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDes() {
            return des;
        }

        public void setDes(String des) {
            this.des = des;
        }

        public String getMaxnum() {
            return maxnum;
        }

        public void setMaxnum(String maxnum) {
            this.maxnum = maxnum;
        }

        public String getExtention() {
            return extention;
        }

        public void setExtention(String extention) {
            this.extention = extention;
        }

        public String getListorder() {
            return listorder;
        }

        public void setListorder(String listorder) {
            this.listorder = listorder;
        }

        public String getSiteid() {
            return siteid;
        }

        public void setSiteid(String siteid) {
            this.siteid = siteid;
        }

        public String getThumb() {
            return thumb;
        }

        public void setThumb(String thumb) {
            this.thumb = thumb;
        }

        public List<DataBean> getData() {
            return data;
        }

        public void setData(List<DataBean> data) {
            this.data = data;
        }

        public static class DataBean {
            /**
             * per : 79000
             * title : 樱花盛开
             * description : 樱花
             * thumb : http://rutu.9fat.com/uploadfile/2017/0704/20170704112232193.jpg
             * inputtime : 1499138350
             * style :
             * url : http://rutu.9fat.com/index.php?m=content&amp;c=index&amp;a=show&amp;catid=8&amp;id=5
             * id : 5
             * catid : 8
             * listorder : 5
             */

            private String per;
            private String title;
            private String description;
            private String thumb;
            private String inputtime;
            private String style;
            private String url;
            private String id;
            private String catid;
            private String listorder;

            public String getPer() {
                return per;
            }

            public void setPer(String per) {
                this.per = per;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
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
        }
    }
}
