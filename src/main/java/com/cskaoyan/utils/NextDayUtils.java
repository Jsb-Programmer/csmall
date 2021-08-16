package com.cskaoyan.utils;

import java.util.Calendar;
import java.util.Date;

/**
 * @description:
 * @author: Woo
 * @create: 2021-08-14 23:17
 **/

public class NextDayUtils {
    public static Date getNextDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, +1);//+1今天的时间加一天
        date = calendar.getTime();
        return date;
    }
}
