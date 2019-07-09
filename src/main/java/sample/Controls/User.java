package sample.Controls;

public class User {

    private int id;
    private int accessMode;
    private String username;
    private String password;
    private String email;

    public User() { }
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
    public User(int id, int accessMode, String username, String password, String email) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public void setId(int id) {
        this.id = id;
    }
    public void setAccessMode(int accessMode) {
        this.accessMode = accessMode;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setEMail(String email) {
        this.email = email;
    }

    public int getId() {
        return id;
    }
    public int getAccessMode() {
        return accessMode;
    }
    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }
    public String getEmail() {
        return email;
    }

    public String toString () {
        return getClass().getSimpleName() + ": " + id + ' ' + accessMode + ' ' + username + ' ' + password + ' ' + email;
    }
}
