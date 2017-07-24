package com.example.FurnitureShare.base;

import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.FurnitureShare.app.FSApplication;
import com.example.FurnitureShare.nohttp.CallServer;
import com.example.FurnitureShare.nohttp.HttpListener;
import com.example.FurnitureShare.utils.StaturBar;
import com.yanzhenjie.nohttp.rest.Request;


public class BaseActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);

        // 添加到Activity集合
        FSApplication.instance.addActivity(this);

    }

    /**
     * 发起请求。
     *
     * @param what      what.
     * @param request   请求对象。
     * @param callback  回调函数。
     * @param canCancel 是否能被用户取消。
     * @param isLoading 实现显示加载框。
     * @param <T>       想请求到的数据类型。
     */
    public <T> void request(int what, Request<T> request, HttpListener<T> callback, boolean canCancel, boolean isLoading) {
        request.setCancelSign(this);
        CallServer.getInstance().requests(this, what, request, callback, canCancel, isLoading);

    }

    /**
     * 返回键判断 走finish();
     */
    public void onBackPressed() {
        super.onBackPressed();
        onDestroy();
    }




	/*
	 * private long exitTime = 0;
	 *
	 * @Override public boolean onKeyDown(int keyCode, KeyEvent event) { if
	 * (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() ==
	 * KeyEvent.ACTION_DOWN) { if ((System.currentTimeMillis() - exitTime) >
	 * 2000) { Toast.makeText(getApplicationContext(), "再按一次退出程序",
	 * Toast.LENGTH_SHORT).show(); exitTime = System.currentTimeMillis(); } else
	 * { finish(); System.exit(0); } return true; } return
	 * super.onKeyDown(keyCode, event); }
	 */

    @Override
    protected void onDestroy() {

        super.onDestroy();
        // 结束Activity&从集合中移除
//        TMApplication.instance.finishActivity(this);

    }

    @Override
    protected void onResume() {
        super.onResume();
    }


}
