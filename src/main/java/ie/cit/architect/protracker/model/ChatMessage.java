package ie.cit.architect.protracker.model;

/**
 * Created by brian on 26/04/17.
 */
public class ChatMessage {

    private String message;

    public ChatMessage() { }

    public ChatMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return message;
    }
}
