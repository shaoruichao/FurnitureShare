package com.example.FurnitureShare.entry;

import java.io.Serializable;

/**
 * 发送验证码
 * Created by src on 2017/7/12.
 */

public class SendCode implements Serializable {

    /**
     * code : 200
     * msg : 短信发送成功！
     * return_id : 480585
     */

    private int code;
    private String msg;
    private int return_id;

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

    public int getReturn_id() {
        return return_id;
    }

    public void setReturn_id(int return_id) {
        this.return_id = return_id;
    }
}
