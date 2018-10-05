package viewmodel;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.User;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class UserVM implements PropertyChangeListener {

    public UserVM(User u){
        u.addPropertyChangeListener(this);
        setUser(u);
        setText(u.toString());
    }

    private SimpleObjectProperty<User> user = new SimpleObjectProperty<>();
        public User getUser() { return user.get();}
        public SimpleObjectProperty<User> userProperty() { return user; }
        public void setUser(User user) { this.user.set(user); }

    private StringProperty text = new SimpleStringProperty();
        public String getText() { return text.get(); }
        public StringProperty textProperty() { return text; }
        public void setText(String text) { this.text.set(text); }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}
