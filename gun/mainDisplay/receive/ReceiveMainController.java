package mainDisplay.receive;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Popup;
import javafx.stage.Stage;

public class ReceiveMainController implements Initializable {
    /* 음성 재생용 미디어, 미디어플레이어 선언 */
    private Media media;
    private MediaPlayer mediaPlayer;

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
        /*팝업 화면 띄우기*/
        try {

            Stage primaryStage = (Stage) btnPopup.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("receivePopup.fxml"));
            Popup popup = new Popup();
            popup.getContent().add(root);
            popup.show(primaryStage);

            Button callReject = (Button) root.lookup("#reject");
            ToggleButton callAccept = (ToggleButton) root.lookup("#accept");

            callReject.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent e) {
                    popup.hide();
                    if (mediaPlayer != null) {
                        mediaPlayer.dispose();
                    }
                }
            });
            callAccept.setOnMouseClicked(ex -> handleCallAccept(ex));

        } catch (IOException ex) {

        }

        if (mediaPlayer != null) {
            mediaPlayer.dispose();
        }

        media = new Media(getClass().getResource("sounds/bell1.mp3").toString());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();

        mediaPlayer.setOnEndOfMedia(() -> {
            mediaPlayer.dispose();
        });
    }

    private void handleCallAccept(MouseEvent ex) {
        if (mediaPlayer != null) {
            mediaPlayer.dispose();
        }
    }

}
