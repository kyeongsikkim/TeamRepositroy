package ch17.team01;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


public class SecurityController implements Initializable {

    @FXML
    private BorderPane secure;
    @FXML
    private ToggleButton call1;
    @FXML
    private ToggleGroup menu;
    @FXML
    private ToggleButton call2;
    @FXML
    private ToggleButton call3;

    @FXML
    private ToggleButton btnCall;
    @FXML
    private ImageView imageCall;
    @FXML
    private Label lblConnect;
    @FXML
    private ImageView imageOff;
    @FXML
    private ToggleGroup callToggle;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        menu.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            @Override
            public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {
                if (newValue == call1) {
                    try {
                        Parent parent = FXMLLoader.load(getClass().getResource("root.fxml"));
                        Scene scene = new Scene(parent);
                        Stage primaryStage = (Stage) call1.getScene().getWindow();
                        primaryStage.setScene(scene);
                        
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }else if(newValue == call2){
                    try {
                        Parent parent = FXMLLoader.load(getClass().getResource("root.fxml"));
                        Scene scene = new Scene(parent);
                        Stage primaryStage = (Stage) call2.getScene().getWindow();
                        primaryStage.setScene(scene);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }else if(newValue == call3){
                    System.out.println("call3 호출중....");
                }
            }

        });
        
        btnCall.toFront();
        callToggle.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            @Override
            public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {              
                
                if (newValue == btnCall) {
                    System.out.println("전화걸기");
                    lblConnect.setText(".........");
                   // imageCall.toBack();
                    //imageOff.toFront();
                    imageCall.setImage(new Image(getClass().getResource("images/calloff.png").toString()));
                    
                    
                } else {
                    System.out.println("전화끊기");
                    lblConnect.setText("");
                   //imageCall.toFront();
                  // imageOff.toBack();
                  imageCall.setImage(new Image(getClass().getResource("images/call03.png").toString()));
                }
            }
        });
    }    
    
}
