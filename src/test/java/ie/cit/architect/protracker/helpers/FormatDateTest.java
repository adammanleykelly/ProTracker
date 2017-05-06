package ie.cit.architect.protracker.helpers;

import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.assertEquals;

/**
 * Created by brian on 06/05/17.
 */
public class FormatDateTest {

    private FormatDate formatDate;

    @Before
    public void setUp() throws Exception {

    }


    @Test
    public void formatDate() throws Exception {

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.WEEK_OF_MONTH);
        int day = cal.get(Calendar.DAY_OF_WEEK);
        cal.set(year, month, day);

        Date date = cal.getTime();


        assertEquals(FormatDate.formatDate(date), "07/02/2017");
    }

}