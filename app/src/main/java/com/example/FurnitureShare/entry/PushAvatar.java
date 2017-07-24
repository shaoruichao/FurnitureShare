package com.example.FurnitureShare.entry;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * 上传头像
 * Created by src on 2017/7/14.
 */

public class PushAvatar implements Serializable {

    /**
     * code : 200
     * avatar : http://rutu.9fat.com/phpsso_server/uploadfile/avatar/2/1/10010/src.jpg?_=1500013379
     * htmlavatar : http://rutu.9fat.com/phpsso_server/uploadfile/avatar/2/1/10010/src.jpg
     * thumb : {"180":"http://rutu.9fat.com/phpsso_server/uploadfile/avatar/2/1/10010/180x180.jpg?_=1500013379","90":"http://rutu.9fat.com/phpsso_server/uploadfile/avatar/2/1/10010/90x90.jpg?_=1500013379","45":"http://rutu.9fat.com/phpsso_server/uploadfile/avatar/2/1/10010/45x45.jpg?_=1500013379","30":"http://rutu.9fat.com/phpsso_server/uploadfile/avatar/2/1/10010/30x30.jpg?_=1500013379"}
     */

    private int code;
    private String avatar;
    private String htmlavatar;
    private ThumbBean thumb;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
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

    public ThumbBean getThumb() {
        return thumb;
    }

    public void setThumb(ThumbBean thumb) {
        this.thumb = thumb;
    }

    public static class ThumbBean {
        /**
         * 180 : http://rutu.9fat.com/phpsso_server/uploadfile/avatar/2/1/10010/180x180.jpg?_=1500013379
         * 90 : http://rutu.9fat.com/phpsso_server/uploadfile/avatar/2/1/10010/90x90.jpg?_=1500013379
         * 45 : http://rutu.9fat.com/phpsso_server/uploadfile/avatar/2/1/10010/45x45.jpg?_=1500013379
         * 30 : http://rutu.9fat.com/phpsso_server/uploadfile/avatar/2/1/10010/30x30.jpg?_=1500013379
         */

        @SerializedName("180")
        private String _$180;
        @SerializedName("90")
        private String _$90;
        @SerializedName("45")
        private String _$45;
        @SerializedName("30")
        private String _$30;

        public String get_$180() {
            return _$180;
        }

        public void set_$180(String _$180) {
            this._$180 = _$180;
        }

        public String get_$90() {
            return _$90;
        }

        public void set_$90(String _$90) {
            this._$90 = _$90;
        }

        public String get_$45() {
            return _$45;
        }

        public void set_$45(String _$45) {
            this._$45 = _$45;
        }

        public String get_$30() {
            return _$30;
        }

        public void set_$30(String _$30) {
            this._$30 = _$30;
        }
    }
}
