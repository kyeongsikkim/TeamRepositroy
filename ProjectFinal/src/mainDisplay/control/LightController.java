package mainDisplay.control;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.FillTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.TranslateTransition;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.util.Duration;
import static mainDisplay.control.ControlController.lightvalue;

public class LightController implements Initializable {

	public static Media media;
	public static MediaPlayer mediaPlayer;
	
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
    private Rectangle background;
    @FXML
    private Circle trigger;
    
    private BooleanProperty switchedOn = new SimpleBooleanProperty(lightvalue.getBtnAllSwitch());
    private TranslateTransition translateAnimation = new TranslateTransition(Duration.seconds(0.25));
    private FillTransition fillAnimation = new FillTransition(Duration.seconds(0.25));
    private ParallelTransition animation = new ParallelTransition(translateAnimation, fillAnimation);
    @FXML
    private Label lblRoomName4;
    @FXML
    private Label lblRoomName5;
    @FXML
    private Label lblRoomName6;
    @FXML
    private Label lblRoomName1;
    @FXML
    private Label lblRoomName2;
    @FXML
    private Label lblRoomName3;
    @FXML
    private Label lblOnOff;
    
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Font font1 = Font.loadFont(getClass().getResource("fonts/08SeoulNamsanEB.ttf").toExternalForm(), 15);

        lblRoomName1.setFont(font1);
        lblRoomName2.setFont(font1);
        lblRoomName3.setFont(font1);
        lblRoomName4.setFont(font1);
        lblRoomName5.setFont(font1);
        lblRoomName6.setFont(font1);
       
            
        if (lightvalue.getBtnImage1() == null) {
            btnImg1.setImage(new Image(getClass().getResource("images/light_off.png").toString()));
        } else{
            btnImg1.setImage(new Image(lightvalue.getBtnImage1()));
            
        } if (lightvalue.getBtnImage2() == null) {
            btnImg2.setImage(new Image(getClass().getResource("images/light_off.png").toString()));
        } else{
            btnImg2.setImage(new Image(lightvalue.getBtnImage2()));
        }
        
        if (lightvalue.getBtnImage3() == null) {
            btnImg3.setImage(new Image(getClass().getResource("images/light_off.png").toString()));
        } else {
            btnImg3.setImage(new Image(lightvalue.getBtnImage3()));
        }
        
        if (lightvalue.getBtnImage4() == null) {
            btnImg4.setImage(new Image(getClass().getResource("images/light_off.png").toString()));
        } else {
            btnImg4.setImage(new Image(lightvalue.getBtnImage4()));
        }
        
        if (lightvalue.getBtnImage5() == null) {
            btnImg5.setImage(new Image(getClass().getResource("images/light_off.png").toString()));
        } else {
            btnImg5.setImage(new Image(lightvalue.getBtnImage5()));
        }
        
        if (lightvalue.getBtnImage6() == null ) {
            btnImg6.setImage(new Image(getClass().getResource("images/light_off.png").toString()));
        } else {
            btnImg6.setImage(new Image(lightvalue.getBtnImage6()));
        }
        
        if (lightvalue.getBtnAllSwitch() == false) {
            trigger.setUserData("false");
        } else {
            trigger.setUserData("true");
            trigger.setCenterX(50);
            background.setFill(Color.LIGHTSALMON);
        }
        
        if (lightvalue.getLblOnOff() == null || lightvalue.getLblOnOff().equals("OFF")) {
            lblOnOff.setText("OFF");
            lblOnOff.setAlignment(Pos.CENTER_LEFT);
        } else {
            lblOnOff.setText("ON");
            lblOnOff.setAlignment(Pos.CENTER_RIGHT);
        }
             
        btn1.setOnAction(e -> handleBtnAction1(e));
        btn2.setOnAction(e -> handleBtnAction2(e));
        btn3.setOnAction(e -> handleBtnAction3(e));
        btn4.setOnAction(e -> handleBtnAction4(e));
        btn5.setOnAction(e -> handleBtnAction5(e));
        btn6.setOnAction(e -> handleBtnAction6(e));
       
        translateAnimation.setNode(trigger);
        fillAnimation.setShape(background);
        
        switchedOn.addListener((obs, oldState, newState) -> {
            boolean isOn = newState.booleanValue();
            translateAnimation.setByX(isOn ? 50 : -50);
            fillAnimation.setFromValue(isOn ? Color.WHITE : Color.LIGHTSALMON);
            fillAnimation.setToValue(isOn ? Color.LIGHTSALMON : Color.WHITE);
            animation.play();
        });
        trigger.setOnMouseClicked(e -> handletrigger(e));
        background.setOnMouseClicked(e -> handletrigger(e));
    }

    private void handleBtnAction1(ActionEvent e) {
        if (btn1.getId().equals("false")) {
            btn1.setId("true");
            btnImg1.setImage(new Image(getClass().getResource("images/light_on.png").toString()));
            lightvalue.setBtnImage1(getClass().getResource("images/light_on.png").toString());
            return;
        } else if (btn1.getId().equals("true")) {
            btn1.setId("false");
            btnImg1.setImage(new Image(getClass().getResource("images/light_off.png").toString()));
            lightvalue.setBtnImage1(getClass().getResource("images/light_off.png").toString());
        }
    }

    private void handleBtnAction2(ActionEvent e) {
        if (btn2.getId().equals("false")) {
            btn2.setId("true");
            btnImg2.setImage(new Image(getClass().getResource("images/light_on.png").toString()));
            lightvalue.setBtnImage2(getClass().getResource("images/light_on.png").toString());
            return;
        } else if (btn2.getId().equals("true")) {
            btn2.setId("false");
            btnImg2.setImage(new Image(getClass().getResource("images/light_off.png").toString()));
            lightvalue.setBtnImage2(getClass().getResource("images/light_off.png").toString());
        }
    }

    private void handleBtnAction3(ActionEvent e) {
        if (btn3.getId().equals("false")) {
            btn3.setId("true");
            btnImg3.setImage(new Image(getClass().getResource("images/light_on.png").toString()));
            lightvalue.setBtnImage3(getClass().getResource("images/light_on.png").toString());
            return;
        } else if (btn3.getId().equals("true")) {
            btn3.setId("false");
            btnImg3.setImage(new Image(getClass().getResource("images/light_off.png").toString()));
            lightvalue.setBtnImage3(getClass().getResource("images/light_off.png").toString());
        }
    }

    private void handleBtnAction4(ActionEvent e) {
        if (btn4.getId().equals("false")) {
            btn4.setId("true");
            btnImg4.setImage(new Image(getClass().getResource("images/light_on.png").toString()));
            lightvalue.setBtnImage4(getClass().getResource("images/light_on.png").toString());
            return;
        } else if (btn4.getId().equals("true")) {
            btn4.setId("false");
            btnImg4.setImage(new Image(getClass().getResource("images/light_off.png").toString()));
            lightvalue.setBtnImage4(getClass().getResource("images/light_off.png").toString());
        }
    }

    private void handleBtnAction5(ActionEvent e) {
        if (btn5.getId().equals("false")) {
            btn5.setId("true");
            btnImg5.setImage(new Image(getClass().getResource("images/light_on.png").toString()));
            lightvalue.setBtnImage5(getClass().getResource("images/light_on.png").toString());
            return;
        } else if (btn5.getId().equals("true")) {
            btn5.setId("false");
            btnImg5.setImage(new Image(getClass().getResource("images/light_off.png").toString()));
            lightvalue.setBtnImage5(getClass().getResource("images/light_off.png").toString());
        }
    }

    private void handleBtnAction6(ActionEvent e) {
        if (btn6.getId().equals("false")) {
            btn6.setId("true");
            btnImg6.setImage(new Image(getClass().getResource("images/light_on.png").toString()));
            lightvalue.setBtnImage6(getClass().getResource("images/light_on.png").toString());
            return;
        } else if (btn6.getId().equals("true")) {
            btn6.setId("false");
            btnImg6.setImage(new Image(getClass().getResource("images/light_off.png").toString()));
            lightvalue.setBtnImage6(getClass().getResource("images/light_off.png").toString());
        }
    }

    private void handletrigger(MouseEvent e) {
        switchedOn.setValue(!switchedOn.getValue());
        
        if (trigger.getUserData().equals("false")) {
            trigger.setUserData("true");
            lightvalue.setBtnAllSwitch(Boolean.valueOf(trigger.getUserData().toString()));
            
            lblOnOff.setText("ON");
            lblOnOff.setAlignment(Pos.CENTER_RIGHT);
            lightvalue.setLblOnOff(lblOnOff.getText());
                  
			
			if(mediaPlayer != null) {
				mediaPlayer.dispose();
			}
			
            media = new Media(getClass().getResource("../sounds/lightOn.mp3").toString());
            mediaPlayer = new MediaPlayer(media); 
            mediaPlayer.play();
        
            
            btnImg1.setImage(new Image(getClass().getResource("images/light_on.png").toString()));
            btnImg2.setImage(new Image(getClass().getResource("images/light_on.png").toString()));
            btnImg3.setImage(new Image(getClass().getResource("images/light_on.png").toString()));
            btnImg4.setImage(new Image(getClass().getResource("images/light_on.png").toString()));
            btnImg5.setImage(new Image(getClass().getResource("images/light_on.png").toString()));
            btnImg6.setImage(new Image(getClass().getResource("images/light_on.png").toString()));
            lightvalue.setBtnImage1(getClass().getResource("images/light_on.png").toString());
            lightvalue.setBtnImage2(getClass().getResource("images/light_on.png").toString());
            lightvalue.setBtnImage3(getClass().getResource("images/light_on.png").toString());
            lightvalue.setBtnImage4(getClass().getResource("images/light_on.png").toString());
            lightvalue.setBtnImage5(getClass().getResource("images/light_on.png").toString());
            lightvalue.setBtnImage6(getClass().getResource("images/light_on.png").toString());
           
            return;
            
        } else if (trigger.getUserData().equals("true")) {
            trigger.setUserData("false");
            lightvalue.setBtnAllSwitch(Boolean.valueOf(trigger.getUserData().toString()));

            lblOnOff.setText("OFF");
            lblOnOff.setAlignment(Pos.CENTER_LEFT);
            lightvalue.setLblOnOff(lblOnOff.getText());
            
			
			if(mediaPlayer != null) {
				mediaPlayer.dispose();
			}
			
            media = new Media(getClass().getResource("../sounds/lightOff.mp3").toString());
            mediaPlayer = new MediaPlayer(media); 
            mediaPlayer.play();
            
            btnImg1.setImage(new Image(getClass().getResource("images/light_off.png").toString()));
            btnImg2.setImage(new Image(getClass().getResource("images/light_off.png").toString()));
            btnImg3.setImage(new Image(getClass().getResource("images/light_off.png").toString()));
            btnImg4.setImage(new Image(getClass().getResource("images/light_off.png").toString()));
            btnImg5.setImage(new Image(getClass().getResource("images/light_off.png").toString()));
            btnImg6.setImage(new Image(getClass().getResource("images/light_off.png").toString()));
            lightvalue.setBtnImage1(getClass().getResource("images/light_off.png").toString());
            lightvalue.setBtnImage2(getClass().getResource("images/light_off.png").toString());
            lightvalue.setBtnImage3(getClass().getResource("images/light_off.png").toString());
            lightvalue.setBtnImage4(getClass().getResource("images/light_off.png").toString());
            lightvalue.setBtnImage5(getClass().getResource("images/light_off.png").toString());
            lightvalue.setBtnImage6(getClass().getResource("images/light_off.png").toString());
            
        }
    }

}
