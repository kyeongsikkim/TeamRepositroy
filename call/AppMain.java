package mainDisplay.call;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class AppMain extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Font.loadFont(getClass().getResource("fonts/08SeoulNamsanEB.ttf").toExternalForm(), 16);
        
        Parent parent = FXMLLoader.load(getClass().getResource("call.fxml"));
        Scene scene = new Scene(parent);
        
        primaryStage.setScene(scene);
        primaryStage.setTitle("창제목");
        
        primaryStage.setOnCloseRequest(event -> {
            System.out.println("Close...");
        });
        
        primaryStage.show(); 
    }
    
    public static void main(String[] args) {
        launch(args);
    }
    
}
