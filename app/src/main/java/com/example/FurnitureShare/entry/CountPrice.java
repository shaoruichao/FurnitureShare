package com.example.FurnitureShare.entry;

import java.io.Serializable;

/**
 * Created by src on 2017/7/31.
 */

public class CountPrice implements Serializable {


    /**
     * code : 200
     * data : {"bg":"40127","data":{"rutu":{"bg":"40127","jm":"0","js":"3","sz":"243.75","zj":"41403"}},"hj":"81773.75","jm":"0","js":"3"}
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

    public static class DataBeanX implements Serializable{
        /**
         * bg : 40127
         * data : {"rutu":{"bg":"40127","jm":"0","js":"3","sz":"243.75","zj":"41403"}}
         * hj : 81773.75
         * jm : 0
         * js : 3
         */

        private String bg;
        private DataBean data;
        private String hj;
        private String jm;
        private String js;

        public String getBg() {
            return bg;
        }

        public void setBg(String bg) {
            this.bg = bg;
        }

        public DataBean getData() {
            return data;
        }

        public void setData(DataBean data) {
            this.data = data;
        }

        public String getHj() {
            return hj;
        }

        public void setHj(String hj) {
            this.hj = hj;
        }

        public String getJm() {
            return jm;
        }

        public void setJm(String jm) {
            this.jm = jm;
        }

        public String getJs() {
            return js;
        }

        public void setJs(String js) {
            this.js = js;
        }

        public static class DataBean {
            /**
             * rutu : {"bg":"40127","jm":"0","js":"3","sz":"243.75","zj":"41403"}
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
                 * bg : 40127
                 * jm : 0
                 * js : 3
                 * sz : 243.75
                 * zj : 41403
                 */

                private String bg;
                private String jm;
                private String js;
                private String sz;
                private String zj;

                public String getBg() {
                    return bg;
                }

                public void setBg(String bg) {
                    this.bg = bg;
                }

                public String getJm() {
                    return jm;
                }

                public void setJm(String jm) {
                    this.jm = jm;
                }

                public String getJs() {
                    return js;
                }

                public void setJs(String js) {
                    this.js = js;
                }

                public String getSz() {
                    return sz;
                }

                public void setSz(String sz) {
                    this.sz = sz;
                }

                public String getZj() {
                    return zj;
                }

                public void setZj(String zj) {
                    this.zj = zj;
                }
            }
        }
    }
}
