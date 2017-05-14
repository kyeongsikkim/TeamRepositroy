package mainDisplay.call;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;

public class CallController implements Initializable {

    @FXML
    private ToggleButton call1;
    @FXML
    private ToggleGroup menu;
    @FXML
    private ToggleButton call2;
    @FXML
    private ToggleButton call3;
    @FXML
    private StackPane stack;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        call1.setSelected(true);
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("callphone.fxml"));
            stack.getChildren().add(parent);
//            call1.setDisable(true);
//            call2.setDisable(false);
//            call3.setDisable(false);
        } catch (IOException ex) {
        }
        menu.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            @Override
            public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {
                if (newValue == call1) {
                    try {
                        stack.getChildren().clear();
                        Parent parent = FXMLLoader.load(getClass().getResource("callphone.fxml"));
                        Button btnStar = (Button) parent.lookup("#btnStar");
                        Button btnSharp = (Button) parent.lookup("#btnSharp");
                        btnStar.setText("*");
                        btnSharp.setText("#");
                        stack.getChildren().add(parent);
//                        call1.setDisable(true);
//                        call2.setDisable(false);
//                        call3.setDisable(false);
                    } catch (IOException ex) {
                    }
                } else if (newValue == call2) {
                    try {
                        stack.getChildren().clear();
                        Parent parent = FXMLLoader.load(getClass().getResource("callphone.fxml"));
                        Button btnStar = (Button) parent.lookup("#btnStar");
                        Button btnSharp = (Button) parent.lookup("#btnSharp");;
                        Label lblRequest = (Label) parent.lookup("#lblRequest");
                        btnStar.setText("동");
                        btnSharp.setText("호");
                        lblRequest.setText("동과 호수를 입력해주세요.");
                        stack.getChildren().add(parent);
//                        call1.setDisable(false);
//                        call2.setDisable(true);
//                        call3.setDisable(false);
                    } catch (IOException ex) {
                    }
                } else if (newValue == call3) {
                    try {
                        stack.getChildren().clear();
                        Parent parent = FXMLLoader.load(getClass().getResource("callguard.fxml"));
                        stack.getChildren().add(parent);
//                        call1.setDisable(false);
//                       call2.setDisable(false);
//                        call3.setDisable(true);
                    } catch (IOException ex) {
                    }
                } 
            }

        });
    }

}
