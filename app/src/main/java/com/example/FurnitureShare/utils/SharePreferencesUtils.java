package com.example.FurnitureShare.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Base64;

import com.alibaba.fastjson.JSON;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class SharePreferencesUtils {
	private static final String NAME = "orangeShare";

	public static SharedPreferences getSharedPreferences(Context context) {
		return context.getSharedPreferences(NAME, Context.MODE_APPEND);
	}

	public static void putBean(Context context, String key, Object obj) {
		if (obj instanceof Serializable) {
			try {
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				ObjectOutputStream oos = new ObjectOutputStream(baos);
				oos.writeObject(obj);
				String string64 = new String(Base64.encode(baos.toByteArray(),
						0));
				Editor editor = getSharedPreferences(context).edit();
				editor.putString(key, string64);
				editor.commit();
			} catch (IOException e) {
				e.printStackTrace();
			}

		} else {
			throw new IllegalArgumentException(
					"the obj must implement Serializble");
		}

	}

	public static Object getBean(Context context, String key) {
		Object obj = null;
		try {
			String base64 = getSharedPreferences(context).getString(key, "");
			if (base64.equals("")) {
				return null;
			}
			byte[] base64Bytes = Base64.decode(base64.getBytes(), 1);
			ByteArrayInputStream bais = new ByteArrayInputStream(base64Bytes);
			ObjectInputStream ois = new ObjectInputStream(bais);
			obj = ois.readObject();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return obj;
	}

	public static void seveBeanByFastJson(Context context, String key,
			Object obj) {
		Editor editor = getSharedPreferences(context).edit();
		String objString = JSON.toJSONString(obj);
		editor.putString(key, objString);
		editor.commit();
	}

	public static <T> T getBeanByFastJson(Context context, String key,
			Class<T> clazz) {
		String objString = getSharedPreferences(context).getString(key, "");
		return JSON.parseObject(objString, clazz);
	}
}
