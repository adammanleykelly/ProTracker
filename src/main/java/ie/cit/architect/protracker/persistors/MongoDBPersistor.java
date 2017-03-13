package ie.cit.architect.protracker.persistors;

import com.mongodb.*;
import ie.cit.architect.protracker.model.Project;
import ie.cit.architect.protracker.model.User;
import ie.cit.architect.protracker.model.UserList;

import java.net.UnknownHostException;
import java.util.ArrayList;

/**
 * Created by brian on 13/03/17.
 */
public class MongoDBPersistor implements IPersistor {

    private MongoClient mongoClientConn;
    private DBCollection table;
    private DB db;

    private static final String DB_LOCAL_HOST = "localhost";
    private static final String DB_NAME = "protracker";
    private static final int DB_PORT = 27017;


    public MongoDBPersistor() {

        try {

            mongoClientConn = new MongoClient(DB_LOCAL_HOST, DB_PORT);

            if(mongoClientConn != null) {
                System.out.println("Connected to MongoDB!");
            } else {
                System.out.println("Connection to MongoDB Failed!");
            }

            //Get Database
            // if database doesn't exist, mongoDB will create it for you
            db = mongoClientConn.getDB(DB_NAME);

            //Get Collection / Table from 'testdb'
            //if collection doesn't exist, mongoDB will create it for you
            table = db.getCollection("users");


        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (MongoException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void writeUsers(UserList users) {

        try {
            for (User currUser : users.getUsers()) {
                BasicDBObject document = new BasicDBObject();
                //key value pair
                document.put("email", currUser.getEmailAddress());
                document.put("password", currUser.getPassword());
                table.insert(document);
            }

        }catch (MongoException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void writeProjects(ArrayList<Project> projects) {

    }

    @Override
    public User selectRecords() {
        return null;
    }
}
