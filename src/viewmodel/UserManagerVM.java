package viewmodel;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.User;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class UserManagerVM implements PropertyChangeListener {

    private ListProperty<UserVM> users = new SimpleListProperty<>();
        public ListProperty<UserVM> usersProperty() { return users; }
        public void setUsers(ObservableList<UserVM> users) { this.users.set(users); }
        public ObservableList<UserVM> getUsers(){ return FXCollections.unmodifiableObservableList(users); }


    public UserManagerVM(){
        setUsers(FXCollections.observableArrayList());
    }

    public void addUser(User u){
        users.add(new UserVM(u));
    }

    public UserVM getUserVMAt(int index){
            return users.get(index);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        String propertyName = evt.getPropertyName();

        if ("nom".equals(propertyName)) {

        } else if ("focusedWindow".equals(propertyName)) {

        }
    }
}
