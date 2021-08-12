package com.cskaoyan.utils;

/**
 * @author YinYuan
 * @create 2021/7/10 19:49
 */
public class StringUtil {
    /**
    * @Author YinYuan
    * @Description 判断字符串是否为空
    * @Date 19:49 2021/7/10
    * @Param [s]
    * @return boolean
    **/
    public static boolean isEmpty(String s) {
        if (s == null || s.trim ().equals ("")) {
            return true;
        }
        return false;
    }


    /**
     * @Author YinYuan
     * @Description 判断字符串数组中是否含有空字符串
     * @Date 19:49 2021/7/10
     * @Param [s]
     * @return boolean
     **/
    public static boolean hasEmptyString(String[] strings) {

        for (String s : strings) {
            if (s == null || s.trim ().equals ("")) {
                return true;
            }
        }
        return false;
    }
}
