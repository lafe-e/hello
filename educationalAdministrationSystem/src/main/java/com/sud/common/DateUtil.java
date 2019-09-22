package com.sud.common;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

    public static String stampToDate(Long stamp){
        String res;
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-dd");
        Date date = new Date(stamp);
        res = simpleDateFormat.format(date);
        return res;
    }

    public static String stampToCode(Long stamp){
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-dd");
        Date date = new Date(stamp);
        res = simpleDateFormat.format(date);
        return res;
    }
    public static String stampToDate2(Long stamp){
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-dd HH:mm");
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-dd");
        Date date = new Date(stamp);
        res = simpleDateFormat.format(date);
        return res;
    }
}
