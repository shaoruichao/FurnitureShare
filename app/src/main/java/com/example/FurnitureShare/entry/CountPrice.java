package com.example.FurnitureShare.entry;

import java.io.Serializable;

/**
 * Created by src on 2017/7/31.
 */

public class CountPrice implements Serializable {

    /**
     * code : 200
     * data : {"bg":59244,"data":{"rutu":{"bg":59244,"jm":0,"js":3,"sz":0,"zj":59256}},"hj":118500,"jm":0,"js":3}
     * msg : 订单价格确认接口
     */

    private int code;
    private DataBeanX data;
    private String msg;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public DataBeanX getData() {
        return data;
    }

    public void setData(DataBeanX data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static class DataBeanX {
        /**
         * bg : 59244
         * data : {"rutu":{"bg":59244,"jm":0,"js":3,"sz":0,"zj":59256}}
         * hj : 118500
         * jm : 0
         * js : 3
         */

        private int bg;
        private DataBean data;
        private int hj;
        private int jm;
        private int js;

        public int getBg() {
            return bg;
        }

        public void setBg(int bg) {
            this.bg = bg;
        }

        public DataBean getData() {
            return data;
        }

        public void setData(DataBean data) {
            this.data = data;
        }

        public int getHj() {
            return hj;
        }

        public void setHj(int hj) {
            this.hj = hj;
        }

        public int getJm() {
            return jm;
        }

        public void setJm(int jm) {
            this.jm = jm;
        }

        public int getJs() {
            return js;
        }

        public void setJs(int js) {
            this.js = js;
        }

        public static class DataBean {
            /**
             * rutu : {"bg":59244,"jm":0,"js":3,"sz":0,"zj":59256}
             */

            private RutuBean rutu;

            public RutuBean getRutu() {
                return rutu;
            }

            public void setRutu(RutuBean rutu) {
                this.rutu = rutu;
            }

            public static class RutuBean {
                /**
                 * bg : 59244
                 * jm : 0
                 * js : 3
                 * sz : 0
                 * zj : 59256
                 */

                private int bg;
                private int jm;
                private int js;
                private int sz;
                private int zj;

                public int getBg() {
                    return bg;
                }

                public void setBg(int bg) {
                    this.bg = bg;
                }

                public int getJm() {
                    return jm;
                }

                public void setJm(int jm) {
                    this.jm = jm;
                }

                public int getJs() {
                    return js;
                }

                public void setJs(int js) {
                    this.js = js;
                }

                public int getSz() {
                    return sz;
                }

                public void setSz(int sz) {
                    this.sz = sz;
                }

                public int getZj() {
                    return zj;
                }

                public void setZj(int zj) {
                    this.zj = zj;
                }
            }
        }
    }
}
