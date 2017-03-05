package ie.cit.architect.protracker.persistors;

import ie.cit.architect.protracker.model.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

/**
 * Created by brian on 05/03/17.
 */
public class DBPersistorTest {

    private DBPersistor dbPersistor;
    private User user1, user2;
    private ObservableList<User> users = FXCollections.observableArrayList();

    @Before
    public void setUp() throws Exception {
        user1 = new User("joe@moe.com", "mypass");
        user2 = new User("someone@hotmail.com", "myotherpass");
        dbPersistor = new DBPersistor();

        users.addAll(user1, user2);
        dbPersistor.writeUsers(users);
    }

    @Test
    public void writeUsers() throws Exception {
        assertTrue("Expected not null ", dbPersistor != null);
        assertEquals("joe@moe.com", dbPersistor.getDbUserEmail(user1));
        assertEquals("mypass", dbPersistor.getDbUserPass(user1));
    }

}