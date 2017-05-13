package mainDisplay;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

public class InterphonePopupController implements Initializable {

    private Media media;
    private MediaPlayer mediaPlayer;

    @FXML
    private MediaView mediaViewInterphone;
    @FXML
    private Button btnInterphoneOpenDoor;
    @FXML
    private Button btnInterphoneSave;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btnInterphoneSave.setOnAction(e -> handleBtnInterphoneSave(e));
        btnInterphoneOpenDoor.setOnAction(e -> handleBtnInterphoneOpenDoor(e));

        btnInterphoneSave.setFocusTraversable(false);
        btnInterphoneOpenDoor.setFocusTraversable(false);
    }

    private void handleBtnInterphoneSave(ActionEvent e) {
        media = new Media(getClass().getResource("sounds/interphone.mp3").toString());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();

        String mediaUriTemp = mediaViewInterphone.getMediaPlayer().getMedia().getSource();
        String mediaUri = mediaUriTemp.substring(6);
        System.out.println(mediaUri);
        try {
            SimpleDateFormat sdf1 = new SimpleDateFormat("MM월dd일E요일HH시mm분SS초");
            String name = sdf1.format(new Date()) + ".m4v";
            FileInputStream fis = new FileInputStream(mediaUri);
            FileOutputStream fos = new FileOutputStream("C:\\Temp\\" + name);
            byte[] byteArr = new byte[100];
            int readBytes = -1;
            while (true) {
                readBytes = fis.read(byteArr);
                if (readBytes == -1) {
                    break;
                }
                fos.write(byteArr, 0, readBytes);
            }

            fos.flush();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void handleBtnInterphoneOpenDoor(ActionEvent e) {
        media = new Media(getClass().getResource("sounds/openDoor.mp3").toString());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();
    }
}
