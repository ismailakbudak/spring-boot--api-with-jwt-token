package report.service.v3.util;

import report.service.v3.exception.InvalidDateFormatException;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
    public static Date parse(Object date) throws InvalidDateFormatException {
        if(date == null)
            return null;

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return format.parse(date.toString());
        } catch (Exception e) {
            e.printStackTrace();
            throw new InvalidDateFormatException("invalid date format");
        }
    }
}
