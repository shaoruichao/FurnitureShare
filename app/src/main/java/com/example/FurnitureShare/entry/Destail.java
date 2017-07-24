package com.example.FurnitureShare.entry;

import java.io.Serializable;

/**
 * 物品 - 详情
 * Created by src on 2017/7/12.
 */

public class Destail implements Serializable{

    /**
     * code : 200
     * msg : 首页场景信息
     * data : {"id":"94","catid":"10","typeid":"0","title":"无题","style":"","thumb":"http://rutu.9fat.com/uploadfile/2017/0707/20170707011350469.jpg","keywords":"无题","description":"","posids":"1","url":"http://rutu.9fat.com/index.php?m=content&c=index&a=show&catid=10&id=94","listorder":"0","status":"99","sysadd":"1","islink":"0","username":"rutu","inputtime":"1499404397","updatetime":"1499404462","add":"0","lan":"79*47","tp":"0","per":"2000","content":"<img src=\"http://rutu.9fat.com/uploadfile/2017/0707/20170707011350469.jpg\" /><br />\r\n<img src=\"http://rutu.9fat.com/uploadfile/2017/0707/20170707011355392.jpg\" /><br />\r\n<img src=\"http://rutu.9fat.com/uploadfile/2017/0707/20170707011358727.jpg\" /><br />\r\n","readpoint":"0","groupids_view":"","paginationtype":"0","maxcharperpage":"10000","template":"","paytype":"0","allow_comment":"1","relation":"","sp":"0","timg":"{\"0\":{\"url\":\"http:\\/\\/rutu.9fat.com\\/uploadfile\\/2017\\/0707\\/20170707011350469.jpg\",\"alt\":\"\\u5fae\\u4fe1\\u56fe\\u7247_20170607165545\"},\"1\":{\"url\":\"http:\\/\\/rutu.9fat.com\\/uploadfile\\/2017\\/0707\\/20170707011355392.jpg\",\"alt\":\"\\u5fae\\u4fe1\\u56fe\\u7247_20170607165556\"},\"2\":{\"url\":\"http:\\/\\/rutu.9fat.com\\/uploadfile\\/2017\\/0707\\/20170707011358727.jpg\",\"alt\":\"\\u5fae\\u4fe1\\u56fe\\u7247_20170607165559_\\u526f\\u672c\"}}","inclu":"有框","col":"原木色","cai":"原作","maker":"马丽"}
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
         * id : 94
         * catid : 10
         * typeid : 0
         * title : 无题
         * style :
         * thumb : http://rutu.9fat.com/uploadfile/2017/0707/20170707011350469.jpg
         * keywords : 无题
         * description :
         * posids : 1
         * url : http://rutu.9fat.com/index.php?m=content&c=index&a=show&catid=10&id=94
         * listorder : 0
         * status : 99
         * sysadd : 1
         * islink : 0
         * username : rutu
         * inputtime : 1499404397
         * updatetime : 1499404462
         * add : 0
         * lan : 79*47
         * tp : 0
         * per : 2000
         * content : <img src="http://rutu.9fat.com/uploadfile/2017/0707/20170707011350469.jpg" /><br />
         <img src="http://rutu.9fat.com/uploadfile/2017/0707/20170707011355392.jpg" /><br />
         <img src="http://rutu.9fat.com/uploadfile/2017/0707/20170707011358727.jpg" /><br />

         * readpoint : 0
         * groupids_view :
         * paginationtype : 0
         * maxcharperpage : 10000
         * template :
         * paytype : 0
         * allow_comment : 1
         * relation :
         * sp : 0
         * timg : {"0":{"url":"http:\/\/rutu.9fat.com\/uploadfile\/2017\/0707\/20170707011350469.jpg","alt":"\u5fae\u4fe1\u56fe\u7247_20170607165545"},"1":{"url":"http:\/\/rutu.9fat.com\/uploadfile\/2017\/0707\/20170707011355392.jpg","alt":"\u5fae\u4fe1\u56fe\u7247_20170607165556"},"2":{"url":"http:\/\/rutu.9fat.com\/uploadfile\/2017\/0707\/20170707011358727.jpg","alt":"\u5fae\u4fe1\u56fe\u7247_20170607165559_\u526f\u672c"}}
         * inclu : 有框
         * col : 原木色
         * cai : 原作
         * maker : 马丽
         */

        private String id;
        private String catid;
        private String typeid;
        private String title;
        private String style;
        private String thumb;
        private String keywords;
        private String description;
        private String posids;
        private String url;
        private String listorder;
        private String status;
        private String sysadd;
        private String islink;
        private String username;
        private String inputtime;
        private String updatetime;
        private String add;
        private String lan;
        private String tp;
        private String per;
        private String content;
        private String readpoint;
        private String groupids_view;
        private String paginationtype;
        private String maxcharperpage;
        private String template;
        private String paytype;
        private String allow_comment;
        private String relation;
        private String sp;
        private String timg;
        private String inclu;
        private String col;
        private String cai;
        private String maker;

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

        public String getTypeid() {
            return typeid;
        }

        public void setTypeid(String typeid) {
            this.typeid = typeid;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
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

        public String getKeywords() {
            return keywords;
        }

        public void setKeywords(String keywords) {
            this.keywords = keywords;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getPosids() {
            return posids;
        }

        public void setPosids(String posids) {
            this.posids = posids;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getListorder() {
            return listorder;
        }

        public void setListorder(String listorder) {
            this.listorder = listorder;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getSysadd() {
            return sysadd;
        }

        public void setSysadd(String sysadd) {
            this.sysadd = sysadd;
        }

        public String getIslink() {
            return islink;
        }

        public void setIslink(String islink) {
            this.islink = islink;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getInputtime() {
            return inputtime;
        }

        public void setInputtime(String inputtime) {
            this.inputtime = inputtime;
        }

        public String getUpdatetime() {
            return updatetime;
        }

        public void setUpdatetime(String updatetime) {
            this.updatetime = updatetime;
        }

        public String getAdd() {
            return add;
        }

        public void setAdd(String add) {
            this.add = add;
        }

        public String getLan() {
            return lan;
        }

        public void setLan(String lan) {
            this.lan = lan;
        }

        public String getTp() {
            return tp;
        }

        public void setTp(String tp) {
            this.tp = tp;
        }

        public String getPer() {
            return per;
        }

        public void setPer(String per) {
            this.per = per;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getReadpoint() {
            return readpoint;
        }

        public void setReadpoint(String readpoint) {
            this.readpoint = readpoint;
        }

        public String getGroupids_view() {
            return groupids_view;
        }

        public void setGroupids_view(String groupids_view) {
            this.groupids_view = groupids_view;
        }

        public String getPaginationtype() {
            return paginationtype;
        }

        public void setPaginationtype(String paginationtype) {
            this.paginationtype = paginationtype;
        }

        public String getMaxcharperpage() {
            return maxcharperpage;
        }

        public void setMaxcharperpage(String maxcharperpage) {
            this.maxcharperpage = maxcharperpage;
        }

        public String getTemplate() {
            return template;
        }

        public void setTemplate(String template) {
            this.template = template;
        }

        public String getPaytype() {
            return paytype;
        }

        public void setPaytype(String paytype) {
            this.paytype = paytype;
        }

        public String getAllow_comment() {
            return allow_comment;
        }

        public void setAllow_comment(String allow_comment) {
            this.allow_comment = allow_comment;
        }

        public String getRelation() {
            return relation;
        }

        public void setRelation(String relation) {
            this.relation = relation;
        }

        public String getSp() {
            return sp;
        }

        public void setSp(String sp) {
            this.sp = sp;
        }

        public String getTimg() {
            return timg;
        }

        public void setTimg(String timg) {
            this.timg = timg;
        }

        public String getInclu() {
            return inclu;
        }

        public void setInclu(String inclu) {
            this.inclu = inclu;
        }

        public String getCol() {
            return col;
        }

        public void setCol(String col) {
            this.col = col;
        }

        public String getCai() {
            return cai;
        }

        public void setCai(String cai) {
            this.cai = cai;
        }

        public String getMaker() {
            return maker;
        }

        public void setMaker(String maker) {
            this.maker = maker;
        }
    }
}
