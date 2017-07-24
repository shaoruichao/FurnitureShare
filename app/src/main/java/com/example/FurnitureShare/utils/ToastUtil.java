package com.example.FurnitureShare.utils;


import android.content.Context;
import android.widget.Toast;


public class ToastUtil {
	/*
	 * 可以连续弹吐司，不用等上个吐司消失，直接显示
	 */

	private static Toast toast;

	public static void showToast(Context context ,String text) {

		if (toast == null) {

			toast = Toast.makeText( context,text, Toast.LENGTH_SHORT);
		}
		toast.setText(text);
		toast.show();

	}

}
