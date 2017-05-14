package call;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.stage.Popup;
import javafx.stage.Stage;

public class MainCallController implements Initializable {

    @FXML
    private Button btnPopup;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btnPopup.setOnAction(e -> {
            handlePopup(e);
        });
    }

    @FXML
    private void handlePopup(ActionEvent e) {
       
        try {
            
            Stage primaryStage = (Stage) btnPopup.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("popup.fxml"));
            Popup popup = new Popup();
            popup.getContent().add(root);
            popup.setAutoHide(true);
            popup.show(primaryStage);
            
//            AnchorPane anchorPane = (AnchorPane) FXMLLoader.load(getClass().getResource("popup.fxml"));
//            popup.getContent().add(anchorPane);
//            popup.setAutoHide(true);
//            popup.show(call.AppMain.primaryStage);
        } catch (IOException ex) {

        }
    }

}
