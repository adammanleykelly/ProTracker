package ie.cit.architect.protracker.persistors;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.MongoException;
import ie.cit.architect.protracker.helpers.Credentials;

/**
 * Created by brian on 27/04/17.
 */
public class MongoRemoteConnector {


    public static MongoClient databaseConnectionRemote() {

        MongoClient dbConnection = null;

        try {

            String mongoURI = "mongodb://" + Credentials.DB_MONGO_USER + ":" + Credentials.DB_MONGO_PASS + "@" +
                    Credentials.DB_MONGO_IP +"/" + Credentials.DB_NAME;

            dbConnection = new MongoClient( new MongoClientURI(mongoURI));

            // sanity check
            if (dbConnection != null)
                System.out.println("Connected to MongoDB!");


        } catch (MongoException e) {
            e.printStackTrace();
        }

        return dbConnection;
    }

}
