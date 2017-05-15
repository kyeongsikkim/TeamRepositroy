package mainDisplay.securitydisplay;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
import javafx.scene.text.Font;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.util.Duration;
import mainDisplay.setting.Status;

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
    private CheckBox checkGas;

    @FXML
    private CheckBox checkHeater;

    @FXML
    private CheckBox checkFlame;

    @FXML
    private Button btnExitSetting;

    @FXML
    private Button btnHighEnter;

    @FXML
    private Button btnLowEnter;

    @FXML
    private Button btnHighLeave;

    @FXML
    private Button btnLowLeave;

    @FXML
    private TextField txtGetinTime;

    @FXML
    private TextField txtGetoutTime;

    private MediaPlayer mediaPlayer1;

    private MediaPlayer mediaPlayer2;

    static DataSaved data = new DataSaved();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // 폰트 설정 
        Font font = new Font(getClass().getResource("fonts/08SeoulNamsanEB.ttf").toExternalForm(), 14);
        //lblGetinText.setFont(font);
        //lblGetoutText.setFont(font);
        checkGas.setFont(font);
        checkHeater.setFont(font);
        checkFlame.setFont(font);
        btnExitSetting.setFont(font);
        txtGetinTime.setFont(font);
        txtGetoutTime.setFont(font);

        // 초기 설정값 불러오기
        if (data.getTxtGetin() == null) {
            data.setTxtGetin("10분");
        }
        txtGetinTime.setText(data.getTxtGetin());
        if (data.getTxtGetout() == null) {
            data.setTxtGetout("5분");
        }
        txtGetoutTime.setText(data.getTxtGetout());

        if (data.getCheckGas() == null) {
            data.setCheckGas(false);
        }
        checkGas.selectedProperty().set(data.getCheckGas());

        if (data.getCheckHeater() == null) {
            data.setCheckHeater(false);
        }
        checkHeater.selectedProperty().set(data.getCheckHeater());

        if (data.getCheckFlame() == null) {
            data.setCheckFlame(false);
        }
        checkFlame.selectedProperty().set(data.getCheckFlame());

        if (data.getBtnExitSetting() == null) {
            data.setBtnExitSetting("설정");
        }
        btnExitSetting.setText(data.getBtnExitSetting());

        if (data.getBtnHighEnter() == null) {
            data.setBtnHighEnter(false);
        }
        btnHighEnter.disableProperty().set(data.getBtnHighEnter());

        if (data.getBtnHighLeave() == null) {
            data.setBtnHighLeave(false);
        }
        btnHighLeave.disableProperty().set(data.getBtnHighLeave());

        if (data.getBtnLowEnter() == null) {
            data.setBtnLowEnter(false);
        }
        btnLowEnter.disableProperty().set(data.getBtnLowEnter());

        if (data.getBtnLowLeave() == null) {
            data.setBtnLowLeave(false);
        }
        btnLowLeave.disableProperty().set(data.getBtnLowLeave());

        if (data.getCheckGasDisable() == null) {
            data.setCheckGasDisable(false);
        }
        checkGas.disableProperty().set(data.getCheckGasDisable());

        if (data.getCheckHeaterDisable() == null) {
            data.setCheckHeaterDisable(false);
        }
        checkHeater.disableProperty().set(data.getCheckHeaterDisable());

        if (data.getCheckFlameDisable() == null) {
            data.setCheckFlameDisable(false);
        }
        checkFlame.disableProperty().set(data.getCheckFlameDisable());

        checkGas.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (newValue == true) {
                    data.setCheckGas(newValue);
                }
                data.setCheckGas(newValue);
            }
        });

        checkHeater.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (newValue == true) {
                    data.setCheckHeater(newValue);
                }
                data.setCheckHeater(newValue);
            }
        });

        checkFlame.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (newValue == true) {
                    data.setCheckFlame(newValue);
                }
                data.setCheckFlame(newValue);
            }
        });

        /*
        Font font = Font.loadFont(getClass().getResource("fonts/NotoSansCJKkr-Medium.otf").toExternalForm(), 6);
        lblGetinText.setFont(font);
        lblGetoutText.setFont(font);
         */
        // 입실(퇴실)지연조절 화살표 이미지 처리 코드
        Image lowerImage = new Image(getClass().getResource("image/arrow_left.png").toString());
        Image upperImage = new Image(getClass().getResource("image/arrow_right.png").toString());
        upperImage1.setImage(lowerImage);
        upperImage2.setImage(lowerImage);
        lowerImage1.setImage(upperImage);
        lowerImage2.setImage(upperImage);

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
                String[] str1 = txtGetinTime.getText().split("분");
                int second = Integer.parseInt(str1[0]);
                txtGetinTime.setText(second + 1 + "분");
                data.setTxtGetin(txtGetinTime.getText());
                if (second >= 20) {
                    txtGetinTime.setText("20분");
                    data.setTxtGetin(txtGetinTime.getText());
                }

            }
        });

        // 입실지연시간 감소버튼 이벤트처리
        btnLowEnter.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String[] str1 = txtGetinTime.getText().split("분");
                int second = Integer.parseInt(str1[0]);
                txtGetinTime.setText(second - 1 + "분");
                data.setTxtGetin(txtGetinTime.getText());
                if (second <= 0) {
                    txtGetinTime.setText("0분");
                    data.setTxtGetin(txtGetinTime.getText());
                }
            }
        });

        // 퇴실지연시간 증가버튼 이벤트처리
        btnHighLeave.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String[] str1 = txtGetoutTime.getText().split("분");
                int second = Integer.parseInt(str1[0]);
                txtGetoutTime.setText(second + 1 + "분");
                data.setTxtGetout(txtGetoutTime.getText());
                if (second >= 20) {
                    txtGetoutTime.setText("20분");
                    data.setTxtGetout(txtGetoutTime.getText());
                }
            }
        });

        // 퇴실지연시간 감소버튼 이벤트 처리
        btnLowLeave.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String str = txtGetoutTime.getText();
                String[] str1 = str.split("분");
                int second = Integer.parseInt(str1[0]);
                txtGetoutTime.setText(second - 1 + "분");
                data.setTxtGetout(txtGetoutTime.getText());
                if (second <= 0) {
                    txtGetoutTime.setText("0분");
                    data.setTxtGetout(txtGetoutTime.getText());
                }
            }
        });
    }

    // 외출/재택방범 설정 버튼 이벤트 처리
    private void handleBtnExitSetting(ActionEvent event) throws IOException {

        // audio파일 재생 초기화
        if (btnExitSetting.getText().equals("설정")) {
            Media media = new Media(getClass().getResource("../videos/lock.mp3").toString());
            mediaPlayer1 = new MediaPlayer(media);
        } else if (btnExitSetting.getText().equals("해제")) {
            Media media = new Media(getClass().getResource("../videos/unlock.mp3").toString());
            mediaPlayer2 = new MediaPlayer(media);
        }

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

                    if (Status.sta.getTxtPass().equals(password.getText())) {
                        btnExitSetting.setText("해제");
                        data.setBtnExitSetting(btnExitSetting.getText());
                        mediaPlayer1.play();

                        if (mediaPlayer2 != null) {
                            mediaPlayer2.stop();
                        }

                        checkGas.setDisable(true);
                        checkFlame.setDisable(true);
                        checkHeater.setDisable(true);
                        btnLowEnter.setDisable(true);
                        btnHighEnter.setDisable(true);
                        btnLowLeave.setDisable(true);
                        btnHighLeave.setDisable(true);

                        data.setBtnHighEnter(btnHighEnter.disableProperty().get());
                        data.setBtnHighLeave(btnHighLeave.disableProperty().get());
                        data.setBtnLowEnter(btnLowEnter.disableProperty().get());
                        data.setBtnLowLeave(btnLowLeave.disableProperty().get());
                        data.setCheckGasDisable(checkGas.disableProperty().get());
                        data.setCheckHeaterDisable(checkHeater.disableProperty().get());
                        data.setCheckFlameDisable(checkFlame.disableProperty().get());

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
                            KeyFrame keyFrame = new KeyFrame(Duration.millis(10), keyValue);
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

            mediaPlayer2.play();

            if (mediaPlayer1 != null) {
                mediaPlayer1.stop();
            }

            checkGas.setDisable(false);
            checkFlame.setDisable(false);
            checkHeater.setDisable(false);
            btnLowEnter.setDisable(false);
            btnHighEnter.setDisable(false);
            btnLowLeave.setDisable(false);
            btnHighLeave.setDisable(false);

            data.setBtnHighEnter(btnHighEnter.disableProperty().get());
            data.setBtnHighLeave(btnHighLeave.disableProperty().get());
            data.setBtnLowEnter(btnLowEnter.disableProperty().get());
            data.setBtnLowLeave(btnLowLeave.disableProperty().get());
            data.setCheckGasDisable(checkGas.disableProperty().get());
            data.setCheckHeaterDisable(checkHeater.disableProperty().get());
            data.setCheckFlameDisable(checkFlame.disableProperty().get());

            btnExitSetting.setText("설정");
            data.setBtnExitSetting(btnExitSetting.getText());
        }
    }
}
