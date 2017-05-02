package mainDisplay_Test;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

public class MainDisplayController implements Initializable {

    @FXML
    private StackPane stackPane;
    @FXML
    private Label labelDate;
    @FXML
    private Label labelTime;
    @FXML
    private ImageView btnOpenDoor;
    @FXML
    private Button btnElevatorUp;
    @FXML
    private Button btnElevatorDown;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        Thread timeThread = new Thread() {
            @Override
            public void run() {
                boolean stop = false;
                SimpleDateFormat sdf1 = new SimpleDateFormat("HH:mm");
                SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy년 MM월 dd일(E)");
                while (!stop) {
                    String nowTime = sdf1.format(new Date());
                    String nowDate = sdf2.format(new Date());
                    Platform.runLater(() -> {
                        labelTime.setText(nowTime);
                        labelDate.setText(nowDate);
                    });

                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException ex) {
                    }
                }
            }
        };
        timeThread.setDaemon(true);
        timeThread.start();
        
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("buttonGroup.fxml"));
            stackPane.getChildren().add(parent);
            
            Button button = (Button) parent.lookup("#dragButton");
            parent.setTranslateY(200);
            
            button.setOnMouseClicked(e -> {
                KeyValue keyValue1 = new KeyValue(parent.translateYProperty(), 150);
                KeyFrame keyFrame1 = new KeyFrame(Duration.seconds(1.0), keyValue1);
                Timeline timeline1 = new Timeline();
                timeline1.getKeyFrames().add(keyFrame1);
                timeline1.play();
            });
            
        } catch (IOException ex) {}
    }
  
}
