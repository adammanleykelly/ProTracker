package ie.cit.architect.protracker.persistors;

import com.mongodb.*;
import ie.cit.architect.protracker.helpers.Credentials;
import ie.cit.architect.protracker.model.*;

import java.util.ArrayList;
import java.util.List;


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


//                DBObject indexOption = new BasicDBObject();
//                indexOption.put("unique", true);

                //TODO - A unique index ensures that the indexed fields do not store duplicate values
                // https://docs.mongodb.com/v3.2/core/index-unique/

                tableProjects.insert(document);
            }
        }catch (MongoException e) {
            e.printStackTrace();
        }

    }


    @Override
    public void displayCurrentProject(ProjectList projects) {
        try {
            for (IProject currProject : projects.getProjects()) {
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

                String x = String.valueOf(cursor.next());
                System.out.println(x);

            }

        } catch (MongoException e) {
            e.printStackTrace();
        }
    }



    @Override
    public List<Object> getProjectNameList() {

        List<Object> projectNameList = new ArrayList<>();

        try {
            BasicDBObject query = new BasicDBObject();
            BasicDBObject field = new BasicDBObject();
//            BasicDBObject field = new BasicDBObject("name", true).append("_id", false);



            field.put("name", true);
//            field.put("location", true);
            field.append("_id", false);

            DBCursor cursor = tableProjects.find(query, field);


            while (cursor.hasNext()) {

                BasicDBObject object = (BasicDBObject) cursor.next();

                projectNameList.add(object.get("name"));
//                projectNameList.add(object.get("location"));

            }

            System.out.println("Here: " + projectNameList);


        } catch (MongoException e) {
            e.printStackTrace();
        }

        return projectNameList;
    }

















}



















