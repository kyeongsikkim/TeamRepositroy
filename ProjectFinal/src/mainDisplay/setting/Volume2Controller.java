/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mainDisplay.setting;

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
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Font;
import static mainDisplay.setting.RootController.mediaPlayer;
import static mainDisplay.setting.RootController.media;
import static mainDisplay.setting.RootController.sta;

/**
 * FXML Controller class
 *
 * @author KIM
 */
public class Volume2Controller implements Initializable {

    @FXML
    private StackPane stack;
    @FXML
    private Button btnAccept;
    @FXML
    private Button btnLkind;
    @FXML
    private Button btnRkind;
    @FXML
    private Slider sliderCall;
    @FXML
    private ImageView img1;
    @FXML
    private Slider sliderMusic;
    @FXML
    private ImageView img2;
    @FXML
    private Button btnLmusic;
    @FXML
    private TextField txtMusic;
    @FXML
    private Button btnRmusic;

    private double slidersoundValue1 = sta.getSliderCall2();
    private double slidersoundValue2 = sta.getSliderMusic2();
    private String MusicAddress = sta.getMusicAddress3();
    @FXML
    private Label lbl1;
    @FXML
    private TextField txtBell;
    @FXML
    private Label lbl2;
    @FXML
    private Label lbl3;
    @FXML
    private Label lbl4;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Font font1 = Font.loadFont(getClass().getResource("fonts/08SeoulNamsanEB.ttf").toExternalForm(), 20);
        Font font2 = Font.loadFont(getClass().getResource("fonts/08SeoulNamsanEB.ttf").toExternalForm(), 12);
        Font font3 = Font.loadFont(getClass().getResource("fonts/08SeoulNamsanEB.ttf").toExternalForm(), 40);
        Font font4 = Font.loadFont(getClass().getResource("fonts/08SeoulNamsanEB.ttf").toExternalForm(), 16);
        
        lbl1.setFont(font1);
        lbl2.setFont(font1);
        lbl3.setFont(font1);
        lbl4.setFont(font1);
        txtBell.setFont(font3);
        txtMusic.setFont(font3);
        btnAccept.setFont(font4);
        
        if (MusicAddress == null) {
            MusicAddress = "../sounds/bell1.mp3";
        }
        if (sta.getTxtMusic2() != null) {
            txtMusic.setText(sta.getTxtMusic2());
        }
        if (sta.getSliderCall2() == 0) {
            img1.setImage(new Image(sta.getImag12()));
        }
        if (sta.getSliderMusic2() == 0) {
            img2.setImage(new Image(sta.getImg22()));
        }

        btnLkind.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    if (mediaPlayer != null) {
                        mediaPlayer.dispose();
                    }
                    stack.getChildren().remove(0);
                    Parent parent = FXMLLoader.load(getClass().getResource("volume1.fxml"));
                    stack.getChildren().add(parent);
                } catch (IOException ex) {

                }

            }
        });

        sliderCall.setValue(sta.getSliderCall2());
        sliderCall.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {

                if ((sliderCall.getValue()) == 0) {
                    Image setImage = new Image(getClass().getResource("images/speakerremove.png").toString());
                    sta.setImag12(getClass().getResource("images/speakerremove.png").toString());
                    img1.setImage(setImage);
                } else {
                    Image setImage = new Image(getClass().getResource("images/speaker.png").toString());
                    sta.setImag12(getClass().getResource("images/speaker.png").toString());
                    img1.setImage(setImage);
                }
                slidersoundValue1 = newValue.doubleValue();
                if (mediaPlayer != null) {
                    mediaPlayer.dispose();
                }
                media = new Media(getClass().getResource("../sounds/call.mp3").toString());
                mediaPlayer = new MediaPlayer(media);
                mediaPlayer.play();
                mediaPlayer.setVolume(slidersoundValue1 / 100.0);
            }

        });
        sliderMusic.setValue(sta.getSliderMusic2());
        sliderMusic.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {

                if ((sliderMusic.getValue()) == 0) {
                    Image setImage = new Image(getClass().getResource("images/speakerremove.png").toString());
                    sta.setImg22(getClass().getResource("images/speakerremove.png").toString());
                    img2.setImage(setImage);
                } else {
                    Image setImage = new Image(getClass().getResource("images/speaker.png").toString());
                    sta.setImg22(getClass().getResource("images/speaker.png").toString());
                    img2.setImage(setImage);
                }
                slidersoundValue2 = newValue.doubleValue();
                if (mediaPlayer != null) {
                    mediaPlayer.dispose();
                }
                media = new Media(getClass().getResource(MusicAddress).toString());
                mediaPlayer = new MediaPlayer(media);
                mediaPlayer.play();
                mediaPlayer.setVolume(slidersoundValue2 / 100.0);
            }

        });
        btnRmusic.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (mediaPlayer != null) {
                    mediaPlayer.dispose();
                }
                String str = txtMusic.getText();
                if (str.equals("벨소리1")) {
                    txtMusic.setText("벨소리2");
                    MusicAddress = "../sounds/bell2.mp3";
                    media = new Media(getClass().getResource("../sounds/bell2.mp3").toString());
                    mediaPlayer = new MediaPlayer(media);
                    mediaPlayer.play();
                } else if (str.equals("벨소리2")) {
                    txtMusic.setText("벨소리3");
                    MusicAddress = "../sounds/bell3.mp3";
                    media = new Media(getClass().getResource("../sounds/bell3.mp3").toString());
                    mediaPlayer = new MediaPlayer(media);
                    mediaPlayer.play();

                } else {
                    txtMusic.setText("벨소리3");
                    media = new Media(getClass().getResource("../sounds/bell3.mp3").toString());
                    mediaPlayer = new MediaPlayer(media);
                    mediaPlayer.play();
                }
            }
        });
        btnLmusic.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (mediaPlayer != null) {
                    mediaPlayer.dispose();
                }
                String str = txtMusic.getText();
                if (str.equals("벨소리3")) {
                    txtMusic.setText("벨소리2");
                    MusicAddress = "../sounds/bell2.mp3";
                    media = new Media(getClass().getResource("../sounds/bell2.mp3").toString());
                    mediaPlayer = new MediaPlayer(media);
                    mediaPlayer.play();
                } else if (str.equals("벨소리2")) {
                    txtMusic.setText("벨소리1");
                    MusicAddress = "../sounds/bell1.mp3";
                    media = new Media(getClass().getResource("../sounds/bell1.mp3").toString());
                    mediaPlayer = new MediaPlayer(media);
                    mediaPlayer.play();
                } else {
                    txtMusic.setText("벨소리1");
                    MusicAddress = "../sounds/bell1.mp3";
                    media = new Media(getClass().getResource("../sounds/bell1.mp3").toString());
                    mediaPlayer = new MediaPlayer(media);
                    mediaPlayer.play();
                }
            }
        });
        btnAccept.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                sta.setTxtMusic2(txtMusic.getText());
                sta.setSliderCall2(slidersoundValue1);
                sta.setSliderMusic2(slidersoundValue2);
                sta.setMusicAddress3(MusicAddress);
            }
        });

    }
}
