package pl.coderslab.warsztat2krkw03.model;

import org.mindrot.jbcrypt.BCrypt;

public class User {

    //parametry
    //alt[lewy]+shift żeby edytować w wielu linijkach {multiedit}
    private int id;
    private String username;
    private String email;
    private String password;


    //konstruktory
    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        setPassword(password);
    }

    public User(int id, String username, String email, String password) {
        this.id = id;
        this.username = username;
        this.email = email;
        setPassword(password);
    }

    public User() {
    }

    //gettery i settery
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = BCrypt.hashpw(password, BCrypt.gensalt());
    }

    //metoda Rafala do sprawdzania hasla
    public boolean isPasswordCorrect(String password) {
        return BCrypt.checkpw(password, getPassword());
    }

    //cialo klasy
    public static void main(String[] args) {


    }


}
