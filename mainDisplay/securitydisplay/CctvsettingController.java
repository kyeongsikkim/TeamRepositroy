package mainDisplay.securitydisplay;

import java.io.IOException;
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
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

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
    private Button btnList;

    @FXML
    private ToggleGroup menu1;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btnList.setOnAction(event -> handleBtnList(event));

        // ToggleButton ToggleGroup으로 이벤트 처리(mediaPlayer 음소거로 설정하여 영상만 보이게 함)
        menu1.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            @Override
            public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {
                try {
                    Media media = new Media(getClass().getResource("media/video.mp4").toString());
                    MediaPlayer mediaPlayer = new MediaPlayer(media);
                    mediaView.setMediaPlayer(mediaPlayer);

                    //이 버튼이 제대로 작동하지 않음. 이거 질문
                    Parent root = FXMLLoader.load(getClass().getResource("security_main.fxml"));
                    ToggleButton btnExitTab = (ToggleButton) root.lookup("#btnExitSettingTab");

                    // 공동현관 ToggleButton 눌렀을 때
                    if (newValue == btnApt) {

                        mediaPlayer.play();

                        btnDoor.setOnAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {
                                mediaPlayer.stop();
                            }
                        });
                        btnPlayground.setOnAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {
                                mediaPlayer.stop();
                            }
                        });

                        btnList.setOnAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {
                                mediaPlayer.stop();
                                try {
                                    Parent visitinglist = FXMLLoader.load(getClass().getResource("visitinglist.fxml"));
                                    AnchorPane root = (AnchorPane) btnList.getScene().getRoot();
                                    root.getChildren().add(visitinglist);
                                } catch (Exception e) {
                                    // 개발 끝나면 이거 지워주기!
                                    e.printStackTrace();
                                }
                            }
                        });
                    } // 현관문 ToggleButton 눌렀을 때
                    else if (newValue == btnDoor) {

                        mediaPlayer.play();

                        btnApt.setOnAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {
                                mediaPlayer.stop();
                            }
                        });
                        btnPlayground.setOnAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {
                                mediaPlayer.stop();
                            }
                        });
                        btnList.setOnAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {
                                mediaPlayer.stop();
                                try {
                                    Parent visitinglist = FXMLLoader.load(getClass().getResource("visitinglist.fxml"));
                                    AnchorPane root = (AnchorPane) btnList.getScene().getRoot();
                                    root.getChildren().add(visitinglist);
                                } catch (Exception e) {
                                    // 개발 끝나면 이거 지워주기!
                                    e.printStackTrace();
                                }
                            }
                        });
                    } // 놀이터 ToggleButton 눌렀을 때
                    else if (newValue == btnPlayground) {

                        mediaPlayer.play();

                        btnApt.setOnAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {
                                mediaPlayer.stop();
                            }
                        });
                        btnDoor.setOnAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {
                                mediaPlayer.stop();
                            }
                        });
                        btnList.setOnAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {
                                mediaPlayer.stop();
                                try {
                                    Parent visitinglist = FXMLLoader.load(getClass().getResource("visitinglist.fxml"));
                                    AnchorPane root = (AnchorPane) btnList.getScene().getRoot();
                                    root.getChildren().add(visitinglist);
                                } catch (Exception e) {
                                    // 개발 끝나면 이거 지워주기!
                                    e.printStackTrace();
                                }
                            }
                        });
                    }
                } catch (IOException ioe) {
                    // 개발 끝나면 지워주기
                    ioe.printStackTrace();
                }
            }
        });
    }

    // 방문목록 버튼 이벤트 처리
    private void handleBtnList(ActionEvent event) {
        try {
            Parent visitinglist = FXMLLoader.load(getClass().getResource("visitinglist.fxml"));
            AnchorPane root = (AnchorPane) btnList.getScene().getRoot();
            root.getChildren().add(visitinglist);
        } catch (Exception e) {
            // 개발 끝나면 이거 지워주기!
            e.printStackTrace();
        }
    }
}
