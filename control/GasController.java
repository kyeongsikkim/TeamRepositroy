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
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;

/**
 * FXML Controller class
 *
 * @author Administrator
 */
public class GasController implements Initializable {

    @FXML
    private BorderPane gas;
    @FXML
    private ToggleButton btnOnOff;
    @FXML
    private ImageView gasImage;
    
    //private boolean endOfMedia;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
//        Media media=new Media(getClass().getResource("media/video.m4v").toString());
//        MediaPlayer mediaPlayer=new MediaPlayer(media);
//        mediaView.setMediaPlayer(mediaPlayer);
//        
//        btnOnOff.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent event) {
//                if(endOfMedia=false)
//                mediaPlayer.play();
//                
//                btnOnOff.setDisable(true);
//            }
//        });
//        mediaPlayer.setOnEndOfMedia(()->{
//            btnOnOff.setDisable(false);
//        });
Image image = new Image(getClass().getResource("images/gas_off.png").toString());
gasImage.setImage(image);
            btnOnOff.setOnAction(e -> handleBtnOnOffAction(e));

    }

    private void handleBtnOnOffAction(ActionEvent e) {
        if (btnOnOff.getText().equals("ON")) {
            btnOnOff.setText("OFF");
            gasImage.setImage(new Image(getClass().getResource("images/gas_on.png").toString()));
            return;
        } else if (btnOnOff.getText().equals("OFF")) {
            btnOnOff.setText("ON");
            
            gasImage.setImage(new Image(getClass().getResource("images/gas_off.png").toString()));
        }
    }

}
