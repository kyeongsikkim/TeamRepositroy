package mainDisplay.securitydisplay;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class VisiterdisplayAppMain extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent parent = FXMLLoader.load(getClass().getResource("visiterdisplay.fxml"));
        Scene scene = new Scene(parent);
        primaryStage.setTitle("방문자");
        primaryStage.setOnCloseRequest(event -> System.out.print("close"));
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    public static void main(String...args) {
        launch(args);
    }
}
