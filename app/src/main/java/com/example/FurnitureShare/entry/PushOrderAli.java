package com.example.FurnitureShare.entry;

import java.io.Serializable;

/**
 * 提交订单支付-阿里
 * Created by src on 2017/6/17.
 */

public class PushOrderAli implements Serializable {


    /**
     * code : 200
     * msg : success
     * order : alipay_sdk=alipay-sdk-php-20161101&app_id=2017060207403536&biz_content=%7B%22body%22%3A%22%E6%B5%8B%E8%AF%95%E6%95%B0%E6%8D%AE%22%2C%22subject%22%3A+%22%E6%B5%8B%E8%AF%951123123%22%2C%22out_trade_no%22%3A+%22T1498648624771x1%22%2C%22timeout_express%22%3A+%2230m%22%2C%22total_amount%22%3A+%220.02%22%2C%22product_code%22%3A%22QUICK_MSECURITY_PAY%22%7D&charset=UTF-8&format=json&method=alipay.trade.app.pay&notify_url=http%3A%2F%2Fwww.9fat.com%2Falinotify.php&sign_type=RSA2&timestamp=2017-06-28+19%3A17%3A04&version=1.0&sign=XuTlS8x4vqc1fc%2BmMJMIK2VGItuUFp6%2FRRyYXLk8i3c10i5YdrSg9NF7%2FSyTl0yepMF00DU%2BeNKucopeNsWfF1vuYgh7%2BEqFDxxdf%2BNRgOxTfftgnfndS8zSEFyCkjR5VzjGLK8W07PcqRTNwx1TSLzggVKGO%2F5prGa5wpjg%2FNS9KrduRCiwNcIgSH5bGzW%2BFvDr0tk%2BBhkgX936qlw1pnNRx67Rq24Q1j8Qfa7X0y6TY2vBlTO9yawjtP0iBUpuoMzOatj6oZxm1yJT%2FfUOhY7f6Yfmu1nbIReLsECbnOczyxJD0rydXzXA1TWQzyS1AV3NnnvwAyjyp0YDXwn9AQ%3D%3D
     */

    private int code;
    private String msg;
    private String order;

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

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }
}
