package ie.cit.architect.protracker.persistors;

import ie.cit.architect.protracker.model.User;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

/**
 * Created by brian on 05/03/17.
 */
public class DBPersistorTest {

    private User dbUser;

    @Before
    public void setUp() throws Exception {
        User user1 = new User("joe@moe.com", "mypass");
        User user2 = new User("someone@hotmail.com", "myotherpass");

        DBPersistor dbPersistor = new DBPersistor();

        ArrayList<User> users = new ArrayList<>();
        users.add(user1);
        users.add(user2);

        dbPersistor.writeUsers(users);
        dbUser = dbPersistor.getDbUser(user1);
    }

    @Test
    public void writeUsers() throws Exception {
        assertTrue("Expected not null ", dbUser != null);
        assertEquals("joe@moe.com", dbUser.getEmailAddress());
        assertEquals("mypass", dbUser.getPassword());
    }

}