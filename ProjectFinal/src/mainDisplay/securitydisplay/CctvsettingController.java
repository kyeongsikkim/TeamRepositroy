package mainDisplay.securitydisplay;

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
import javafx.scene.control.Label;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.text.Font;
import javafx.stage.Popup;
import javafx.stage.Stage;

public class CctvsettingController implements Initializable {

    @FXML
    private MediaView mediaView;

    @FXML
    private ToggleButton btnApt;

    @FXML
    private ToggleButton btnDoor;

    @FXML
    private ToggleButton btnPlayground;

    @FXML
    private Label lblVisitlist;
    
    @FXML
    private Button btnList;

    @FXML
    private ToggleGroup menu1;

    public static MediaPlayer mediaPlayerApt;

    public static MediaPlayer mediaPlayerDoor;

    public static MediaPlayer mediaPlayerPlayground;

    public static AnchorPane listRoot;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btnList.setOnAction(event -> handleBtnList(event));
        
        // 폰트 설정 코드
        Font font = new Font(getClass().getResource("fonts/08SeoulNamsanEB.ttf").toExternalForm(), 14);
        btnApt.setFont(font);
        btnDoor.setFont(font);
        btnPlayground.setFont(font);
        lblVisitlist.setFont(font);

        // ToggleButton ToggleGroup으로 이벤트 처리(mediaPlayer 음소거로 설정하여 영상만 보이게 함)
        menu1.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            @Override
            public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {

                    // 공동현관 ToggleButton 눌렀을 때
                    if (newValue == btnApt) {
                        Media media = new Media(getClass().getResource("../videos/apt.m4v").toString());
                        mediaPlayerApt = new MediaPlayer(media);
                        mediaView.setMediaPlayer(mediaPlayerApt);
                        mediaPlayerApt.play();

                        btnDoor.setOnAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {
                                mediaPlayerApt.stop();
                            }
                        });

                        btnPlayground.setOnAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {
                                mediaPlayerApt.stop();
                            }
                        });

                    } // 주차장 ToggleButton 눌렀을 때
                    else if (newValue == btnDoor) {
                        Media media = new Media(getClass().getResource("../videos/parking.m4v").toString());
                        mediaPlayerDoor = new MediaPlayer(media);
                        mediaView.setMediaPlayer(mediaPlayerDoor);
                        mediaPlayerDoor.play();

                        btnApt.setOnAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {
                                mediaPlayerDoor.stop();
                            }
                        });
                        btnPlayground.setOnAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {
                                mediaPlayerDoor.stop();
                            }
                        });
                    } // 놀이터 ToggleButton 눌렀을 때
                    else if (newValue == btnPlayground) {
                        Media media = new Media(getClass().getResource("../videos/playground.m4v").toString());
                        mediaPlayerPlayground = new MediaPlayer(media);
                        mediaView.setMediaPlayer(mediaPlayerPlayground);
                        mediaPlayerPlayground.play();

                        btnApt.setOnAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {
                                mediaPlayerPlayground.stop();
                            }
                        });
                        btnDoor.setOnAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {
                                mediaPlayerPlayground.stop();
                            }
                        });
                    }

                    btnList.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            mediaPlayerApt.stop();
                            mediaPlayerDoor.stop();
                            mediaPlayerPlayground.stop();
                            handleBtnList(event);
                        }
                    });
                }
        });
    }

    // 방문목록 버튼 이벤트 처리
    private void handleBtnList(ActionEvent event) {
        try {
            Stage primaryStage = (Stage) btnList.getScene().getWindow();
            Popup popup = new Popup();
            Parent visitinglist = FXMLLoader.load(getClass().getResource("visitinglist.fxml"));
            Button popupClose = (Button) visitinglist.lookup("#btnClose");
            popupClose.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    popup.hide();
                    if (btnApt.isSelected()) {
                        mediaPlayerApt.play();
                    } else if (btnDoor.isSelected()) {
                        mediaPlayerDoor.play();
                    } else if (btnPlayground.isSelected()) {
                        mediaPlayerPlayground.play();
                    }
                }
            });
			
            popup.setHideOnEscape(true);
            popup.getContent().add(visitinglist);
            popup.show(primaryStage);

        } catch (Exception e) {
            // 개발 끝나면 이거 지워주기!
            e.printStackTrace();
        }
    }
}
