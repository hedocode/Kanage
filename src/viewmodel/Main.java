package viewmodel;

import javafx.beans.binding.Bindings;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.util.StringConverter;
import javafx.util.converter.FormatStringConverter;
import model.User;
import javafx.scene.control.ListView;
import model.managers.UserManager;

import java.time.LocalDate;
import java.time.chrono.Chronology;


public class Main {

    private UserManager userManager = new UserManager();

    private IntegerProperty index = new SimpleIntegerProperty();

    public Integer getIndex(){
        return index.get();
    }

    public void setIndex(int i){
        index.set(i);
    }

    private StringProperty nom = new SimpleStringProperty();

    public String getCurrentNom(){
        return nom.getValue();
    }

    public void setCurrentNom(String s){
        nom.set(s);
    }

    private StringProperty prenom = new SimpleStringProperty();

    public String getCurrentPrenom(){
        return prenom.getValue();
    }

    public void setCurrentPrenom(String s){
        prenom.set(s);
    }

    private ObjectProperty<StringConverter<LocalDate>> date = new SimpleObjectProperty<>();

    public ObjectProperty<StringConverter<LocalDate>> getDate() {
        return date;
    }

    public void setDate(LocalDate date){
        StringConverter<LocalDate> sld = new StringConverter<LocalDate>() {
            @Override
            public String toString(LocalDate object) {
                if(object != null)
                    return object.toString();
                return "ERROR";
            }

            @Override
            public LocalDate fromString(String string) {
                return LocalDate.parse(string);
            }
        };
        this.date.set(sld);
    }



    @FXML
    private ListView<User> userlist;

    @FXML
    private TextField champnom;

    @FXML
    private TextField champprenom;

    @FXML
    private DatePicker cdn;

    public void setCurrent(){
        User u = userlist.getSelectionModel().getSelectedItem();
        setIndex(userlist.getSelectionModel().getSelectedIndex());
        System.out.println(getIndex());
        setCurrentNom(u.getNom());
        setCurrentPrenom(u.getPrenom());
        System.out.println(u.getBirthDate());
        setDate(u.getBirthDate());
        System.out.println(nom);
        System.out.println(prenom);
        System.out.println(date);
    }

    @FXML
    private void initialize() {
        userManager.addUser(new User("Patrick", "PONT", LocalDate.of(1990, 12, 8)));
        userManager.addUser(new User("Raymond", "BARRE", LocalDate.of(1978, 5, 15)));
        userManager.addUser(new User("Marcel", "HONORE", LocalDate.of(1958, 8, 1)));

        userManager.getUsers().forEach(user -> System.out.println(user.toString()));

        userlist.setItems(FXCollections.observableArrayList(userManager.getUsers()));
        champnom.textProperty().bindBidirectional(nom);
        champprenom.textProperty().bindBidirectional(prenom);
        cdn.converterProperty().bindBidirectional(date);
    }

}
