package client;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class AppMain extends Application {

    public static Stage primaryStage;

    @Override
    public void start(Stage primaryStage) throws Exception {
        AppMain.primaryStage = primaryStage;
        AnchorPane mainAnchorPane = FXMLLoader.load(getClass().getResource("mainControlPanel.fxml"));
        
        Scene scene = new Scene(mainAnchorPane);
        primaryStage.setTitle("AppMain");
        primaryStage.setScene(scene);

        primaryStage.show();
    }

    
    
    public static void main(String[] args) {
        launch(args);
    }
}
