package ie.cit.architect.protracker.model;

import ie.cit.architect.protracker.helpers.Consts;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by brian on 08/03/17.
 */
public class Project implements IProject{

    private int projectId;
    private static AtomicInteger next_id = new AtomicInteger(0);
    private String name;
    private Date date;
    private String author;
    private String location;
    private String clientName;
    private double fee;
    private double vat;
    private double total;
    private ArrayList<Project> projectNames;


    public Project() {}

    public Project(String name) {
        this.name = name;
    }

    public Project(int projectId, String name, Date date) {
        this.projectId = Project.next_id.incrementAndGet();
        this.name = name;
        this.date = date;
    }

    public Project(String name, String clientName, double fee) {
        this.name = name;
        this.clientName = clientName;
        this.fee = fee;
        setVat();
        setTotal();
        setDate();
    }

    public Project(String name, String author, String location, String clientName, double fee) {
        this.projectId = Project.next_id.incrementAndGet();
        this.name = name;
        setDate();
        this.author = author;
        this.location = location;
        this.clientName = clientName;
        this.fee = fee;
    }


    @Override
    public String getLocation() {
        return location;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public Date getDate() {
        return date;
    }

    @Override
    public void setDate() {
        this.date = new Date();
    }

    @Override
    public String getAuthor() {
        return author;
    }


    public void setAuthor(String author) {
        this.author = author;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String getClientName() {
        return clientName;
    }


    public void setClientName(String clientName) {
        this.clientName = clientName;
    }


    public int getProjectId() {
        return projectId;
    }


    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    @Override
    public double getFee() {
        return fee;
    }

    @Override
    public void setFee(double fee) {
        this.fee = fee;
    }


    public double getVat() {
        return vat;
    }

    public void setVat() {
        this.vat = fee * Consts.IRISH_VAT;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal() {
        this.total = fee + vat;
    }

    public String getFormattedDate() {

        String inputPattern = "E MMM dd HH:mm:ss z yyyy";
        String outputPattern = "dd-MMM-yy HH:mm:ss a";
        String dateFormatted = "";
        Date currentDate;
        try {

            String d = String.valueOf(this.date);
            SimpleDateFormat sdf = new SimpleDateFormat(inputPattern, Locale.ENGLISH);
            currentDate = sdf.parse(d);

            SimpleDateFormat sdf2 = new SimpleDateFormat(outputPattern);
            dateFormatted = sdf2.format(currentDate);

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return dateFormatted;

    }

}
