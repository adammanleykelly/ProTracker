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


    @Before
    public void setUp() throws Exception {

    }


    @Test
    public void formatDate() throws Exception {

        Calendar cal = Calendar.getInstance();
        cal.set(2017, 5-1, 07);
        Date date = cal.getTime();

        assertEquals(FormatDate.formatDate(date), "07/05/2017");
    }

}