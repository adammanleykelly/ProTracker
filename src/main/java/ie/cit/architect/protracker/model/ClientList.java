package ie.cit.architect.protracker.model;

import java.util.ArrayList;

/**
 * Created by brian on 01/05/17.
 */
public class ClientList {

    private ArrayList<Client> clients;

    public ClientList() {
        this.clients = new ArrayList<>();
    }

    public ArrayList<Client> getClients() {
        return clients;
    }

    public void setClients(ArrayList<Client> clients) {
        this.clients = clients;
    }

    public void add(Client client) { this.clients.add(client); }

}
