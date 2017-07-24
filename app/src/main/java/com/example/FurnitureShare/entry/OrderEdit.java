package com.example.FurnitureShare.entry;

import java.io.Serializable;

/**
 * 订单编辑
 * Created by src on 2017/6/17.
 */

public class OrderEdit implements Serializable {


    /**
     * code : 200
     * msg : success
     */

    private int code;
    private String msg;

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
}
