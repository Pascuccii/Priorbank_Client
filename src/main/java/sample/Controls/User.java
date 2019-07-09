package sample.Controls;

public class User {

    private int id;
    private int accessMode;
    private String username;
    private String password;
    private String email;

    User() { }
    User(String username, String password) {
        this.username = username;
        this.password = password;
    }
    User(int id, int accessMode, String username, String password, String email) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
    }

    void setId(int id) {
        this.id = id;
    }
    void setAccessMode(int accessMode) {
        this.accessMode = accessMode;
    }
    void setUsername(String username) {
        this.username = username;
    }
    void setPassword(String password) {
        this.password = password;
    }
    void setEMail(String email) {
        this.email = email;
    }

    int getId() {
        return id;
    }
    int getAccessMode() {
        return accessMode;
    }
    String getUsername() {
        return username;
    }
    String getPassword() {
        return password;
    }
    String getEmail() {
        return email;
    }

    public String toString () {
        return getClass().getSimpleName() + ": " + id + ' ' + accessMode + ' ' + username + ' ' + password + ' ' + email;
    }
}
