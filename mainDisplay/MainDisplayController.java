package mainDisplay;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import static javafx.scene.media.MediaPlayer.Status.PLAYING;
import javafx.scene.media.MediaView;
import javafx.scene.text.Font;
import javafx.stage.Popup;
import javafx.util.Duration;
import sun.audio.AudioPlayer;

public class MainDisplayController implements Initializable {

    private Socket socket;
    private Parent parent;
    
    private TableView<Notice> noticeTable;
    public static ObservableList<Notice> list;
    
    private Media media;
    private MediaPlayer mediaPlayer;

    @FXML
    private StackPane stackPane;
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private ImageView imgConnect;
    @FXML
    private Button btnMainNotice;
    @FXML
    private Label labelMainSlide;
    @FXML
    private Label labelMainDate;
    @FXML
    private Label labelMainTime;
    @FXML
    private Label labelMainBtnName1;
    @FXML
    private Label labelMainBtnName2;
    @FXML
    private Label labelMainBtnName3;
    @FXML
    private Label labelMainBtnName4;
    @FXML
    private Label labelMainBtnName5;
    @FXML
    private Button btnMainElevator;
    @FXML
    private Button btnMainOpenDoor;
    @FXML
    private Button btnMainInterphone;
    @FXML
    private Button btnMainMenu;
    @FXML
    private Button btnMainConnect;
    @FXML
    private Label labelMainNew;
    @FXML
    private Button btnMainMenuLock;
    @FXML
    private Button btnMainMenuSetting;
    @FXML
    private Button btnMainMenuControl;
    @FXML
    private Button btnMainMenuCall;
    @FXML
    private Label labelSubBtnName1;
    @FXML
    private Label labelSubBtnName2;
    @FXML
    private Label labelSubBtnName3;
    @FXML
    private Label labelSubBtnName4;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //공지 클라이언트 시작
        startClient();
        //공지 테이블 선언
        try {
            parent = FXMLLoader.load(getClass().getResource("notice.fxml"));
            list = FXCollections.observableArrayList();
        } catch (IOException ex) {
        }
        //폰트 선언
        Font timeFont = Font.loadFont(getClass().getResource("fonts/NanumBarunGothicBold.ttf").toExternalForm(), 56);
        Font dateFont = Font.loadFont(getClass().getResource("fonts/NanumBarunGothicBold.ttf").toExternalForm(), 28);
        Font nameFont = Font.loadFont(getClass().getResource("fonts/NanumBarunGothicBold.ttf").toExternalForm(), 16);
        Font slideFont = Font.loadFont(getClass().getResource("fonts/NanumBarunGothicBold.ttf").toExternalForm(), 15);
        Font subFont = Font.loadFont(getClass().getResource("fonts/NanumBarunGothicBold.ttf").toExternalForm(), 13);
        //버튼 라벨&서브 버튼 라벨 폰트 설정   
        labelMainBtnName1.setFont(nameFont);
        labelMainBtnName2.setFont(nameFont);
        labelMainBtnName3.setFont(nameFont);
        labelMainBtnName4.setFont(nameFont);
        labelMainBtnName5.setFont(subFont);
        labelMainNew.setFont(nameFont);
        labelMainSlide.setFont(slideFont);
        labelSubBtnName1.setFont(subFont);
        labelSubBtnName2.setFont(subFont);
        labelSubBtnName3.setFont(subFont);
        labelSubBtnName4.setFont(subFont);
        //슬라이드 라벨 초기 위치 설정
        labelMainSlide.setLayoutY(-30);
        //시간&날짜 출력 및 시간&날짜 라벨 위치 조정
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
                        labelMainTime.setText(nowTime);
                        labelMainDate.setText(nowDate);

                        labelMainTime.setFont(timeFont);
                        labelMainTime.setLayoutX(800 - labelMainTime.getWidth() - 10);
                        labelMainTime.setLayoutY(0);

                        labelMainDate.setFont(dateFont);
                        labelMainDate.setLayoutX(labelMainTime.getLayoutX() - labelMainDate.getWidth() - 5);
                        labelMainDate.setLayoutY(10);
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
        //버튼 이벤트 선언
        btnMainNotice.setOnAction(e -> handleBtnMainNotice(e));
        btnMainElevator.setOnAction(e -> handleBtnMainElevator(e));
        btnMainOpenDoor.setOnAction(e -> handleBtnMainOpenDoor(e));
        btnMainInterphone.setOnAction(e -> handleBtnMainInterphone(e));
        btnMainMenu.setOnAction(e -> handleBtnMainMenu(e));
        btnMainConnect.setOnAction(e -> handleBtnMainConnect(e));
        btnMainMenuCall.setOnAction(e -> handleBtnMainMenuCall(e));
        btnMainMenuControl.setOnAction(e -> handleBtnMainMenuControl(e));
        btnMainMenuLock.setOnAction(e -> handleBtnMainMenuLock(e));
        btnMainMenuSetting.setOnAction(e -> handleBtnMainMenuSetting(e));
        //포커스 시 파란색 테두리 해제
        btnMainElevator.setFocusTraversable(false);
        btnMainOpenDoor.setFocusTraversable(false);
        btnMainInterphone.setFocusTraversable(false);
        btnMainMenu.setFocusTraversable(false);
        btnMainConnect.setFocusTraversable(false);
        btnMainMenuCall.setFocusTraversable(false);
        btnMainMenuControl.setFocusTraversable(false);
        btnMainMenuLock.setFocusTraversable(false);
        btnMainMenuSetting.setFocusTraversable(false);
    }

    private void handleBtnMainNotice(ActionEvent e) {
        if (btnMainNotice.getUserData().equals("close")) {
            try {
                anchorPane.getChildren().add(parent);

                parent.setTranslateX(60);
                parent.setTranslateY(10);

                labelMainNew.setText("");
                btnMainNotice.setUserData("open");
            } catch (Exception ex) {
            }
            return;
        } else if (btnMainNotice.getUserData().equals("open")) {
            anchorPane.getChildren().remove(parent);
            btnMainNotice.setUserData("close");
        }
    }
    
    private void handleBtnMainElevator(ActionEvent e) {
        Popup popup = new Popup();
        try {
            BorderPane borderPane = (BorderPane) FXMLLoader.load(getClass().getResource("elevatorPopup.fxml"));
            Label labelDisplay = (Label) borderPane.lookup("#labelDisplay");

            Font numFont = Font.loadFont(getClass().getResource("fonts/DS-DIGI.TTF").toExternalForm(), 108);
            labelDisplay.setFont(numFont);

            int floorNum = (int) (Math.random() * 15) + 1;
            labelDisplay.setText(String.valueOf(floorNum));

            popup.getContent().add(borderPane);
            popup.setAutoHide(true);
            popup.show(AppMain.primaryStage);
            
        } catch (IOException ex) {
        }
    }
    
    private void handleBtnMainOpenDoor(ActionEvent e) {
        if(mediaPlayer != null) {
            mediaPlayer.stop();
        }  
        media = new Media(getClass().getResource("sounds/openDoor.mp3").toString());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();

        setLabelMainSlide("현관문이 열렸습니다");
    }
    
    private void handleBtnMainInterphone(ActionEvent e) {
        Popup popup = new Popup();
        try {
            BorderPane borderPane = (BorderPane) FXMLLoader.load(getClass().getResource("interphonePopup.fxml"));
            MediaView mediaView = (MediaView) borderPane.lookup("#mediaViewInterphone");

            Media frontMedia = new Media(getClass().getResource("videos/video.m4v").toString());
            MediaPlayer frontMediaPlayer = new MediaPlayer(frontMedia);
            mediaView.setMediaPlayer(frontMediaPlayer);
            frontMediaPlayer.play();

            popup.getContent().add(borderPane);
            popup.setAutoHide(true);
            popup.show(AppMain.primaryStage);

            popup.setOnAutoHide((event) -> {
                frontMediaPlayer.stop();
            });
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    private void handleBtnMainMenu(ActionEvent e) {
        if (btnMainMenu.getUserData().equals("close")) {
            btnMainMenu.setUserData("open");
            //Call 버튼 이동
            KeyValue keyValue1 = new KeyValue(btnMainMenuCall.translateXProperty(), -240);
            KeyValue keyValue1_1 = new KeyValue(labelSubBtnName1.translateXProperty(), -240);
            KeyFrame keyFrame1 = new KeyFrame(Duration.seconds(0.5), keyValue1);
            KeyFrame keyFrame1_1 = new KeyFrame(Duration.seconds(0.5), keyValue1_1);
            Timeline timeline1 = new Timeline();
            timeline1.getKeyFrames().add(keyFrame1);
            timeline1.getKeyFrames().add(keyFrame1_1);
            //Control 버튼 이동
            KeyValue keyValue2 = new KeyValue(btnMainMenuControl.translateXProperty(), -120);
            KeyValue keyValue2_1 = new KeyValue(labelSubBtnName2.translateXProperty(), -120);
            KeyFrame keyFrame2 = new KeyFrame(Duration.seconds(0.3), keyValue2);
            KeyFrame keyFrame2_1 = new KeyFrame(Duration.seconds(0.3), keyValue2_1);
            Timeline timeline2 = new Timeline();
            timeline2.getKeyFrames().add(keyFrame2);
            timeline2.getKeyFrames().add(keyFrame2_1);
            //Lcck 버튼 이동
            KeyValue keyValue3 = new KeyValue(btnMainMenuLock.translateXProperty(), 120);
            KeyValue keyValue3_1 = new KeyValue(labelSubBtnName3.translateXProperty(), 120);
            KeyFrame keyFrame3 = new KeyFrame(Duration.seconds(0.3), keyValue3);
            KeyFrame keyFrame3_1 = new KeyFrame(Duration.seconds(0.3), keyValue3_1);
            Timeline timeline3 = new Timeline();
            timeline3.getKeyFrames().add(keyFrame3);
            timeline3.getKeyFrames().add(keyFrame3_1);
            //Setting 버튼 이동
            KeyValue keyValue4 = new KeyValue(btnMainMenuSetting.translateXProperty(), 240);
            KeyValue keyValue4_1 = new KeyValue(labelSubBtnName4.translateXProperty(), 240);
            KeyFrame keyFrame4 = new KeyFrame(Duration.seconds(0.5), keyValue4);
            KeyFrame keyFrame4_1 = new KeyFrame(Duration.seconds(0.5), keyValue4_1);
            Timeline timeline4 = new Timeline();
            timeline4.getKeyFrames().add(keyFrame4);
            timeline4.getKeyFrames().add(keyFrame4_1);
            //timeline 실행
            timeline1.play();
            timeline2.play();
            timeline3.play();
            timeline4.play();
        } else if (btnMainMenu.getUserData().equals("open")) {
            btnMainMenu.setUserData("close");
            //Call 버튼 이동
            KeyValue keyValue1 = new KeyValue(btnMainMenuCall.translateXProperty(), 0);
            KeyValue keyValue1_1 = new KeyValue(labelSubBtnName1.translateXProperty(), 0);
            KeyFrame keyFrame1 = new KeyFrame(Duration.seconds(0.5), keyValue1);
            KeyFrame keyFrame1_1 = new KeyFrame(Duration.seconds(0.5), keyValue1_1);
            Timeline timeline1 = new Timeline();
            timeline1.getKeyFrames().add(keyFrame1);
            timeline1.getKeyFrames().add(keyFrame1_1);
            //Control 버튼 이동
            KeyValue keyValue2 = new KeyValue(btnMainMenuControl.translateXProperty(), 0);
            KeyValue keyValue2_1 = new KeyValue(labelSubBtnName2.translateXProperty(), 0);
            KeyFrame keyFrame2 = new KeyFrame(Duration.seconds(0.3), keyValue2);
            KeyFrame keyFrame2_1 = new KeyFrame(Duration.seconds(0.3), keyValue2_1);
            Timeline timeline2 = new Timeline();
            timeline2.getKeyFrames().add(keyFrame2);
            timeline2.getKeyFrames().add(keyFrame2_1);
            //Lcck 버튼 이동
            KeyValue keyValue3 = new KeyValue(btnMainMenuLock.translateXProperty(), 0);
            KeyValue keyValue3_1 = new KeyValue(labelSubBtnName3.translateXProperty(), 0);
            KeyFrame keyFrame3 = new KeyFrame(Duration.seconds(0.3), keyValue3);
            KeyFrame keyFrame3_1 = new KeyFrame(Duration.seconds(0.3), keyValue3_1);
            Timeline timeline3 = new Timeline();
            timeline3.getKeyFrames().add(keyFrame3);
            timeline3.getKeyFrames().add(keyFrame3_1);
            //Setting 버튼 이동
            KeyValue keyValue4 = new KeyValue(btnMainMenuSetting.translateXProperty(), 0);
            KeyValue keyValue4_1 = new KeyValue(labelSubBtnName4.translateXProperty(), 0);
            KeyFrame keyFrame4 = new KeyFrame(Duration.seconds(0.5), keyValue4);
            KeyFrame keyFrame4_1 = new KeyFrame(Duration.seconds(0.5), keyValue4_1);
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

    private void handleBtnMainConnect(ActionEvent e) {
        if (btnMainConnect.getUserData().equals("disconnect")) {
            startClient();
        } else if (btnMainConnect.getUserData().equals("connect")) {
            stopClient();
        }
    }
    
    private void handleBtnMainMenuCall(ActionEvent e) {
        
    }

    private void handleBtnMainMenuControl(ActionEvent e) {
        
    }

    private void handleBtnMainMenuLock(ActionEvent e) {
        try {
            AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("securitydisplay/security_main.fxml"));
            System.out.println(anchorPane);
            stackPane.getChildren().add(anchorPane);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void handleBtnMainMenuSetting(ActionEvent e) {
        
    }

    private void setLabelMainSlide(String message) {
        Thread slideThread = new Thread() {
            @Override
            public void run() {
                Platform.runLater(() -> {
                    labelMainSlide.setText(message);
                    KeyValue keyValue = new KeyValue(labelMainSlide.layoutYProperty(), 18);
                    KeyFrame keyFrame = new KeyFrame(Duration.seconds(0.4), keyValue);
                    Timeline timeline = new Timeline();
                    timeline.getKeyFrames().add(keyFrame);
                    timeline.play();
                });

                try {
                    Thread.sleep(2500);
                } catch (InterruptedException ex) {
                }

                Platform.runLater(() -> {
                    KeyValue keyValue = new KeyValue(labelMainSlide.layoutYProperty(), -30);
                    KeyFrame keyFrame = new KeyFrame(Duration.seconds(0.4), keyValue);
                    Timeline timeline = new Timeline();
                    timeline.getKeyFrames().add(keyFrame);
                    timeline.play();
                });
            }
        };
        slideThread.start();
    }

    private void startClient() {
        Thread thread = new Thread() {
            @Override
            public void run() {
                try {
                    socket = new Socket();
                    socket.connect(new InetSocketAddress("localhost", 50001));

                    Platform.runLater(() -> {
                        btnMainConnect.setUserData("connect");
                        imgConnect.setImage(new Image(getClass().getResource("images/disconnect.png").toString()));
                        labelMainBtnName5.setText("공지서버 연결 중지");
                        setLabelMainSlide("공지서버에 연결되었습니다.");
                    });

                    receive();

                } catch (Exception ex) {
                    return;
                }
            }
        };
        thread.start();
    }

    private void stopClient() {
        try {
            socket.close();
        } catch (IOException ex) {
        }
        Platform.runLater(() -> {
            btnMainConnect.setUserData("disconnect");
            imgConnect.setImage(new Image(getClass().getResource("images/connect.png").toString()));
            labelMainBtnName5.setText("공지서버 연결");
            setLabelMainSlide("공지서버와의 연결이 끊겼습니다.");
        });

    }

    private void receive() {
        try {
            while (true) {
                InputStream is1 = socket.getInputStream();
                byte[] byteArr1 = new byte[100];
                int readBytes1 = is1.read(byteArr1);
                if (readBytes1 == -1) {
                    throw new Exception();
                }
                String strData1 = new String(byteArr1, 0, readBytes1);

                InputStream is2 = socket.getInputStream();
                byte[] byteArr2 = new byte[100];
                int readBytes2 = is2.read(byteArr2);
                if (readBytes2 == -1) {
                    throw new Exception();
                }
                String strData2 = new String(byteArr2, 0, readBytes2);
                System.out.println("제목: " + strData1);
                System.out.println("내용: " + strData2);

                Platform.runLater(() -> {
                    TableView<Notice> noticeTable = (TableView) parent.lookup("#tableView");

                    TableColumn tc0 = noticeTable.getColumns().get(0);
                    TableColumn tc1 = noticeTable.getColumns().get(1);

                    tc0.setCellValueFactory(new PropertyValueFactory<Notice, String>("date"));
                    tc1.setCellValueFactory(new PropertyValueFactory<Notice, String>("title"));

                    SimpleDateFormat sdf = new SimpleDateFormat("MM월 dd일(E) HH:mm");
                    list.add(new Notice(sdf.format(new Date()), strData1, strData2));
                    noticeTable.setItems(list);

                    if (btnMainNotice.getUserData().equals("close")) {
                        labelMainNew.setText("N");
                    }

                });

            }
        } catch (Exception ex) {
        }
    }

}
