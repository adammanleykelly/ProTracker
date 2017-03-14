package ie.cit.architect.protracker.model;

/**
 * Created by brian on 08/03/17.
 */
public class Project {

    private String name;
    private String date;
    private String author;
    private String location;
    private String clientName;

    public Project() {}

    public Project(String name, String date, String author, String location, String clientName) {
        this.name = name;
        this.date = date;
        this.author = author;
        this.location = location;
        this.clientName = clientName;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    @Override
    public String toString() {
        return "Project{" +
                "name='" + name + '\'' +
                ", date=" + date +
                ", author='" + author + '\'' +
                ", location='" + location + '\'' +
                ", clientName='" + clientName + '\'' +
                '}';
    }
}
