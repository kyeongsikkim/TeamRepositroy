package callDisplay;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class SecurityController implements Initializable {

    @FXML
    private ToggleButton btnCall;
    @FXML
    private ImageView imageCall;
    @FXML
    private Label lblConnect;
    @FXML
    private ToggleGroup connect;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        btnCall.toFront();
        connect.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            @Override
            public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {

                if (newValue == btnCall) {
                    System.out.println("전화걸기");
                    lblConnect.setText(".........");
                    imageCall.setImage(new Image(getClass().getResource("images/calloff.png").toString()));

                } else {
                    System.out.println("전화끊기");
                    lblConnect.setText("");
                    imageCall.setImage(new Image(getClass().getResource("images/call03.png").toString()));
                }
            }

        });
    }

}
