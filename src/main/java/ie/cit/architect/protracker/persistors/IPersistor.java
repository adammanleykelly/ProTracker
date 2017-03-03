package ie.cit.architect.protracker.persistors;

import ie.cit.architect.protracker.model.User;
import javafx.collections.ObservableList;

/**
 * Created by brian on 3/3/2017.
 */
public interface IPersistor {


    void writeUsers(ObservableList<User> users);

}
