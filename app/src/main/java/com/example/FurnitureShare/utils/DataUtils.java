package com.example.FurnitureShare.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by src on 2016/9/25.
 */
public class DataUtils {
    private static SimpleDateFormat sf = null;

    /* 获取系统时间 格式为："yyyy/MM/dd " */
    public static String getCurrentDate() {
        Date d = new Date();
        sf = new SimpleDateFormat("yyyy年MM月dd日");
        return sf.format(d);
    }

    /* 时间戳转换成字符窜 */
    public static String getDateToString(long time) {
        Date d = new Date(time);
        sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sf.format(d);
    }

    /* 将字符串转为时间戳 */
    public static long getStringToDate(String time) {
        sf = new SimpleDateFormat("yyyy年MM月dd日");
        Date date = new Date();
        try {
            date = sf.parse(time);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return date.getTime();
    }

    public static String timeParse(long duration) {
        String time = "" ;

        long minute = duration / 60000 ;
        long seconds = duration % 60000 ;

        long second = Math.round((float)seconds/1000) ;

        if( minute < 10 ){
            time += "0" ;
        }
        time += minute + "分"+":" ;

        if( second < 10 ){
            time += "0" ;
        }
        time += second + "秒" ;

        return time ;
    }
}
