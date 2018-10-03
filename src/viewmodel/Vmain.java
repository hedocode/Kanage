package viewmodel;

import javafx.beans.property.*;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.util.Callback;
import javafx.util.StringConverter;
import model.User;
import model.managers.UserManager;

import java.time.LocalDate;
import java.util.Observable;
import java.util.Observer;


public class Vmain implements Observer {

    private UserManager userManager = new UserManager();

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

    private ListProperty<User> users = new SimpleListProperty<>(userManager.getUsers());
        public ListProperty<User> usersProperty()       { return users; }
        public void setUsers(ObservableList<User> us)   { users.setValue(us); }
        public ObservableList<User> getUsers()          { return users.getValue(); }



      //-----------//
     //---FXML----//
    //-----------//
    @FXML
    private ListView<User> userlist;

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
            setCurrent();
        }
    }

    @FXML
    private void setCurrent(){
        User u = userlist.getSelectionModel().getSelectedItem();
        setIndex(userlist.getSelectionModel().getSelectedIndex());

        setNom(u.getNom());
        setPrenom(u.getPrenom());
        System.out.println(u.getBirthDate());
        datepicker.setValue(u.getBirthDate());
        System.out.println(date);
    }

    @FXML
    private void initialize() {
        setData();

        userManager.getUsers().forEach(user -> System.out.println(user.toString()));

        users.addListener((observable, oldValue, newValue) -> {  });

        //users.bindBidirectional(userManager.getUsers());

        nom.bindBidirectional(index, new StringConverter<Number>() {
            @Override
            public String toString(Number object) {
                return userManager.getUser(object).getNom();
            }

            @Override
            public Number fromString(String string) {
                return null;
            }
        });

        userlist.setCellFactory(new Callback<>() {
            private int created;

            @Override
            public ListCell<User> call(ListView<User> param) {
                created++;
                return new ListCell<>() {

                    protected void updateItem(User value, boolean empty) {
                        super.updateItem(value, empty);
                        setText((value == null || empty) ? null : String.valueOf(value));

                    }
                };
            }
        });

        userlist.itemsProperty().bind(users);

        userlist.getSelectionModel().selectFirst();

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

        //DISABLE BINDINGS
        add.disableProperty().bind(modify);
        userlist.disableProperty().bind(modify.not());
    }

    @FXML
    private void addUser() {
        userManager.addUser(new User(getPrenom(), getNom(), getDate()));
        setPrenom("");
        setNom("");
        setDate(null);
    }

    private void setData(){
        userManager.addUser(new User("Patrick", "FIONI", LocalDate.of(1990, 12, 8)));
        userManager.addUser(new User("Raymond", "BARRE", LocalDate.of(1978, 5, 15)));
        userManager.addUser(new User("Marcel", "HONORE", LocalDate.of(1958, 8, 1)));
        userManager.addUser(new User("Arnold", "SWARZKOPZ", LocalDate.of(1999, 2, 9)));
        userManager.addUser(new User("Julien", "MALABRE", LocalDate.of(1980, 4, 5)));
        userManager.addUser(new User("Marie", "JUDAS", LocalDate.of(1940, 2, 14)));
        userManager.addUser(new User("Anne", "MILLE", LocalDate.of(1970, 10, 18)));
        userManager.addUser(new User("Marc", "ELINO", LocalDate.of(1988, 11, 7)));
        userManager.addUser(new User("Lisa", "STEVONIVAK", LocalDate.of(1998, 2, 15)));
        userManager.addUser(new User("Paul", "COURT", LocalDate.of(1994, 3, 16)));
        userManager.addUser(new User("Antoine", "PETIT", LocalDate.of(1948, 7, 20)));
        userManager.addUser(new User("Castor", "MARECHAL", LocalDate.of(1938, 1, 30)));
        userManager.addUser(new User("Léa", "REBOUR", LocalDate.of(1959, 5, 1)));
        userManager.addUser(new User("Loic", "ROI", LocalDate.of(1964, 6, 2)));
        userManager.addUser(new User("Rémi", "GENTI", LocalDate.of(1969, 2, 5)));
        userManager.addUser(new User("Nina", "RICCI", LocalDate.of(1990, 4, 9)));
        userManager.addUser(new User("François", "PICCOLINI", LocalDate.of(1978, 9, 20)));
        userManager.addUser(new User("Denis", "PISALCO", LocalDate.of(1958, 10, 14)));
        userManager.addUser(new User("Zac", "RIOLU", LocalDate.of(1990, 3, 10)));
        userManager.addUser(new User("Xavier", "PORTE", LocalDate.of(1978, 11, 1)));
        userManager.addUser(new User("Henri", "MAVIE", LocalDate.of(1958, 7, 20)));
    }

    @Override
    public void update(Observable o, Object arg) {
        if(o.getClass().equals(User.class)){

        }
    }
}
