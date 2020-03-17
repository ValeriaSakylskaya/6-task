package Models;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Note {
    private int Id;
    private String Theme;
    private Date CreatedAt;
    private String Email;
    private String Message;
    private DateFormat format = new SimpleDateFormat("dd.MM.yyyy");
    public Note (int Id, String theme, Date createdAt, String email, String message){
        this.Id = Id;
        this.Theme = theme;
        this.CreatedAt = createdAt;
        this.Email = email;
        this.Message = message;
    }

    public String getMessage() {
        return Message;
    }

    public Date getCreatedAt() {
        return CreatedAt;
    }

    public String getTheme() {
        return Theme;
    }

    public int getId() {
        return Id;
    }

    public String getEmail() {
        return Email;
    }

    @Override
    public String toString(){
        StringBuilder builder = new StringBuilder();
        builder.append(" Note - " + Id + " at the " + format.format(CreatedAt) + "\n");
        builder.append("Theme: " + Theme + "\n");
        builder.append("Email: " + Email + "\n");
        builder.append("Message: " + Message);
        return builder.toString();
    }
}

