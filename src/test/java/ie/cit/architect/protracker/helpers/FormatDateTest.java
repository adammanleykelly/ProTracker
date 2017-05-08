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

    private static int YEAR_TWENTY_SEVENTEEN = 2017;
    private static int MONTH_MAY = 5-1;
    private static int DAY_FIRST = 01;

    @Before
    public void setUp() throws Exception {

    }


    @Test
    public void formatDate() throws Exception {

        Calendar cal = Calendar.getInstance();
        cal.set(YEAR_TWENTY_SEVENTEEN, MONTH_MAY, DAY_FIRST);
        Date date = cal.getTime();

        String expected = FormatDate.formatDate(date);
        String actual = "01/05/2017";

        assertEquals(expected, actual);
    }

}