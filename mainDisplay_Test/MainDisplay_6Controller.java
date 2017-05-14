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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.util.Duration;

public class MainDisplay_6Controller implements Initializable {

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
    private Button btnLock;
    @FXML
    private Button btnSetting;
    @FXML
    private Button btnControl;
    @FXML
    private Button btnCall;
    @FXML
    private Button btnMenu;
    @FXML
    private Button btnEntrance;
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private Label labelName1;
    @FXML
    private Label labelName2;
    @FXML
    private Label labelName3;
    @FXML
    private Label labelName4;
    @FXML
    private Label labelSub1;
    @FXML
    private Label labelSub2;
    @FXML
    private Label labelSub3;
    @FXML
    private Label labelSub4;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        Font timeFont = Font.loadFont(getClass().getResource("fonts/NotoSansCJKkr-DemiLight.otf").toExternalForm(), 56);
        Font dateFont = Font.loadFont(getClass().getResource("fonts/NotoSansCJKkr-DemiLight.otf").toExternalForm(), 28);
        Font nameFont = Font.loadFont(getClass().getResource("fonts/NotoSansCJKkr-Medium.otf").toExternalForm(), 16);
        Font subFont = Font.loadFont(getClass().getResource("fonts/NotoSansCJKkr-Medium.otf").toExternalForm(), 12);
        
        labelName1.setFont(nameFont);
        labelName2.setFont(nameFont);
        labelName3.setFont(nameFont);
        labelName4.setFont(nameFont);
        
        labelSub1.setFont(subFont);
        labelSub2.setFont(subFont);
        labelSub3.setFont(subFont);
        labelSub4.setFont(subFont);
        
        Thread timeThread = new Thread() {
            @Override
            public void run() {
                SimpleDateFormat sdf1 = new SimpleDateFormat("HH:mm");
                SimpleDateFormat sdf2 = new SimpleDateFormat("MM월 dd일(E)");
                boolean stop = false;
                while (!stop) {
                    String nowTime = sdf1.format(new Date());
                    String nowDate = sdf2.format(new Date());
                    Platform.runLater(() -> {
                        labelTime.setText(nowTime);
                        labelDate.setText(nowDate);
                        
                        labelTime.setFont(timeFont);
                        labelTime.setLayoutX(800 - labelTime.getWidth() - 10);
                        labelTime.setLayoutY(0);

                        labelDate.setFont(dateFont);
                        labelDate.setLayoutX(labelTime.getLayoutX() - labelDate.getWidth() - 10);
                        labelDate.setLayoutY(10);
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
            KeyValue keyValue1 = new KeyValue(btnCall.translateXProperty(), -240);
            KeyValue keyValue1_1 = new KeyValue(labelSub1.translateXProperty(), -240);
            KeyFrame keyFrame1 = new KeyFrame(Duration.seconds(0.6), keyValue1);
            KeyFrame keyFrame1_1 = new KeyFrame(Duration.seconds(0.6), keyValue1_1);
            Timeline timeline1 = new Timeline();
            timeline1.getKeyFrames().add(keyFrame1);
            timeline1.getKeyFrames().add(keyFrame1_1);
            //Control 버튼 이동
            KeyValue keyValue2 = new KeyValue(btnControl.translateXProperty(), -120);
            KeyValue keyValue2_1 = new KeyValue(labelSub2.translateXProperty(), -120);
            KeyFrame keyFrame2 = new KeyFrame(Duration.seconds(0.4), keyValue2);
            KeyFrame keyFrame2_1 = new KeyFrame(Duration.seconds(0.4), keyValue2_1);
            Timeline timeline2 = new Timeline();
            timeline2.getKeyFrames().add(keyFrame2);
            timeline2.getKeyFrames().add(keyFrame2_1);
            //Lcck 버튼 이동
            KeyValue keyValue3 = new KeyValue(btnLock.translateXProperty(), 120);
            KeyValue keyValue3_1 = new KeyValue(labelSub3.translateXProperty(), 120);
            KeyFrame keyFrame3 = new KeyFrame(Duration.seconds(0.4), keyValue3);
            KeyFrame keyFrame3_1 = new KeyFrame(Duration.seconds(0.4), keyValue3_1);
            Timeline timeline3 = new Timeline();
            timeline3.getKeyFrames().add(keyFrame3);
            timeline3.getKeyFrames().add(keyFrame3_1);
            //Setting 버튼 이동
            KeyValue keyValue4 = new KeyValue(btnSetting.translateXProperty(), 240);
            KeyValue keyValue4_1 = new KeyValue(labelSub4.translateXProperty(), 240);
            KeyFrame keyFrame4 = new KeyFrame(Duration.seconds(0.6), keyValue4);
            KeyFrame keyFrame4_1 = new KeyFrame(Duration.seconds(0.6), keyValue4_1);
            Timeline timeline4 = new Timeline();
            timeline4.getKeyFrames().add(keyFrame4);
            timeline4.getKeyFrames().add(keyFrame4_1);
            //timeline 실행
            timeline1.play();
            timeline2.play();
            timeline3.play();
            timeline4.play();
        } else if (btnMenu.getUserData().equals("open")) {
            btnMenu.setUserData("close");
            //Call 버튼 이동
            KeyValue keyValue1 = new KeyValue(btnCall.translateXProperty(), 0);
            KeyValue keyValue1_1 = new KeyValue(labelSub1.translateXProperty(), 0);
            KeyFrame keyFrame1 = new KeyFrame(Duration.seconds(0.6), keyValue1);
            KeyFrame keyFrame1_1 = new KeyFrame(Duration.seconds(0.6), keyValue1_1);
            Timeline timeline1 = new Timeline();
            timeline1.getKeyFrames().add(keyFrame1);
            timeline1.getKeyFrames().add(keyFrame1_1);
            //Control 버튼 이동
            KeyValue keyValue2 = new KeyValue(btnControl.translateXProperty(), 0);
            KeyValue keyValue2_1 = new KeyValue(labelSub2.translateXProperty(), 0);
            KeyFrame keyFrame2 = new KeyFrame(Duration.seconds(0.4), keyValue2);
            KeyFrame keyFrame2_1 = new KeyFrame(Duration.seconds(0.4), keyValue2_1);
            Timeline timeline2 = new Timeline();
            timeline2.getKeyFrames().add(keyFrame2);
            timeline2.getKeyFrames().add(keyFrame2_1);
            //Lcck 버튼 이동
            KeyValue keyValue3 = new KeyValue(btnLock.translateXProperty(), 0);
            KeyValue keyValue3_1 = new KeyValue(labelSub3.translateXProperty(), 0);
            KeyFrame keyFrame3 = new KeyFrame(Duration.seconds(0.4), keyValue3);
            KeyFrame keyFrame3_1 = new KeyFrame(Duration.seconds(0.4), keyValue3_1);
            Timeline timeline3 = new Timeline();
            timeline3.getKeyFrames().add(keyFrame3);
            timeline3.getKeyFrames().add(keyFrame3_1);
            //Setting 버튼 이동
            KeyValue keyValue4 = new KeyValue(btnSetting.translateXProperty(), 0);
            KeyValue keyValue4_1 = new KeyValue(labelSub4.translateXProperty(), 0);
            KeyFrame keyFrame4 = new KeyFrame(Duration.seconds(0.6), keyValue4);
            KeyFrame keyFrame4_1 = new KeyFrame(Duration.seconds(0.6), keyValue4_1);
            Timeline timeline4 = new Timeline();
            timeline4.getKeyFrames().add(keyFrame4);
            timeline4.getKeyFrames().add(keyFrame4_1);
            //timeline 실행
            timeline1.play();
            timeline2.play();
            timeline3.play();
            timeline4.play();
        }
    }

}
