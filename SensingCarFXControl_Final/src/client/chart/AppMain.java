package client.chart;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AppMain extends Application {

    public static Stage primaryStage;

    @Override
    public void start(Stage primaryStage) throws Exception {
        AppMain.primaryStage = primaryStage;
        //Parent parent = FXMLLoader.load(getClass().getResource("thermistorChart.fxml"));
        //Parent parent = FXMLLoader.load(getClass().getResource("photoresistorChart.fxml"));
        Parent parent = FXMLLoader.load(getClass().getResource("ultrasonicChart.fxml"));
        //Parent parent = FXMLLoader.load(getClass().getResource("gasChart.fxml"));
        Scene scene = new Scene(parent);
        primaryStage.setTitle("lineChart");
        primaryStage.setScene(scene);

        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
