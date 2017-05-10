package ie.cit.architect.protracker.helpers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by brian on 26/04/17.
 */
public class DateHelper {

    public static String formatDate(Date date) {

        String inputPattern = "E MMM dd HH:mm:ss z yyyy";
        String outputPattern = "dd/MM/yyyy";
        String dateFormatted = "";
        Date currentDate;
        try {

            String d = String.valueOf(date);
            SimpleDateFormat sdf = new SimpleDateFormat(inputPattern, Locale.ENGLISH);
            currentDate = sdf.parse(d);

            SimpleDateFormat sdf2 = new SimpleDateFormat(outputPattern);
            dateFormatted = sdf2.format(currentDate);

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return dateFormatted;

    }
}
