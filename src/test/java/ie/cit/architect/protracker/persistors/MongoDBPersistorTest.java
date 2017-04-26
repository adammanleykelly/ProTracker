package ie.cit.architect.protracker.persistors;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.MongoException;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import ie.cit.architect.protracker.model.IUser;
import ie.cit.architect.protracker.model.Project;
import ie.cit.architect.protracker.model.User;
import ie.cit.architect.protracker.model.UserList;
import org.bson.Document;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;
import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsNot.not;


/**
 * Created by brian on 11/04/17.
 */
public class MongoDBPersistorTest {

    private MongoCollection collectionUsers, collectionProjects;
    private MongoDatabase database;

    private static String DB_MONGO_PASS = "topdog12";
    private static String DB_MONGO_USER = "developmentuser";
    private static String DB_MONGO_IP = "ec2-54-202-69-181.us-west-2.compute.amazonaws.com";
    private static String DB_NAME = "protrackerdev";

    private MongoDBPersistor mongoDBPersistor;
    private User mUser;


    @Before
    public void setUp() throws Exception {

        mongoDBPersistor = new MongoDBPersistor();

        // local database
//        MongoClient mongoClientConn = new MongoClient("localhost", 27017);
//        database = mongoClientConn.getDatabase("mongotest");

        try {
            // remote database
            String mongoURI = "mongodb://" + DB_MONGO_USER + ":" + DB_MONGO_PASS + "@" +
                    DB_MONGO_IP + "/" + DB_NAME;

            MongoClient mongoClientConn = new MongoClient(new MongoClientURI(mongoURI));

            database = mongoClientConn.getDatabase(DB_NAME);

            collectionUsers = database.getCollection("users");

            collectionProjects = database.getCollection("projects");

        } catch (MongoException e) {
            e.printStackTrace();
        }

    }


    @Test
    public void writeOneUser() throws Exception {

        // Given
        IUser user1 = new User("pete@email.com", "passwd");
        Document document = new Document();
        document.put("email", user1.getEmailAddress());
        document.put("password", user1.getPassword());

        // When
        collectionUsers.insertOne(document);

        // Then
        assertThat(collectionUsers.count(), is(1L));

        collectionUsers.deleteOne(eq("email", "pete@email.com"));


    }


    @Test
    public void writeManyUsers() throws Exception {

        // Given
        User user1 = new User("frank@email.com", "passwd");
        Document document1 = new Document();
        document1.put("_id", 11);
        document1.put("email", user1.getEmailAddress());
        document1.put("password", user1.getPassword());

        User user2 = new User("grace@email.com", "mpass");
        Document document2 = new Document();
        document2.put("_id", 22);
        document2.put("email", user2.getEmailAddress());
        document2.put("password", user2.getPassword());


        List<Document> docList = new ArrayList<>();
        docList.add(document1);
        docList.add(document2);

        // When
        collectionUsers.insertMany(docList);

        // Then
        assertThat(collectionUsers.count(), is(2L));


        // Given
        UserList userList = new UserList();
        userList.add(user1);
        userList.add(user2);

        // When
        mongoDBPersistor.writeUsers(userList);

        mUser = mongoDBPersistor.getDbUser(user1);

        // Then
        assertTrue("Expected not null ", mUser != null);
        assertEquals("frank@email.com", mUser.getEmailAddress());
        assertEquals("passwd", mUser.getPassword());


        // clean up
        collectionUsers.deleteOne(eq("email", "frank@email.com"));
        collectionUsers.deleteOne(eq("email", "grace@email.com"));


    }


    @Test
    public void updateProject() throws Exception {

        // Given
        String currProjectName = "House";
        String newProjectName = "Apartment";
        String updatedProjectName = null;

        /** the project's object name is first set to "House" **/
        Project project = new Project(currProjectName);

        Document document = new Document();
        document.put("name", project.getName());

        collectionProjects.insertOne(document);


        // When  (testing the implementation)
        collectionProjects.updateOne(eq("name", currProjectName),
                new Document("$set", new Document("name", newProjectName)));



        // find the documents updated value and pass is to the String newProjectName
        FindIterable<Document> databaseRecords = database.getCollection("projects").find();
        MongoCursor<Document> cursor = databaseRecords.iterator();

        try {
            updatedProjectName = cursor.next().getString("name");
            project.setName(updatedProjectName);
        } finally {
            cursor.close();
        }


        // Then
        assertThat(updatedProjectName, is("Apartment"));
        assertThat(updatedProjectName, is(not("House")));



        //**********************************************************************

        Project project1;

        // When  (testing the actual method)

        /** the project's object name is then updated to "Apartment" **/
        project1 = mongoDBPersistor.updateProject(currProjectName, newProjectName);

        // Then
        assertThat(project1.getName(), is("Apartment"));


        // cleanup
        collectionProjects.deleteOne(eq("name", updatedProjectName));

    }


}




























