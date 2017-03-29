package ie.cit.architect.protracker.model;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by brian on 07/02/17.
 */
public class UserTest {


    private User user;
    private String name = "Joe";
    private String email = "joe@aol.ie";

    @Before
    public void setUp() throws Exception {
        user = new User(email, name);
        user.setName("Joe");
    }

    @Test
    public void testValidateName() throws Exception {
        assertThat(user.isNameValid(), is(true));
    }


    @Test
    public void isEmailValid() throws Exception {

        // test valid emails are true
        ArrayList validEmails = new ArrayList();
        validEmails.add("joe@gmail.com");
        validEmails.add("joe@yahoo.co.uk");
        validEmails.add("joe.peters@aol.com");
        validEmails.add("joe_peters@hotmail.com");
        validEmails.add("username@yahoo.corporate.uk");

        for(int i = 0; i < validEmails.size(); i++) {
            assertThat(user.isEmailValid(
                    validEmails.get(i).toString()), is(true));
        }


        // test invalid emails are false
        ArrayList invalidEmails = new ArrayList();
        invalidEmails.add(".username@yahoo.com");
        invalidEmails.add("username@yahoo.com.");
        invalidEmails.add("username@yahoo..com");
        invalidEmails.add("username@yahoo.c");
        invalidEmails.add("username@yahoo.corporate");
        invalidEmails.add("@yahoo.com");


        for(int i = 0; i < invalidEmails.size(); i++) {
            assertThat(user.isEmailValid(
                    invalidEmails.get(i).toString()), is(false));
        }


    }

}