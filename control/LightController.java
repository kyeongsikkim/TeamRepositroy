/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch17.control;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;

/**
 * FXML Controller class
 *
 * @author Administrator
 */
public class LightController implements Initializable {

    @FXML
    private BorderPane light;
    @FXML
    private Button btn5;
    @FXML
    private ImageView btnImg5;
    @FXML
    private Button btn6;
    @FXML
    private ImageView btnImg6;
    @FXML
    private Button btn1;
    @FXML
    private ImageView btnImg1;
    @FXML
    private Button btn2;
    @FXML
    private ImageView btnImg2;
    @FXML
    private Button btn3;
    @FXML
    private ImageView btnImg3;
    @FXML
    private Button btn4;
    @FXML
    private ImageView btnImg4;
    @FXML
    private Button lightAll;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        btn1.setOnAction(e -> handleBtnAction1(e));
        btn2.setOnAction(e -> handleBtnAction2(e));
        btn3.setOnAction(e -> handleBtnAction3(e));
        btn4.setOnAction(e -> handleBtnAction4(e));
        btn5.setOnAction(e -> handleBtnAction5(e));
        btn6.setOnAction(e -> handleBtnAction6(e));
        lightAll.setOnAction(e -> handleBtnAll(e));

    }

    private void handleBtnAction1(ActionEvent e) {
        if (btn1.getId().equals("true")) {
            btn1.setId("false");
            btnImg1.setImage(new Image(getClass().getResource("images/light_on.png").toString()));
            return;
        } else if (btn1.getId().equals("false")) {
            btn1.setId("true");
            btnImg1.setImage(new Image(getClass().getResource("images/light_off.png").toString()));
        }
    }

    private void handleBtnAction2(ActionEvent e) {
        if (btn2.getId().equals("true")) {
            btn2.setId("false");
            btnImg2.setImage(new Image(getClass().getResource("images/light_on.png").toString()));
            return;
        } else if (btn2.getId().equals("false")) {
            btn2.setId("true");
            btnImg2.setImage(new Image(getClass().getResource("images/light_off.png").toString()));
        }
    }

    private void handleBtnAction3(ActionEvent e) {
        if (btn3.getId().equals("true")) {
            btn3.setId("false");
            btnImg3.setImage(new Image(getClass().getResource("images/light_on.png").toString()));
            return;
        } else if (btn3.getId().equals("false")) {
            btn3.setId("true");
            btnImg3.setImage(new Image(getClass().getResource("images/light_off.png").toString()));
        }
    }

    private void handleBtnAction4(ActionEvent e) {
        if (btn4.getId().equals("true")) {
            btn4.setId("false");
            btnImg4.setImage(new Image(getClass().getResource("images/light_on.png").toString()));
            return;
        } else if (btn4.getId().equals("false")) {
            btn4.setId("true");
            btnImg4.setImage(new Image(getClass().getResource("images/light_off.png").toString()));
        }
    }

    private void handleBtnAction5(ActionEvent e) {
        if (btn5.getId().equals("true")) {
            btn5.setId("false");
            btnImg5.setImage(new Image(getClass().getResource("images/light_on.png").toString()));
            return;
        } else if (btn5.getId().equals("false")) {
            btn5.setId("true");
            btnImg5.setImage(new Image(getClass().getResource("images/light_off.png").toString()));
        }
    }

    private void handleBtnAction6(ActionEvent e) {
        if (btn6.getId().equals("true")) {
            btn6.setId("false");
            btnImg6.setImage(new Image(getClass().getResource("images/light_on.png").toString()));
            return;
        } else if (btn6.getId().equals("false")) {
            btn6.setId("true");
            btnImg6.setImage(new Image(getClass().getResource("images/light_off.png").toString()));
        }
    }

    private void handleBtnAll(ActionEvent e) {
        if (lightAll.getText().equals("Turn On")) {

            lightAll.setText("Turn Off");

//            Media media = new Media(getClass().getResource("media/전체 점등 되었습니다.mp3").toString());
//            MediaPlayer mediaPlayer = new MediaPlayer(media); 
//            mediaPlayer.play();
            btnImg1.setImage(new Image(getClass().getResource("images/light_on.png").toString()));
            btnImg2.setImage(new Image(getClass().getResource("images/light_on.png").toString()));
            btnImg3.setImage(new Image(getClass().getResource("images/light_on.png").toString()));
            btnImg4.setImage(new Image(getClass().getResource("images/light_on.png").toString()));
            btnImg5.setImage(new Image(getClass().getResource("images/light_on.png").toString()));
            btnImg6.setImage(new Image(getClass().getResource("images/light_on.png").toString()));
            return;
        } else if (lightAll.getText().equals("Turn Off")) {
            lightAll.setText("Turn On");

//            Media media = new Media(getClass().getResource("media/전체 소등 되었습니다.mp3").toString());
//            MediaPlayer mediaPlayer = new MediaPlayer(media); 
//            mediaPlayer.play();
            btnImg1.setImage(new Image(getClass().getResource("images/light_off.png").toString()));
            btnImg2.setImage(new Image(getClass().getResource("images/light_off.png").toString()));
            btnImg3.setImage(new Image(getClass().getResource("images/light_off.png").toString()));
            btnImg4.setImage(new Image(getClass().getResource("images/light_off.png").toString()));
            btnImg5.setImage(new Image(getClass().getResource("images/light_off.png").toString()));
            btnImg6.setImage(new Image(getClass().getResource("images/light_off.png").toString()));
        }
    }

}
