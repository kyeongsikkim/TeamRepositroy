package mainDisplay.securitydisplay;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.text.Font;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.util.Duration;

public class ExitsettingController implements Initializable {

    @FXML
    private Label lblGetinText;

    @FXML
    private Label lblGetoutText;

    @FXML
    private ImageView upperImage1;

    @FXML
    private ImageView upperImage2;

    @FXML
    private ImageView lowerImage1;

    @FXML
    private ImageView lowerImage2;

    @FXML
    private Button btnExitSetting;

    @FXML
    private CheckBox checkMotion;

    @FXML
    private CheckBox checkGas;

    @FXML
    private CheckBox checkHeater;

    @FXML
    private Button btnHighEnter;

    @FXML
    private Button btnLowEnter;

    @FXML
    private Button btnHighLeave;

    @FXML
    private Button btnLowLeave;

    @FXML
    private MediaView soundlock;

    @FXML
    private MediaView soundunlock;

    @FXML
    private TextField txtGetinTime;

    @FXML
    private TextField txtGetoutTime;

    @FXML
    private CheckBox btnFlame;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Font font =
        // Font.loadFont(getClass().getResource("fonts/NotoSansCJKkr-Medium.otf").toExternalForm(),6);
        // lblGetinText.setFont(font);

        // 입실(퇴실)지연조절 화살표 이미지 처리 코드
        Image upperImage = new Image(getClass().getResource("image/upper arrow.png").toString());
        Image lowerImage = new Image(getClass().getResource("image/lower arrow.png").toString());
        upperImage1.setImage(upperImage);
        upperImage2.setImage(upperImage);
        lowerImage1.setImage(lowerImage);
        lowerImage2.setImage(lowerImage);

        btnExitSetting.setOnAction(event -> {
            try {
                handleBtnExitSetting(event);
            } catch (IOException ioe) {
                // 개발 끝나면 지워주기
                ioe.printStackTrace();
            }
        });

        // 입실지연시간 증가버튼 이벤트 처리
        btnHighEnter.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String str = txtGetinTime.getText();
                String[] str1 = str.split("초");
                int second = Integer.parseInt(str1[0]);
                txtGetinTime.setText(second + 5 + "초");
                if (second >= 255) {
                    txtGetinTime.setText("255초");
                }
            }
        });

        // 입실지연시간 감소버튼 이벤트처리
        btnLowEnter.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String str = txtGetinTime.getText();
                String[] str1 = str.split("초");
                int second = Integer.parseInt(str1[0]);
                txtGetinTime.setText(second - 5 + "초");
                if (second <= 0) {
                    txtGetinTime.setText("0초");
                }
            }
        });

        // 퇴실지연시간 증가버튼 이벤트처리
        btnHighLeave.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String str = txtGetoutTime.getText();
                String[] str1 = str.split("초");
                int second = Integer.parseInt(str1[0]);
                txtGetoutTime.setText(second + 5 + "초");
                if (second >= 255) {
                    txtGetoutTime.setText("255초");
                }
            }
        });

        // 퇴실지연시간 감소버튼 이벤트 처리
        btnLowLeave.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String str = txtGetoutTime.getText();
                String[] str1 = str.split("초");
                int second = Integer.parseInt(str1[0]);
                txtGetoutTime.setText(second - 5 + "초");
                if (second <= 0) {
                    txtGetoutTime.setText("0초");
                }
            }
        });
    }

    // 외출/재택방범 설정 버튼 이벤트 처리
    private void handleBtnExitSetting(ActionEvent event) throws IOException {

        if (btnExitSetting.getText().equals("설정")) {
            Stage primaryStage = (Stage) btnExitSetting.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("password.fxml"));
            Popup popup = new Popup();
            popup.getContent().add(root);
            popup.show(primaryStage);

            Button cancel = (Button) root.lookup("#popupClose");
            Button confirm = (Button) root.lookup("#popupConfirm");
            TextField password = (TextField) root.lookup("#txtNumber");
            StackPane errorPopup = (StackPane) root.lookup("#passErrorPopup");
            cancel.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent event) {
                    popup.hide();
                }

            });
            confirm.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent event) {
                    if (PasswordController.password.equals(password.getText())) {
                        btnExitSetting.setText("해제");
                        Media media = new Media(getClass().getResource("media/외출-재택방범이_설정되었습니다.mp3").toString());
                        MediaPlayer mediaPlayer = new MediaPlayer(media);
                        soundlock.setMediaPlayer(mediaPlayer);
                        mediaPlayer.play();

                        checkMotion.setDisable(true);
                        checkGas.setDisable(true);
                        checkHeater.setDisable(true);
                        btnLowEnter.setDisable(true);
                        btnHighEnter.setDisable(true);
                        btnLowLeave.setDisable(true);
                        btnHighLeave.setDisable(true);
                        btnFlame.setDisable(true);

                        popup.hide();

                    } else {
                        try {
                            Parent parent = FXMLLoader.load(getClass().getResource("errorpopup.fxml"));
                            Label message = (Label) parent.lookup("#lblText");
                            if (password.getText().equals("")) {
                                message.setText("비밀번호를 입력하세요");
                            } else {
                                message.setText("비밀번호가 맞지 않습니다");
                            }

                            if (!errorPopup.getChildren().isEmpty()) {
                                errorPopup.getChildren().clear();
                            }

                            errorPopup.getChildren().add(parent);

                            parent.setTranslateX(225);
                            parent.setTranslateY(0);

                            Timeline timeline = new Timeline();
                            KeyValue keyValue = new KeyValue(parent.translateYProperty(), 20);
                            KeyFrame keyFrame = new KeyFrame(Duration.millis(1000), keyValue);
                            timeline.getKeyFrames().add(keyFrame);
                            timeline.play();

                        } catch (IOException e) {
                            // 개발 끝나면 지워주기
                            e.printStackTrace();
                        }
                        password.setText("");
                    }
                }
            });

        } else if (btnExitSetting.getText().equals("해제")) {

            Media media = new Media(getClass().getResource("media/해제되었습니다.mp3").toString());
            MediaPlayer mediaPlayer = new MediaPlayer(media);
            soundunlock.setMediaPlayer(mediaPlayer);
            mediaPlayer.play();

            checkMotion.setDisable(false);
            checkGas.setDisable(false);
            checkHeater.setDisable(false);
            btnLowEnter.setDisable(false);
            btnHighEnter.setDisable(false);
            btnLowLeave.setDisable(false);
            btnHighLeave.setDisable(false);
            btnFlame.setDisable(false);

            btnExitSetting.setText("설정");
        }
    }
}
