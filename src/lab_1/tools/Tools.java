package lab_1.tools;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Tools {
    public static String[] parseInput(String input) {
        return input.split("/");
    }

    public static Date parseDate(String dateStr) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.parse(dateStr);
    }

    public static String formatDate(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(date);
    }
}
