package model.managers;

import model.User;

import java.util.ArrayList;
import java.util.List;

public class UserManager {
    private List<User> users = new ArrayList<User>();

    public void addUser(User u){
        users.add(u);
    }

    public List<User> getUsers(){
        return users;
    }
}
