package ie.cit.architect.protracker;

import ie.cit.architect.protracker.gui.MainApp;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by brian on 06/02/17.
 */
public class MainTest {

   private MainApp mainApp;


    @Before
    public void setUp() throws Exception {
       mainApp = new MainApp();
    }


    @Test
    public void dummyTest() throws Exception {

        assertEquals(4, mainApp.dummyTest());
    }


}