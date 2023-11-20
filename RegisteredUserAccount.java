package se2203b.assignments.adminapp;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


//model class for the entity class RegisteredUserAccount
public class RegisteredUserAccount {

    //attributes
    private final StringProperty name;
    private final StringProperty username;
    private final StringProperty password;
    private final StringProperty address;

    //constructor
    public RegisteredUserAccount(String name, String username, String password, String address){
        this.name = new SimpleStringProperty(name);
        this.username = new SimpleStringProperty(username);
        this.password = new SimpleStringProperty(password);
        this.address = new SimpleStringProperty(address);
    }

    //set and get functions
    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public String getUsername() {
        return username.get();
    }

    public StringProperty usernameProperty() {
        return username;
    }

    public String getPassword() {
        return password.get();
    }

    public StringProperty passwordProperty() {
        return password;
    }

    public String getAddress() {
        return address.get();
    }

    public StringProperty addressProperty() {
        return address;
    }

    public void setName(String name){
        this.name.set(name);
    }

    public void setUsername(String username){
        this.username.set(username);
    }

    public void setPassword(String password){
        this.password.set(password);
    }

    public void setAddress(String address){
        this.address.set(address);
    }
}
