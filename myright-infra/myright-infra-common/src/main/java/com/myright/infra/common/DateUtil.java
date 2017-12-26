
package com.myright.infra.common;


import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

    public static String formatDate(){
        SimpleDateFormat dateFormater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormater.format(new Date());
    }


}
