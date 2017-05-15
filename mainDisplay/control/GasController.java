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
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
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
import static mainDisplay.control.ControlController.gasvalue;

public class GasController implements Initializable {

    @FXML
    private BorderPane gas;
    private ToggleButton btnOnOff;
    @FXML
    private ImageView gasImage;
    @FXML
    private Rectangle background;
    @FXML
    private Circle trigger;

    private BooleanProperty switchedOn = new SimpleBooleanProperty(gasvalue.getBtnAllSwitch());
    private TranslateTransition translateAnimation = new TranslateTransition(Duration.seconds(0.25));
    private FillTransition fillAnimation = new FillTransition(Duration.seconds(0.25));
    private ParallelTransition animation = new ParallelTransition(translateAnimation, fillAnimation);
    @FXML
    private Label lblWarning;
    @FXML
    private Label lblOnOff;
    
        
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         Font font1 = Font.loadFont(getClass().getResource("fonts/08SeoulNamsanEB.ttf").toExternalForm(), 18);

        lblWarning.setFont(font1);
        
        if (gasvalue.getGasImage()==null) {
            gasImage.setImage(new Image(getClass().getResource("images/gasoff1.png").toString()));
        } else{
            gasImage.setImage(new Image(gasvalue.getGasImage()));
        }
        
        
        if (gasvalue.getBtnAllSwitch()==false) {
            trigger.setUserData("false");
        } else {
            trigger.setUserData("true");
            trigger.setCenterX(50);
           background.setFill(Color.WHEAT);
        }
        
        if (gasvalue.getLblOnOff() == null || gasvalue.getLblOnOff().equals("OFF")) {
            lblOnOff.setText("OFF");
            lblOnOff.setAlignment(Pos.CENTER_LEFT);
        } else {
            lblOnOff.setText("ON");
            lblOnOff.setAlignment(Pos.CENTER_RIGHT);
        }
        
        lblWarning.setText(" * 경 고 * 외출 시 가스밸브를 잠가 주시기 바랍니다. ");
        lblWarning.setTextFill(Color.DARKRED);

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

    private void handleBtnOnOffAction(ActionEvent e) {
        if (btnOnOff.getText().equals("ON")) {
            btnOnOff.setText("OFF");
            gasImage.setImage(new Image(getClass().getResource("images/gason1.png").toString()));
            gasvalue.setGasImage(getClass().getResource("images/gason1.png").toString());
            gasvalue.setGasOnOff(btnOnOff.getText());

            return;
        } else if (btnOnOff.getText().equals("OFF")) {
            btnOnOff.setText("ON");
            gasImage.setImage(new Image(getClass().getResource("images/gasoff1.png").toString()));
            gasvalue.setGasImage(getClass().getResource("images/gasoff1.png").toString());
            gasvalue.setGasOnOff(btnOnOff.getText());

        }
    }

    private void handletrigger(MouseEvent e) {
        switchedOn.setValue(!switchedOn.getValue());
        
        if (trigger.getUserData().equals("false")) {
            trigger.setUserData("true");
            gasvalue.setBtnAllSwitch(Boolean.valueOf(trigger.getUserData().toString()));

            lblOnOff.setText("ON");
            lblOnOff.setAlignment(Pos.CENTER_RIGHT);
            gasvalue.setLblOnOff(lblOnOff.getText());
            
            Media media = new Media(getClass().getResource("../sounds/gasOn.mp3").toString());
            MediaPlayer mediaPlayer = new MediaPlayer(media); 
            mediaPlayer.play();
            
            gasImage.setImage(new Image(getClass().getResource("images/gason1.png").toString()));
            gasvalue.setGasImage(getClass().getResource("images/gason1.png").toString());

            return;
        } else if (trigger.getUserData().equals("true")) {
            trigger.setUserData("false");
            gasvalue.setBtnAllSwitch(Boolean.valueOf(trigger.getUserData().toString()));

            lblOnOff.setText("OFF");
            lblOnOff.setAlignment(Pos.CENTER_LEFT);
            gasvalue.setLblOnOff(lblOnOff.getText());
            
            Media media = new Media(getClass().getResource("../sounds/gasOff.mp3").toString());
            MediaPlayer mediaPlayer = new MediaPlayer(media); 
            mediaPlayer.play();
            
            gasImage.setImage(new Image(getClass().getResource("images/gasoff1.png").toString()));
            gasvalue.setGasImage(getClass().getResource("images/gasoff1.png").toString());
        }
    }

}
