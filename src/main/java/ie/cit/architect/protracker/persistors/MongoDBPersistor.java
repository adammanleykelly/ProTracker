package ie.cit.architect.protracker.persistors;

import com.mongodb.*;
import ie.cit.architect.protracker.helpers.Credentials;
import ie.cit.architect.protracker.model.IUser;
import ie.cit.architect.protracker.model.Project;
import ie.cit.architect.protracker.model.ProjectList;
import ie.cit.architect.protracker.model.UserList;


/**
 * Created by brian on 13/03/17.
 */
public class MongoDBPersistor implements IPersistor {

    private MongoClient mongoClientConn;
    private DBCollection tableUsers, tableProjects;
    private DB db;




    public MongoDBPersistor() {

        try {

            //local database
//            mongoClientConn = new MongoClient("localhost", 27017);

            // remote database - there is ~2sec delay
           mongoClientConn = new MongoClient( new MongoClientURI(
                   "mongodb://" + Credentials.DB_MONGO_USER + ":" +
                           Credentials.DB_MONGO_PASS + "@" + Credentials.DB_MONGO_IP +"/" + Credentials.DB_NAME));


            if(mongoClientConn != null) {
                System.out.println("Connected to MongoDB!");
            } else {
                System.out.println("Connection to MongoDB Failed!");
            }

            //Get Database
            // if database doesn't exist, mongoDB will create it for you
            db = mongoClientConn.getDB(Credentials.DB_NAME);

            //Get Collection / Table from 'protracker'
            //if collection doesn't exist, mongoDB will create it for you
            tableUsers = db.getCollection("users");

            // create another table
            tableProjects = db.getCollection("projects");


        } catch (MongoException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void writeUsers(UserList users) {

        new Thread(() -> {
            try {
                for (IUser currUser : users.getUsers()) {
                    BasicDBObject document = new BasicDBObject();
                    //key value pair
                    document.put("email", currUser.getEmailAddress());
                    document.put("password", currUser.getPassword());
                    tableUsers.insert(document);
                }

            } catch (MongoException e) {
                e.printStackTrace();
            }
        }).start();

    }

    @Override
    public void writeProjects(ProjectList projects) {

        try {
            for (Project currProject : projects.getProjects()) {
                BasicDBObject document = new BasicDBObject();
                document.put("name", currProject.getName());
                document.put("author", currProject.getAuthor());
                document.put("location", currProject.getLocation());
                document.put("client_name", currProject.getClientName());

                tableProjects.insert(document);
            }
        }catch (MongoException e) {
            e.printStackTrace();
        }

    }


    @Override
    public void displayCurrentProject(ProjectList projects) {
        try {
            for (Project currProject : projects.getProjects()) {
                BasicDBObject searchQuery = new BasicDBObject();
                searchQuery.put("name", currProject.getName());
                searchQuery.put("author", currProject.getAuthor());
                searchQuery.put("location", currProject.getLocation());
                searchQuery.put("client_name", currProject.getClientName());

                DBCursor cursor = tableProjects.find(searchQuery);

                System.out.println("Project:");
                while (cursor.hasNext()){
                    System.out.println(cursor.next());
                }
            }
        }catch (MongoException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void displayCreatedProjects() {

        try {
            DBCursor cursor = tableProjects.find();

            System.out.println("Project:");
            while (cursor.hasNext()) {
                System.out.println(cursor.next());
            }

        } catch (MongoException e) {
            e.printStackTrace();
        }
    }



    // get all documents where email is 'coveneygeorgia@hotmail.com'
    @Override
    public void selectRecords() {
        BasicDBObject whereQuery = new BasicDBObject();
        whereQuery.put("email", "coveneygeorgia@hotmail.com");
        DBCursor cursor = tableUsers.find(whereQuery);
        while(cursor.hasNext()) {
            System.out.println(cursor.next());
        }
    }
}



















