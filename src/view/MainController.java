package view;

import javafx.beans.binding.Bindings;
import javafx.beans.property.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.util.Callback;
import javafx.util.StringConverter;
import model.User;
import viewmodel.UserManagerVM;
import viewmodel.UserVM;

import java.time.LocalDate;

public class MainController {

    private UserManagerVM userManagerVM = new UserManagerVM();

      //-----------//
     //-Properties//
    //-----------//
    private IntegerProperty index = new SimpleIntegerProperty();
        public IntegerProperty indexProperty() { return index; }
        public int getIndex() { return index.get(); }
        public void setIndex(int index) { this.index.set(index); }

    private StringProperty nom = new SimpleStringProperty();
        public StringProperty nomProperty() { return nom; }
        public String getNom() { return nom.get(); }
        public void setNom(String nom) { this.nom.set(nom); }

    private StringProperty prenom = new SimpleStringProperty();
        public StringProperty prenomProperty() { return prenom; }
        public String getPrenom() { return prenom.get(); }
        public void setPrenom(String prenom) { this.prenom.set(prenom);}

    private ObjectProperty<LocalDate> date = new SimpleObjectProperty<>();
        public ObjectProperty<LocalDate> dateProperty() { return date; }
        public LocalDate getDate()                      { return date.get(); }
        public void setDate(LocalDate date)             { this.date.set(date); }

    private BooleanProperty modify = new SimpleBooleanProperty();
        public BooleanProperty modifyProperty() { return modify; }
        public boolean isModify()               { return modify.get(); }
        public void setModify(boolean modify)   { this.modify.set(modify); }




      //-----------//
     //---FXML----//
    //-----------//
    @FXML
    private ListView<UserVM> userlist;

    @FXML
    private TextField champnom;

    @FXML
    private TextField champprenom;

    @FXML
    private DatePicker datepicker;

    @FXML
    private Button addmodify;

    @FXML
    private Button add;

    @FXML
    private void changeMode(){
        modify.setValue(!modify.getValue());
        if(!modify.getValue()){
            setPrenom("");
            setNom("");
            setDate(null);
            champnom.textProperty().unbindBidirectional(nom);
            champprenom.textProperty().unbindBidirectional(prenom);
            date.unbindBidirectional(datepicker.valueProperty());
        }
        else{
            champnom.textProperty().bindBidirectional(nom);
            champprenom.textProperty().bindBidirectional(prenom);
            date.bindBidirectional(datepicker.valueProperty());
        }
    }

    @FXML
    private void initialize() {

        userManagerVM.getUsers().forEach(user -> System.out.println(user.getText()));

        userManagerVM.usersProperty().addListener((observable, oldValue, newValue) -> {  });

        userlist.itemsProperty().bind(userManagerVM.usersProperty());

        nom.bindBidirectional(index, new StringConverter<>() {
            @Override
            public String toString(Number object) {
                try{
                    return userManagerVM.getUsers().get((Integer)object).getLastName();
                }
                catch(IndexOutOfBoundsException e){

                    return null;
                }
            }

            @Override
            public Number fromString(String string) {
                return null;
            }
        });

        userlist.setCellFactory(__ -> new ListCell<UserVM>(){
            @Override
            protected void updateItem(UserVM item, boolean empty) {
                super.updateItem(item, empty);

                if(!empty){
                    textProperty().bind(Bindings.concat(item.getText()));
                }else{
                    textProperty().unbind();
                    setText("");
                }
            }
        });

        userlist.itemsProperty().bind(userManagerVM.usersProperty());

        userlist.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if(oldValue!=null){
                champnom.textProperty().unbindBidirectional(oldValue.firstNameProperty());
            }
            if(newValue!=null){
                champnom.textProperty().bindBidirectional(newValue.firstNameProperty());
            }
        });

        champnom.textProperty().bindBidirectional(nom);
        champprenom.textProperty().bindBidirectional(prenom);


        date.bindBidirectional(datepicker.valueProperty());

        addmodify.textProperty().bindBidirectional(modify, new StringConverter<Boolean>() {
            @Override
            public String toString(Boolean object) {
                if(object){
                    return "Modification";
                }
                return "Ajout";
            }

            @Override
            public Boolean fromString(String string) {
                if(string.equals("Modification")){
                    return true;
                }
                return false;
            }
        });

        setData();

        //DISABLE BINDINGS
        add.disableProperty().bind(modify);
        userlist.disableProperty().bind(modify.not());
    }

    @FXML
    private void addUser() {
        userManagerVM.addUser(new User(getPrenom(), getNom(), getDate()));
        setPrenom("");
        setNom("");
        setDate(null);
    }

    private void setData(){
        userManagerVM.addUser(new User("Raymond", "BARRE", LocalDate.of(1978, 5, 15)));
        userManagerVM.addUser(new User("Marcel", "HONORE", LocalDate.of(1958, 8, 1)));
        userManagerVM.addUser(new User("Arnold", "SWARZKOPZ", LocalDate.of(1999, 2, 9)));
        userManagerVM.addUser(new User("Julien", "MALABRE", LocalDate.of(1980, 4, 5)));
        userManagerVM.addUser(new User("Patrick", "FIONI", LocalDate.of(1990, 12, 8)));
        userManagerVM.addUser(new User("Marie", "JUDAS", LocalDate.of(1940, 2, 14)));
        userManagerVM.addUser(new User("Anne", "MILLE", LocalDate.of(1970, 10, 18)));
        userManagerVM.addUser(new User("Marc", "ELINO", LocalDate.of(1988, 11, 7)));
        userManagerVM.addUser(new User("Lisa", "STEVONIVAK", LocalDate.of(1998, 2, 15)));
        userManagerVM.addUser(new User("Paul", "COURT", LocalDate.of(1994, 3, 16)));
        userManagerVM.addUser(new User("Antoine", "PETIT", LocalDate.of(1948, 7, 20)));
        userManagerVM.addUser(new User("Castor", "MARECHAL", LocalDate.of(1938, 1, 30)));
        userManagerVM.addUser(new User("Léa", "REBOUR", LocalDate.of(1959, 5, 1)));
        userManagerVM.addUser(new User("Loic", "ROI", LocalDate.of(1964, 6, 2)));
        userManagerVM.addUser(new User("Rémi", "GENTI", LocalDate.of(1969, 2, 5)));
        userManagerVM.addUser(new User("Nina", "RICCI", LocalDate.of(1990, 4, 9)));
        userManagerVM.addUser(new User("François", "PICCOLINI", LocalDate.of(1978, 9, 20)));
        userManagerVM.addUser(new User("Denis", "PISALCO", LocalDate.of(1958, 10, 14)));
        userManagerVM.addUser(new User("Zac", "RIOLU", LocalDate.of(1990, 3, 10)));
        userManagerVM.addUser(new User("Xavier", "PORTE", LocalDate.of(1978, 11, 1)));
        userManagerVM.addUser(new User("Henri", "MAVIE", LocalDate.of(1958, 7, 20)));
    }
}