package model.managers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.User;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class UserManager {
    private PropertyChangeSupport pcs = new PropertyChangeSupport(this);

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        this.pcs.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        this.pcs.removePropertyChangeListener(listener);
    }

    private ObservableList<User> users = FXCollections.observableArrayList();

    public void addUser(User u){
        users.add(u);
        //pcs.fireIndexedPropertyChange();
    }

    public ObservableList<User> getUsers(){
        return users;
    }

    public User getUser(Number i) { return users.get((Integer) i);}
}
