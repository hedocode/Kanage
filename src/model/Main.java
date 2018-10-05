package model;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.awt.*;

public class Main extends Application {

    private Dimension screenDimension = Toolkit.getDefaultToolkit().getScreenSize();


    @Override
    public void start(Stage primaryStage) throws Exception{

        Parent root = FXMLLoader.load(getClass().getResource("../view/sample.fxml"));
        primaryStage.setTitle("Kanage");
        primaryStage.setScene(new Scene(root, screenDimension.width/2 , screenDimension.height/2));
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
