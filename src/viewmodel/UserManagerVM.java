package viewmodel;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.User;
import model.managers.UserManager;

import java.beans.IndexedPropertyChangeEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class UserManagerVM implements PropertyChangeListener {

    private UserManager usersManager;

    private ObservableList<UserVM> listObs = FXCollections.observableArrayList();
    private ListProperty<UserVM> users = new SimpleListProperty<>(listObs);
        public ListProperty<UserVM> usersProperty() { return users; }
        public void setUsers(ObservableList<UserVM> users) { this.users.set(users); }
        public ObservableList<UserVM> getUsers(){ return users.get(); }


    public UserManagerVM(){
        usersManager = new UserManager();
        usersManager.addPropertyChangeListener(this);
        setUsers(FXCollections.observableArrayList());
    }

    public void addUser(User u){
        listObs.add(new UserVM(u));
        usersManager.addUser(u);
    }

    public void delUser(User u){
        listObs.remove(u);
        usersManager.removeUser(u);
    }

    public UserVM getUserVMAt(int index){
            return users.get(index);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        IndexedPropertyChangeEvent e = (IndexedPropertyChangeEvent) evt;

        if(evt.getPropertyName().equals(UserManager.PROP_LIST_USER)){
            User c = (User) evt.getNewValue();
            System.out.println(e.getIndex());
            if (listObs.size() <= e.getIndex()){
                listObs.add(new UserVM((User) evt.getNewValue()));
            }else {
                if (!listObs.get(e.getIndex()).getUser().equals(c)) {
                    listObs.add(e.getIndex(), new UserVM((User) evt.getNewValue()));
                }
            }
        }
    }
}
