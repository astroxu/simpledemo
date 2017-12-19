package hbidemo.core.test3.impl;

import java.text.SimpleDateFormat;
import java.util.Date;

public class FileUtils {
    protected FileUtils() {
    }
    public static String formatYMD(Date date) {
        SimpleDateFormat YMD = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
        return YMD.format(date);
    }
}