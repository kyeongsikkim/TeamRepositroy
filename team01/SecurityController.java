package ch17.team01;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class SecurityController implements Initializable {

    @FXML
    private BorderPane security;
    @FXML
    private ToggleGroup menu;
    @FXML
    private ToggleButton call1;
    @FXML
    private ToggleButton call2;
    @FXML
    private ToggleButton call3;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        //call1.setOnAction(event -> handleBtnCall(event));
        //call2.setOnAction(event -> handleBtnCall(event));
        menu.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            @Override
            public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {

                if (newValue == call1) {
                    //StackPane rootPane = (StackPane) call1.getScene().getRoot(); 
                    // rootPane.getChildren().remove(security);

                } else if (newValue == call2) {
                    //StackPane rootPane = (StackPane) call2.getScene().getRoot(); 
                    //rootPane.getChildren().remove(security);
                    try {
                        Parent parent = FXMLLoader.load(getClass().getResource("root.fxml"));
                        Scene scene = new Scene(parent);
                        Stage primaryStage = (Stage) call2.getScene().getWindow();
                        primaryStage.setScene(scene);
                    } catch (IOException ex) {
                    }
                } else if (newValue == call3) {

                }
            }

        });
    }

//    private void handleBtnCall(ActionEvent event) {
//        try {
//            Parent parent = FXMLLoader.load(getClass().getResource("root.fxml"));
//            Scene scene = new Scene(parent);
//            Stage primaryStage = (Stage) call1.getScene().getWindow();
//            primaryStage.setScene(scene);
//        } catch (IOException ex) {
//            ex.printStackTrace();
//        }
//    }
}


//                    try {
//                        Parent parent = FXMLLoader.load(getClass().getResource("root.fxml"));
//                        Scene scene = new Scene(parent);
//                        Stage primaryStage = (Stage) call1.getScene().getWindow();
//                        primaryStage.setScene(scene);
//                    } catch (IOException ex) {
//                    }