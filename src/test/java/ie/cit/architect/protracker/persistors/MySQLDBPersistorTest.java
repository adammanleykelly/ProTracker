// checking that this is breaking my Jenkins build

//package ie.cit.architect.protracker.persistors;
//
//import ie.cit.architect.protracker.model.User;
//import ie.cit.architect.protracker.model.UserList;
//import org.junit.Before;
//import org.junit.Test;
//
//import static junit.framework.TestCase.assertTrue;
//import static org.junit.Assert.assertEquals;
//
///**
// * Created by brian on 05/03/17.
// */
//public class MySQLDBPersistorTest {
//
//    private User dbUser;
//
//    @Before
//    public void setUp() throws Exception {
//        User user1 = new User("joe@moe.com", "mypass");
//        User user2 = new User("someone@hotmail.com", "myotherpass");
//
//        MySQLPersistor dbPersistor = new MySQLPersistor();
//
//        UserList userList = new UserList();
//
//        userList.add(user1);
//        userList.add(user2);
//
//        dbPersistor.writeUsers(userList);
//        dbUser = dbPersistor.getDbUser(user1);
//    }
//
//    @Test
//    public void writeUsers() throws Exception {
//        assertTrue("Expected not null ", dbUser != null);
//        assertEquals("joe@moe.com", dbUser.getEmailAddress());
//        assertEquals("mypass", dbUser.getPassword());
//    }
//
//}