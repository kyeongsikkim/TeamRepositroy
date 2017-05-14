package mainDisplay_Test;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

public class MainDisplay_3Controller implements Initializable {

    @FXML
    private StackPane stackPane;
    @FXML
    private Label labelDate;
    @FXML
    private Label labelTime;
    @FXML
    private Button btnElevator;
    @FXML
    private Button btnOpenDoor;
    @FXML
    private Button btnSetting;
    @FXML
    private Button btnLock;
    @FXML
    private Button btnControl;
    @FXML
    private Button btnCall;
    @FXML
    private Button btnMenu;
    @FXML
    private Button btnEntrance;

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

        btnMenu.setOnAction(e -> handleBtnMenu(e));
    }

    private void handleBtnMenu(ActionEvent e) {
        if (btnMenu.getUserData().equals("close")) {
            btnMenu.setUserData("open");
            //Call 버튼 이동
            KeyValue keyValue1 = new KeyValue(btnCall.translateXProperty(), -225);
            KeyValue keyValue2 = new KeyValue(btnCall.translateYProperty(), 50);
            KeyFrame keyFrame1 = new KeyFrame(Duration.seconds(0.6), keyValue1);
            KeyFrame keyFrame2 = new KeyFrame(Duration.seconds(0.6), keyValue2);
            Timeline timeline1 = new Timeline();
            timeline1.getKeyFrames().add(keyFrame1);
            timeline1.getKeyFrames().add(keyFrame2);
            //Control 버튼 이동
            KeyValue keyValue3 = new KeyValue(btnControl.translateXProperty(), -75);
            KeyValue keyValue4 = new KeyValue(btnControl.translateYProperty(), 120);
            KeyFrame keyFrame3 = new KeyFrame(Duration.seconds(0.4), keyValue3);
            KeyFrame keyFrame4 = new KeyFrame(Duration.seconds(0.4), keyValue4);
            Timeline timeline2 = new Timeline();
            timeline2.getKeyFrames().add(keyFrame3);
            timeline2.getKeyFrames().add(keyFrame4);
            //Lcck 버튼 이동
            KeyValue keyValue5 = new KeyValue(btnLock.translateXProperty(), 75);
            KeyValue keyValue6 = new KeyValue(btnLock.translateYProperty(), 120);
            KeyFrame keyFrame5 = new KeyFrame(Duration.seconds(0.4), keyValue5);
            KeyFrame keyFrame6 = new KeyFrame(Duration.seconds(0.4), keyValue6);
            Timeline timeline3 = new Timeline();
            timeline3.getKeyFrames().add(keyFrame5);
            timeline3.getKeyFrames().add(keyFrame6);
            //Setting 버튼 이동
            KeyValue keyValue7 = new KeyValue(btnSetting.translateXProperty(), 225);
            KeyValue keyValue8 = new KeyValue(btnSetting.translateYProperty(), 50);
            KeyFrame keyFrame7 = new KeyFrame(Duration.seconds(0.6), keyValue7);
            KeyFrame keyFrame8 = new KeyFrame(Duration.seconds(0.6), keyValue8);
            Timeline timeline4 = new Timeline();
            timeline4.getKeyFrames().add(keyFrame7);
            timeline4.getKeyFrames().add(keyFrame8);
            //timeline 실행
            timeline1.play();
            timeline2.play();
            timeline3.play();
            timeline4.play();
        } else if (btnMenu.getUserData().equals("open")) {
            btnMenu.setUserData("close");
            //call 버튼 이동
            KeyValue keyValue1 = new KeyValue(btnCall.translateXProperty(), 0);
            KeyValue keyValue2 = new KeyValue(btnCall.translateYProperty(), 0);
            KeyFrame keyFrame1 = new KeyFrame(Duration.seconds(0.4), keyValue1);
            KeyFrame keyFrame2 = new KeyFrame(Duration.seconds(0.4), keyValue2);
            Timeline timeline1 = new Timeline();
            timeline1.getKeyFrames().add(keyFrame1);
            timeline1.getKeyFrames().add(keyFrame2);
            //Control 버튼 이동
            KeyValue keyValue3 = new KeyValue(btnControl.translateXProperty(), 0);
            KeyValue keyValue4 = new KeyValue(btnControl.translateYProperty(), 0);
            KeyFrame keyFrame3 = new KeyFrame(Duration.seconds(0.4), keyValue3);
            KeyFrame keyFrame4 = new KeyFrame(Duration.seconds(0.4), keyValue4);
            Timeline timeline2 = new Timeline();
            timeline2.getKeyFrames().add(keyFrame3);
            timeline2.getKeyFrames().add(keyFrame4);
            //Lcck 버튼 이동
            KeyValue keyValue5 = new KeyValue(btnLock.translateXProperty(), 0);
            KeyValue keyValue6 = new KeyValue(btnLock.translateYProperty(), 0);
            KeyFrame keyFrame5 = new KeyFrame(Duration.seconds(0.4), keyValue5);
            KeyFrame keyFrame6 = new KeyFrame(Duration.seconds(0.4), keyValue6);
            Timeline timeline3 = new Timeline();
            timeline3.getKeyFrames().add(keyFrame5);
            timeline3.getKeyFrames().add(keyFrame6);
            //Setting 버튼 이동
            KeyValue keyValue7 = new KeyValue(btnSetting.translateXProperty(), 0);
            KeyValue keyValue8 = new KeyValue(btnSetting.translateYProperty(), 0);
            KeyFrame keyFrame7 = new KeyFrame(Duration.seconds(0.4), keyValue7);
            KeyFrame keyFrame8 = new KeyFrame(Duration.seconds(0.4), keyValue8);
            Timeline timeline4 = new Timeline();
            timeline4.getKeyFrames().add(keyFrame7);
            timeline4.getKeyFrames().add(keyFrame8);
            //timeline 실행
            timeline1.play();
            timeline2.play();
            timeline3.play();
            timeline4.play();
        }
    }    
    
}
