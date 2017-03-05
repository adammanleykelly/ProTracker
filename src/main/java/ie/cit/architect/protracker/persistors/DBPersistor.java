package ie.cit.architect.protracker.persistors;

import ie.cit.architect.protracker.helpers.MySQLCredentials;
import ie.cit.architect.protracker.model.User;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by brian on 3/3/2017.
 */
public class DBPersistor  implements IPersistor{

    private Connection dbConnection;
    private ArrayList<AutoCloseable> dbObjects;


    private static final String DB_REMOTE_HOST = "82.118.226.76";
    private static final String DB_LOCAL_HOST = "localhost";
    private static final String DB_NAME = "protracker";
    private static final String DB_PORT = "3306";
    private static final String DB_USER = "remoteUser";
    private static final String DB_LOCALHOST_PASS = "bossdog12";


    public DBPersistor(){

        dbObjects = new ArrayList<>();
        try {

            String db_Driver = "com.mysql.cj.jdbc.Driver";

            String db_URL = "jdbc:mysql://"+ DB_REMOTE_HOST +":"+ DB_PORT +"/"+ DB_NAME +
                    "?user="+ DB_USER + "&password=" + MySQLCredentials.DB_PASS;

            String db_TEST_URL = "jdbc:mysql://"+ DB_LOCAL_HOST +":"+ DB_PORT +"/"+ DB_NAME +
                    "?user="+ "root" + "&password=" + DB_LOCALHOST_PASS;

            Class.forName(db_Driver);

            this.dbConnection = DriverManager.getConnection(db_TEST_URL);

            if(this.dbConnection != null) {
                System.out.println("Connected!");
            } else {
                System.out.println("Connection Failed!");
            }


        }catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void writeUsers(ObservableList<User> users) {
        try {
            for (User currUser : users) {
                PreparedStatement preparedStatement =
                        dbConnection.prepareStatement(
                                "INSERT INTO users " +
                                        "(email, password)" +
                                        "VALUES(?, ?)");

                preparedStatement.setString(1, currUser.getEmailAddress());
                preparedStatement.setString(2, currUser.getPassword());

                preparedStatement.executeUpdate();
                dbObjects.add(preparedStatement);
            }
            close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void close() {
        try{
            for(AutoCloseable curr : dbObjects) {
                curr.close();
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }


    public String getDbUserEmail(User user) {
        String email = user.getEmailAddress();
        return email;
    }


    public String getDbUserPass(User user) {
        String pass = user.getPassword();
        return pass;
    }





















}
