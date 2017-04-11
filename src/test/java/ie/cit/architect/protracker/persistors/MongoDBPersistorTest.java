package ie.cit.architect.protracker.persistors;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import ie.cit.architect.protracker.model.IUser;
import ie.cit.architect.protracker.model.User;
import org.bson.Document;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


/**
 * Created by brian on 11/04/17.
 */
public class MongoDBPersistorTest {

    private MongoCollection collectionUsers, collectionProjects;
    private MongoDatabase database;

    @Before
    public void setUp() throws Exception {
        MongoClient mongoClientConn = new MongoClient("localhost", 27017);
        database = mongoClientConn.getDatabase("mongotest");
        collectionUsers = database.getCollection("users");
        collectionProjects = database.getCollection("projects");
    }


    @Test
    public void writeOneUser() throws Exception {

        IUser user1 = new User("pete@email.com", "passwd");

        Document document = new Document();
        document.put("name", user1.getEmailAddress());
        document.put("password", user1.getPassword());

        collectionUsers.insertOne(document);

        assertThat(collectionUsers.count(), is(1L));

        database.drop();

    }


    @Test
    public void writeManyUsers() throws Exception {

        IUser user1 = new User("frank@email.com", "passwd");
        Document document1 = new Document();
        document1.put("_id", 11);
        document1.put("name", user1.getEmailAddress());
        document1.put("password", user1.getPassword());

        IUser user2 = new User("grace@email.com", "mpass");
        Document document2 = new Document();
        document2.put("_id", 22);
        document2.put("name", user2.getEmailAddress());
        document2.put("password", user2.getPassword());

        List<Document> docList = new ArrayList<>();
        docList.add(document1);
        docList.add(document2);

        collectionUsers.insertMany(docList);

        assertThat(collectionUsers.count(), is(2L));

        database.drop();

    }


    @Test
    public void updateProject() throws Exception {



    }


}




























