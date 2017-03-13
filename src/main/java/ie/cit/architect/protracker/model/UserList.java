package ie.cit.architect.protracker.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by brian on 13/03/17.
 */
public class UserList implements Serializable{

    private ArrayList<User> users;

    public UserList() {
        this.users = new ArrayList<>();
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }

    public void add(User user) {
        this.users.add(user);
    }
}
