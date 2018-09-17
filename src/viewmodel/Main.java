package viewmodel;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import model.User;
import javafx.scene.control.ListView;
import model.managers.UserManager;

import java.time.LocalDate;


public class Main {

    private UserManager userManager = new UserManager();

    @FXML
    private ListView<User> userlist;

    @FXML
    private void initialize() {
        userManager.addUser(new User("Patrick", "PONT", LocalDate.of(1990, 12, 8)));
        userManager.addUser(new User("Raymond", "BARRE", LocalDate.of(1978, 5, 15)));

        userManager.getUsers().forEach(user -> System.out.println(user.toString()));

        userlist.setItems(FXCollections.observableArrayList(userManager.getUsers()));
    }

}
