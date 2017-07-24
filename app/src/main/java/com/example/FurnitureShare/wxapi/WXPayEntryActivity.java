package com.example.FurnitureShare.wxapi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.FurnitureShare.eventbus.MessageEvent;
import com.example.FurnitureShare.utils.ToastUtil;
import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import org.greenrobot.eventbus.EventBus;

public class WXPayEntryActivity extends Activity implements IWXAPIEventHandler {

	private static final String TAG = "WXPayEntryActivity";
	
    private IWXAPI api;
	private String way = "wx";
	private String result;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.pay_result);

        
    	api = WXAPIFactory.createWXAPI(this, "wx791e5c84162d83bb");
        api.handleIntent(getIntent(), this);
    }

	@Override
	protected void onNewIntent(Intent intent) {
		super.onNewIntent(intent);
		setIntent(intent);
        api.handleIntent(intent, this);
	}

	@Override
	public void onReq(BaseReq req) {
	}

	@Override
	public void onResp(BaseResp resp) {

		Log.e(TAG, "onResp1: "+resp.errCode );
		Log.e(TAG, "onResp2: "+resp.errStr );


//		if (resp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX) {
//			AlertDialog.Builder builder = new AlertDialog.Builder(this);
//			builder.setTitle("提示");
//			builder.setMessage(getString(R.string.pay_result_callback_msg, String.valueOf(resp.errCode)));
//			builder.show();
//		}

//		if (resp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX){
//			if (resp.errCode == 0){
//				ToastUtil.showToast(getBaseContext(),"支付成功");
//
//			}else {
//				ToastUtil.showToast(getBaseContext(),"支付失败，请重试");
//			}
//			finish();
//		}

		if (resp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX) {
			int code = resp.errCode;
			switch (code) {
				case 0:
					ToastUtil.showToast(getBaseContext(),"支付成功");
//					startActivity(new Intent(WXPayEntryActivity.this,HomeActivity.class));
//					Intent intent = new Intent();
//					intent.putExtra("way",way);
//					intent.putExtra("result",result);
//					intent.setClass(getBaseContext(),PaySuccessfulActivity.class);
//					startActivity(intent);
					EventBus.getDefault().post(new MessageEvent(""));
					finish();
					break;
				case -1:
					ToastUtil.showToast(getBaseContext(),"支付失败");
					finish();
					break;
				case -2:
					ToastUtil.showToast(getBaseContext(),"支付取消");
					finish();
					break;
				default:
					ToastUtil.showToast(getBaseContext(),"支付失败");
					setResult(RESULT_OK);
					finish();
					break;
			}
		}

//		if (resp.errCode == 0) {//支付成功
//			Intent intent = new Intent();
//			intent.setAction("fbPayAction");
//			sendBroadcast(intent);
//			Toast.makeText(getApplicationContext(), "支付成功", Toast.LENGTH_LONG).show();
//			finish();
//		}else if (resp.errCode == -1) {//支付失败
//			Toast.makeText(getApplicationContext(), "支付失败", Toast.LENGTH_LONG).show();
//			finish();
//		}else {//取消
//			Toast.makeText(getApplicationContext(), "支付取消", Toast.LENGTH_LONG).show();
//			finish();
//		}

	}

}