package ie.cit.architect.protracker.persistors;

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



    public DBPersistor(){

        dbObjects = new ArrayList<AutoCloseable>();
        try {

            String db_Driver = "com.mysql.cj.jdbc.Driver";
            String db_URL = "jdbc:mysql://localhost:3308/";
            String db_Name = "protracker?";
            String db_DisableSSL = "autoReconnect=true&useSSL=false";
            String db_User = "root";
            String db_Pass = "bossdog12";

            Class.forName(db_Driver);

            this.dbConnection = DriverManager.getConnection(
                    db_URL+db_Name+db_DisableSSL, db_User, db_Pass);

            if(this.dbConnection != null) {
                System.out.println("CONNECTED TO DATABASE!! :" + this.dbConnection);
            } else {
                System.out.println("CONNECTION FAILED!!");
            }


        }catch (SQLException e) {
            System.out.println(e.getMessage());
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
        }catch(Exception ex){
            System.out.println("ERROR 3:"+ex.getMessage());
        }
    }






















}
