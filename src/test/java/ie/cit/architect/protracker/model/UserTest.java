package ie.cit.architect.protracker.model;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by brian on 07/02/17.
 */
public class UserTest {

    private User architectUser, clientUser;
    private String validName = "Joe";

    @Before
    public void setUp() throws Exception {

        architectUser = new UserArchitect();
        architectUser.setName("Joe");


    }

    @Test
    public void testValidateName() throws Exception {

        assertThat(architectUser.isNameValid(validName), is(true));

    }

}