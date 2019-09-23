package service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class DateUtil {

    private String dateFormatOutput = "yyyy-MM-dd HH:mm:ss";

    public String getFormattedDate(Date date) {
        DateFormat df = new SimpleDateFormat(dateFormatOutput);
        return df.format(date);

    }
    public String getDate() {
        setTimeZone();
        Date date = new Date();
        return getFormattedDate(date);
    }

    public void setTimeZone() {
        String timezone = "UTC";
        TimeZone.setDefault(TimeZone.getTimeZone(timezone));
    }
}
