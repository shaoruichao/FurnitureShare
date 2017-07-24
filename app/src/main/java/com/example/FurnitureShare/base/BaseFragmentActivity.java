package com.example.FurnitureShare.base;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.example.FurnitureShare.app.FSApplication;
import com.example.FurnitureShare.nohttp.CallServer;
import com.example.FurnitureShare.nohttp.HttpListener;
import com.yanzhenjie.nohttp.rest.Request;


/**
 *
 */
public class BaseFragmentActivity extends FragmentActivity {

    public <T> void request(int what, Request<T> request, HttpListener<T> callback, boolean canCancel, boolean isLoading) {
        request.setCancelSign(this);
        CallServer.getInstance().request(what, request, callback, canCancel, isLoading);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FSApplication.instance.addActivity(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        FSApplication.instance.finishActivity(this);
    }
}
