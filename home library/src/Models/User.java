package Models;

import Enums.*;

public class User {
    private String login;
    private String password;
    private String salt;
    private String email;
    private Role role;

    public User(String login, String password, String salt, String email, Role role) {
        this.login = login;
        this.password = password;
        this.email = email;
        this.role = role;
        this.salt = salt;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getSalt() {
        return salt;
    }

    public String getEmail() {
        return email;
    }

    public Role getRole() {
        return role;
    }
}
