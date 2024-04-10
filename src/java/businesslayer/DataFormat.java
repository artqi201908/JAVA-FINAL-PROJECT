package java.businesslayer;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DataFormat {

    public static Date format(String dateStr) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        try {
            return sdf.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return null;
    }

    // is Expired within the next one week
    public static boolean isExpiredInNextWeek(Date date) {
        if (date == null) {
            return false;
        }

        Date today = new Date();
        long diff = date.getTime() - today.getTime();
        long diffDays = diff / (24 * 60 * 60 * 1000);

        return diffDays < 7 && diffDays > 0;
    }
}
