package model.managers;

import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.User;

import java.util.ArrayList;
import java.util.List;

public class UserManager {
    private ObservableList<User> users = FXCollections.observableArrayList();

    public void addUser(User u){
        users.add(u);
    }

    public ObservableList<User> getUsers(){
        return FXCollections.unmodifiableObservableList(users);
    }

    public User getUser(Number i) { return users.get((Integer) i);}
}
