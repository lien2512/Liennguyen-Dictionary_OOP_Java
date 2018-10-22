package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.PrintWriter;
import java.io.StringWriter;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Từ điển Anh - Việt");
        primaryStage.setScene(new Scene (root, 1280, 800));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
