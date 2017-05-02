package ie.cit.architect.protracker.model;

import java.util.ArrayList;

/**
 * Created by brian on 01/05/17.
 */
public class ClientList {

    private ArrayList<ClientUser> clientUsers;

    public ClientList() {
        this.clientUsers = new ArrayList<>();
    }

    public ArrayList<ClientUser> getClientUsers() {
        return clientUsers;
    }

    public void setClientUsers(ArrayList<ClientUser> clientUsers) {
        this.clientUsers = clientUsers;
    }

    public void add(ClientUser clientUser) { this.clientUsers.add(clientUser); }

}
