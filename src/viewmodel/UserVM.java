package viewmodel;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.User;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class UserVM implements PropertyChangeListener {

    private User u;

    public UserVM(User u){
        this.u = u;
        this.u.addPropertyChangeListener(this);
        setText(u.toString());
        setFirstName(u.getFirstName());
        setLastName(u.getLastName());
    }

    private StringProperty firstName = new SimpleStringProperty();
        public String getFirstName() { return firstName.get(); }
        public StringProperty firstNameProperty() { return firstName; }
        public void setFirstName(String firstName) { this.firstName.set(firstName); }

    private StringProperty lastName = new SimpleStringProperty();
        public String getLastName() { return lastName.get(); }
        public StringProperty lastNameProperty() { return lastName; }
        public void setLastName(String lastName) { this.lastName.set(lastName); }

    private StringProperty text = new SimpleStringProperty();
        public String getText() { return text.get(); }
        public StringProperty textProperty() { return text; }
        public void setText(String text) { this.text.set(text); }

    private SimpleObjectProperty<User> user = new SimpleObjectProperty<>(u);
        public User getUser() { return user.get(); }
        public SimpleObjectProperty<User> userProperty() { return user; }
        public void setUser(User user) { this.user.set(user); }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if(evt.getPropertyName().equals(User.PROP_FN)){
            setFirstName((String) evt.getNewValue());
        }

    }
}
