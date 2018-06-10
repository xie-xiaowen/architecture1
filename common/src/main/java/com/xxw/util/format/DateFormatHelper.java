package com.xxw.util.format;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatHelper {

    private DateFormatHelper() { }

    private final static String FORMAT_STR = "yyyy-MM-dd HH:mm:ss";

    public static String longToStr(long time){
        Date date = new Date(time);
        DateFormat df = new SimpleDateFormat(FORMAT_STR);
        return df.format(date);
    }

}
;
