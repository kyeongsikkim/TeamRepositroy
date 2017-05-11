package mainDisplay;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
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
import javafx.scene.media.MediaView;
import javafx.scene.text.Font;
import javafx.stage.Popup;
import javafx.util.Duration;

public class MainDisplayController implements Initializable {
    //음성 재생용 미디어, 미디어플레이어 선언
    private Media media;
    private MediaPlayer mediaPlayer;
    //영상 재생용 미디어, 미디어플레이어 선언
    private Media media2;
    private MediaPlayer mediaPlayer2;
    //소켓 선언
    private Socket socket;
    //공지사항 pane 및 테이블에 들어갈 List 선언
    private BorderPane noticeBorderPane;
    public static ObservableList<Notice> list;
    
    @FXML
    private StackPane stackPane;
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private Label labelMainDate;
    @FXML
    private Label labelMainTime;
    @FXML
    private Button btnMainElevator;
    @FXML
    private Button btnMainOpenDoor;
    @FXML
    private Button btnMainInterphone;
    @FXML
    private Label labelMainBtnName5;
    @FXML
    private Button btnMainMenuLock;
    @FXML
    private Button btnMainMenuSetting;
    @FXML
    private Button btnMainMenuControl;
    @FXML
    private Button btnMainMenuCall;
    @FXML
    private Label labelMainBtnName1;
    @FXML
    private Label labelMainBtnName2;
    @FXML
    private Label labelMainBtnName3;
    @FXML
    private Label labelMainBtnName4;
    @FXML
    private Label labelSubBtnName1;
    @FXML
    private Label labelSubBtnName2;
    @FXML
    private Label labelSubBtnName3;
    @FXML
    private Label labelSubBtnName4;
    @FXML
    private Button btnMainMenu;
    @FXML
    private Button btnMainNotice;
    @FXML
    private Button btnMainConnect;
    @FXML
    private ImageView imgConnect;
    @FXML
    private Label labelMainNew;
    @FXML
    private Label labelMainSlide;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //메인디스플레이 상단 날짜,시간 출력 메소드 실행
        startDateTime();
        //폰트 설정 메소드 실행
        setMainFont();
        //공지사항 pane 및 테이블에 들어갈 List 선언
        try {
            noticeBorderPane = FXMLLoader.load(getClass().getResource("notice.fxml"));
            list = FXCollections.observableArrayList();
        } catch (IOException ex) {
        }
        //각 버튼별 이벤트 메소드 지정
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
    }    

    private void startDateTime() {
        //시간,날짜 라벨 폰트 선언
        Font timeFont = Font.loadFont(getClass().getResource("fonts/NanumBarunGothicBold.ttf").toExternalForm(), 56);
        Font dateFont = Font.loadFont(getClass().getResource("fonts/NanumBarunGothicBold.ttf").toExternalForm(), 28);
        //시간,날짜 데이터 읽기, 라벨에 출력하기, 라벨 위치 설정하기
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
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                    }
                }
            }
        };
        timeThread.setDaemon(true);
        timeThread.start();
    }

    private void setMainFont() {
        //폰트 선언
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
    }

    private void handleBtnMainNotice(ActionEvent e) {
        if (btnMainNotice.getUserData().equals("close")) {
            openNotice();
        } else if (btnMainNotice.getUserData().equals("open")) {
            closeNotice();
        }
    }
    
    private void openNotice() {
        try {
            btnMainNotice.setUserData("open");

            anchorPane.getChildren().add(noticeBorderPane);
            noticeBorderPane.setTranslateX(60);
            noticeBorderPane.setTranslateY(10);

            labelMainNew.setText("");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void closeNotice() {
        btnMainNotice.setUserData("close");
        anchorPane.getChildren().remove(noticeBorderPane);
    }

    private void handleBtnMainElevator(ActionEvent e) {
        Popup popup = new Popup();
        try {
            BorderPane borderPane = (BorderPane) FXMLLoader.load(getClass().getResource("elevatorPopup.fxml"));
            Label labelDisplay = (Label) borderPane.lookup("#labelDisplay");
            Button btnElevatorUp = (Button) borderPane.lookup("#btnElevatorUp");
            Button btnElevatorDown = (Button) borderPane.lookup("#btnElevatorDown");

            Font numFont = Font.loadFont(getClass().getResource("fonts/DS-DIGI.TTF").toExternalForm(), 108);
            labelDisplay.setFont(numFont);

            int floorNum = (int) (Math.random() * 15) + 1;
            labelDisplay.setText(String.valueOf(floorNum));
            
            btnElevatorUp.setOnAction(e2 -> {
                media = new Media(getClass().getResource("sounds/elevatorUp.mp3").toString());
                mediaPlayer = new MediaPlayer(media);

                Thread elevThread = new Thread() {
                    @Override
                    public void run() {
                        while (true) {
                            int floor = Integer.parseInt(labelDisplay.getText());
                            if (floor < 12) {
                                try {
                                    Thread.sleep(700);
                                } catch (InterruptedException ex) {
                                }
                                Platform.runLater(() -> {
                                    labelDisplay.setText(String.valueOf(floor + 1));
                                });
                            } else if (floor > 12) {
                                try {
                                    Thread.sleep(700);
                                } catch (InterruptedException ex) {
                                }
                                Platform.runLater(() -> {
                                    labelDisplay.setText(String.valueOf(floor - 1));
                                });
                            } else {
                                Platform.runLater(() -> {
                                    mediaPlayer.play();
                                    mediaPlayerDisposer();
                                });
                                break;
                            }
                        }
                    }
                };
                elevThread.start();
            });
            
            btnElevatorDown.setOnAction(e2 -> {
                media = new Media(getClass().getResource("sounds/elevatorDown.mp3").toString());
                mediaPlayer = new MediaPlayer(media);

                Thread elevThread = new Thread() {
                    @Override
                    public void run() {
                        while (true) {
                            int floor = Integer.parseInt(labelDisplay.getText());
                            if (floor < 12) {
                                try {
                                    Thread.sleep(700);
                                } catch (InterruptedException ex) {
                                }
                                Platform.runLater(() -> {
                                    labelDisplay.setText(String.valueOf(floor + 1));
                                });
                            } else if (floor > 12) {
                                try {
                                    Thread.sleep(700);
                                } catch (InterruptedException ex) {
                                }
                                Platform.runLater(() -> {
                                    labelDisplay.setText(String.valueOf(floor - 1));
                                });
                            } else {
                                Platform.runLater(() -> {
                                    mediaPlayer.play();
                                    mediaPlayerDisposer();  
                                });
                                break;
                            }
                        }
                    }
                };
                elevThread.start();
            });

            popup.getContent().add(borderPane);
            popup.setAutoHide(true);
            popup.show(AppMain.primaryStage);
        } catch (IOException ex) {
        }
    }
    
    private void handleBtnMainOpenDoor(ActionEvent e) {
        if(mediaPlayer != null) {
            mediaPlayer.dispose();
        }
        
        media = new Media(getClass().getResource("sounds/openDoor.mp3").toString());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();
        
        mediaPlayerDisposer();
        setLabelMainSlide("현관문이 열렸습니다");
    }

    private void handleBtnMainInterphone(ActionEvent e) {
        Popup popup = new Popup();
        try {
            BorderPane borderPane = (BorderPane) FXMLLoader.load(getClass().getResource("interphonePopup.fxml"));
            MediaView mediaView = (MediaView) borderPane.lookup("#mediaViewInterphone");
            Button btnInterphoneOpenDoor = (Button) borderPane.lookup("#btnInterphoneOpenDoor");
            Button btnInterphoneSave = (Button) borderPane.lookup("#btnInterphoneSave");
            
            media2 = new Media(getClass().getResource("videos/video.m4v").toString());
            mediaPlayer2 = new MediaPlayer(media2);
            mediaView.setMediaPlayer(mediaPlayer2);
            mediaPlayer2.play();
            
            btnInterphoneOpenDoor.setOnAction(e2 -> handleBtnMainOpenDoor(e2));
            btnInterphoneSave.setOnAction(e2 -> handleBtnInterphoneSave(e2));
            
            popup.getContent().add(borderPane);
            popup.setAutoHide(true);
            popup.show(AppMain.primaryStage);

            popup.setOnAutoHide((event) -> {
                mediaPlayer2.stop();
                mediaPlayer2.dispose();
                
                System.gc();
            });
			
        } catch (IOException ex) {
        }
    }
    
    private void handleBtnInterphoneSave(ActionEvent e) {
        if(mediaPlayer != null) {
            mediaPlayer.dispose();
        }
        media = new Media(getClass().getResource("sounds/interphone.mp3").toString());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();
        
        mediaPlayerDisposer();
        
        String mediaUriTemp = media2.getSource();
        String mediaUri = mediaUriTemp.substring(6);
        
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
            fos.close();
            fis.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void handleBtnMainMenu(ActionEvent e) {
        if (btnMainMenu.getUserData().equals("close")) {
            openMenuAnimation();
        } else if (btnMainMenu.getUserData().equals("open")) {
            closeMenuAnimation();
        }
    }
    
    private void openMenuAnimation() {
        //menu 버튼 유저데이터 세팅
        btnMainMenu.setUserData("open");
        //Call 버튼 애니메이션
        KeyValue keyValue1_1 = new KeyValue(btnMainMenuCall.translateXProperty(), -240);
        KeyValue keyValue1_2 = new KeyValue(labelSubBtnName1.translateXProperty(), -240);
        KeyFrame keyFrame1_1 = new KeyFrame(Duration.seconds(0.5), keyValue1_1);
        KeyFrame keyFrame1_2 = new KeyFrame(Duration.seconds(0.5), keyValue1_2);
        //Control 버튼 애니메이션
        KeyValue keyValue2_1 = new KeyValue(btnMainMenuControl.translateXProperty(), -120);
        KeyValue keyValue2_2 = new KeyValue(labelSubBtnName2.translateXProperty(), -120);
        KeyFrame keyFrame2_1 = new KeyFrame(Duration.seconds(0.4), keyValue2_1);
        KeyFrame keyFrame2_2 = new KeyFrame(Duration.seconds(0.4), keyValue2_2);
        //Lock 버튼 애니메이션
        KeyValue keyValue3_1 = new KeyValue(btnMainMenuLock.translateXProperty(), 120);
        KeyValue keyValue3_2 = new KeyValue(labelSubBtnName3.translateXProperty(), 120);
        KeyFrame keyFrame3_1 = new KeyFrame(Duration.seconds(0.4), keyValue3_1);
        KeyFrame keyFrame3_2 = new KeyFrame(Duration.seconds(0.4), keyValue3_2);
        //Setting 버튼 애니메이션
        KeyValue keyValue4_1 = new KeyValue(btnMainMenuSetting.translateXProperty(), 240);
        KeyValue keyValue4_2 = new KeyValue(labelSubBtnName4.translateXProperty(), 240);
        KeyFrame keyFrame4_1 = new KeyFrame(Duration.seconds(0.5), keyValue4_1);
        KeyFrame keyFrame4_2 = new KeyFrame(Duration.seconds(0.5), keyValue4_2);
        //Timeline 생성 및 세팅
        Timeline timeline = new Timeline();
        timeline.getKeyFrames().add(keyFrame1_1);
        timeline.getKeyFrames().add(keyFrame1_2);
        timeline.getKeyFrames().add(keyFrame2_1);
        timeline.getKeyFrames().add(keyFrame2_2);
        timeline.getKeyFrames().add(keyFrame3_1);
        timeline.getKeyFrames().add(keyFrame3_2);
        timeline.getKeyFrames().add(keyFrame4_1);
        timeline.getKeyFrames().add(keyFrame4_2);
        //Timeline 실행
        timeline.play();
    }
    
    private void closeMenuAnimation() {
        //menu 버튼 유저데이터 세팅
        btnMainMenu.setUserData("close");
        //Call 버튼 애니메이션
        KeyValue keyValue1_1 = new KeyValue(btnMainMenuCall.translateXProperty(), 0);
        KeyValue keyValue1_2 = new KeyValue(labelSubBtnName1.translateXProperty(), 0);
        KeyFrame keyFrame1_1 = new KeyFrame(Duration.seconds(0.5), keyValue1_1);
        KeyFrame keyFrame1_2 = new KeyFrame(Duration.seconds(0.5), keyValue1_2);
        //Control 버튼 애니메이션
        KeyValue keyValue2_1 = new KeyValue(btnMainMenuControl.translateXProperty(), 0);
        KeyValue keyValue2_2 = new KeyValue(labelSubBtnName2.translateXProperty(), 0);
        KeyFrame keyFrame2_1 = new KeyFrame(Duration.seconds(0.4), keyValue2_1);
        KeyFrame keyFrame2_2 = new KeyFrame(Duration.seconds(0.4), keyValue2_2);
        //Lock 버튼 애니메이션
        KeyValue keyValue3_1 = new KeyValue(btnMainMenuLock.translateXProperty(), 0);
        KeyValue keyValue3_2 = new KeyValue(labelSubBtnName3.translateXProperty(), 0);
        KeyFrame keyFrame3_1 = new KeyFrame(Duration.seconds(0.4), keyValue3_1);
        KeyFrame keyFrame3_2 = new KeyFrame(Duration.seconds(0.4), keyValue3_2);
        //Setting 버튼 애니메이션
        KeyValue keyValue4_1 = new KeyValue(btnMainMenuSetting.translateXProperty(), 0);
        KeyValue keyValue4_2 = new KeyValue(labelSubBtnName4.translateXProperty(), 0);
        KeyFrame keyFrame4_1 = new KeyFrame(Duration.seconds(0.5), keyValue4_1);
        KeyFrame keyFrame4_2 = new KeyFrame(Duration.seconds(0.5), keyValue4_2);
        //Timeline 생성 및 세팅
        Timeline timeline = new Timeline();
        timeline.getKeyFrames().add(keyFrame1_1);
        timeline.getKeyFrames().add(keyFrame1_2);
        timeline.getKeyFrames().add(keyFrame2_1);
        timeline.getKeyFrames().add(keyFrame2_2);
        timeline.getKeyFrames().add(keyFrame3_1);
        timeline.getKeyFrames().add(keyFrame3_2);
        timeline.getKeyFrames().add(keyFrame4_1);
        timeline.getKeyFrames().add(keyFrame4_2);
        //Timeline 실행
        timeline.play();
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
    }
    
    private void handleBtnMainMenuSetting(ActionEvent e) {
    }

    private void mediaPlayerDisposer() {
        mediaPlayer.setOnEndOfMedia(() -> {
            mediaPlayer.dispose();
        });
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
                    socket.connect(new InetSocketAddress("192.168.43.213", 50001));

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

                Platform.runLater(() -> {
                    try {
                        TableView<Notice> noticeTable = (TableView) noticeBorderPane.lookup("#tableView");

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
                    } catch (Exception ex) {
                        
                    }
                });
            }
        } catch (Exception ex) {
            stopClient();
        }
    }
}
