package model.managers;

import model.User;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;

public class UserManager {

    private transient PropertyChangeSupport pcs = new PropertyChangeSupport(this);

    private List<User> users = new ArrayList<>();
    public static final String PROP_LIST_USER = "users";

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        this.pcs.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        this.pcs.removePropertyChangeListener(listener);
    }


    public void addUser(User u){
        users.add(u);
    }

    public void removeUser(User u){
        users.remove(u);
    }

    public List<User> getUsers(){
        return users;
    }

    public User getUser(Number i) { return users.get((Integer) i);}
}
