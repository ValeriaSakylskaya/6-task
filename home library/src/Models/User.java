package Models;
import Enums.*;
public class User {
    private String Login;
    private String Pass;
    private String Salt;
    private String Email;
    private Role role;
    public User (String login, String pass, String salt, String email, Role role){
        this.Login = login;
        this.Pass = pass;
        this.Email = email;
        this.role = role;
        this.Salt = salt;
    }

    public String getLogin() {
        return Login;
    }

    public String getPass() {
        return Pass;
    }

    public String getSalt() {
        return Salt;
    }

    public String getEmail() {
        return Email;
    }

    public Role getRole() {
        return role;
    }
}
