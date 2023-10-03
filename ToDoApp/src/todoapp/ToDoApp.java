package todoapp;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ToDoApp extends Application{

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("HomePage.fxml"));
        Parent root = loader.load();

        Scene scene = new Scene(root);
        
        //String css=this.getClass().getResource("HomePageStyle.css").toExternalForm();
        //scene.getStylesheets().add(css);
        
        primaryStage.setTitle("To Do Application");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}

