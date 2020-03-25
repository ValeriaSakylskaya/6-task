package Models;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Note {
    private int id;
    private String theme;
    private Date createdDate;
    private String email;
    private String message;
    private DateFormat format = new SimpleDateFormat("dd.MM.yyyy");

    public Note(int Id, String theme, Date createdDate, String email, String message) {
        this.id = Id;
        this.theme = theme;
        this.createdDate = createdDate;
        this.email = email;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public String getTheme() {
        return theme;
    }

    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(" Note - " + id + " at the " + format.format(createdDate) + "\n");
        builder.append("Theme: " + theme + "\n");
        builder.append("Email: " + email + "\n");
        builder.append("Message: " + message);
        return builder.toString();
    }
}

