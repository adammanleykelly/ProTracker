package ie.cit.architect.protracker.persistors;

import com.mongodb.MongoClient;
import com.mongodb.MongoException;

/**
 * Author Brian Coveney
 * Date:  26/04/17.
 */
public class MongoLocalConnector {

    public MongoLocalConnector() { }

    public static MongoClient databaseConnectionLocal() {

        MongoClient dbConnection = null;
        try {
            dbConnection = new MongoClient("localhost", 27017);
            // sanity check
            if (dbConnection != null)
                System.out.println("Connected to MongoDB!");
        } catch (MongoException e) {
            e.printStackTrace();
        }
        return dbConnection;
    }

}

