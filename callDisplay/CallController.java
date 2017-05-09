package callDisplay;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.StackPane;

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
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("telephone.fxml"));
            stack.getChildren().add(parent);
        } catch (IOException ex) {
        }
        menu.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            @Override
            public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {
                if (newValue == call1) {
                    try {
                        stack.getChildren().clear();
                        Parent parent = FXMLLoader.load(getClass().getResource("telephone.fxml"));
                        Button btnStar = (Button) parent.lookup("#btnStar");
                        Button btnSharp = (Button) parent.lookup("#btnSharp");
                        btnStar.setText("*");
                        btnSharp.setText("#");
                        stack.getChildren().add(parent);
                        
                    } catch (IOException ex) {
                    }
                } else if (newValue == call2) {
                    try {
                        stack.getChildren().clear();
                        Parent parent = FXMLLoader.load(getClass().getResource("telephone.fxml"));
                        Button btnStar = (Button) parent.lookup("#btnStar");
                        Button btnSharp = (Button) parent.lookup("#btnSharp");
                        TextField textArea = (TextField) parent.lookup("#textArea");
                        
                        btnStar.setText("동");
                        btnSharp.setText("호수");
                        btnStar.setOnAction(new EventHandler<ActionEvent>() {
							@Override
							public void handle(ActionEvent event) {
								textArea.appendText("동");
							}
                        });
                        btnSharp.setOnAction(new EventHandler<ActionEvent>() {
							@Override
							public void handle(ActionEvent event) {
								textArea.appendText("호");
							}
                        });
                        stack.getChildren().add(parent);
                    } catch (IOException ex) {
                    }
                } else if (newValue == call3) {
                    try {
                        stack.getChildren().clear();
                        Parent parent = FXMLLoader.load(getClass().getResource("security.fxml"));
                        stack.getChildren().add(parent);
                    } catch (IOException ex) {
                    }
                }
            }

        });
    }

}
