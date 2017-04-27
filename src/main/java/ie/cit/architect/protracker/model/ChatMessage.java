package ie.cit.architect.protracker.model;

import java.util.Date;

/**
 * Created by brian on 26/04/17.
 */
public class ChatMessage {

    private String message;
    private Date date;

    public ChatMessage() { }

    public ChatMessage(String message) {
        this.message = message;
    }

    public ChatMessage(String message, Date date) {
        this.message = message;
        this.date = new Date();
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getDate() {
        return date;
    }

    @Override
    public String toString() {
        return message;
    }
}
