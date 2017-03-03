package ie.cit.architect.protracker.controller;

import ie.cit.architect.protracker.model.User;
import ie.cit.architect.protracker.persistors.IPersistor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Created by brian on 3/3/2017.
 */
public class DBController
{

    private static DBController instance;
    private IPersistor persistor;
    private ObservableList<User> userList = FXCollections.observableArrayList();

    private DBController() {}

    public static DBController getInstance() {
        if(instance == null) {
            instance = new DBController();
        }
        return instance;
    }


    public void addUser(User user) { this.userList.add(user); }

    public void saveUser() { this.persistor.writeUsers(this.userList); }

    public void setPersistor(IPersistor persistor) { this.persistor = persistor; }

}
