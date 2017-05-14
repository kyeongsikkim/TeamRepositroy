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
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import static mainDisplay.control.ControlController.gasvalue;
import static mainDisplay.control.ControlController.lightvalue;

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
    
    public BooleanProperty switchedOnProperty() {
        return switchedOn;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if (gasvalue.getGasImage()==null) {
            gasImage.setImage(new Image(getClass().getResource("images/gasoff.png").toString()));
        } else{
            gasImage.setImage(new Image(gasvalue.getGasImage()));
        }
        
        
        if (gasvalue.getBtnAllSwitch()==false) {
            trigger.setUserData("true");
        } else {
            trigger.setUserData("false");
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

        
//        btnOnOff.setOnAction(e -> handleBtnOnOffAction(e));

        translateAnimation.setNode(trigger);
        fillAnimation.setShape(background);
        
        switchedOn.addListener((obs, oldState, newState) -> {
            boolean isOn = newState.booleanValue();
            translateAnimation.setByX(isOn ? 50 : -50);
            fillAnimation.setFromValue(isOn ? Color.WHITE : Color.LIGHTGREEN);
            fillAnimation.setToValue(isOn ? Color.WHEAT : Color.WHITE);
            animation.play();
        });
        trigger.setOnMouseClicked(e -> handletrigger(e));
        background.setOnMouseClicked(e -> handletrigger(e));
    }

    private void handleBtnOnOffAction(ActionEvent e) {
        if (btnOnOff.getText().equals("ON")) {
            btnOnOff.setText("OFF");
            gasImage.setImage(new Image(getClass().getResource("images/gason.png").toString()));
            gasvalue.setGasImage(getClass().getResource("images/gason.png").toString());
            gasvalue.setGasOnOff(btnOnOff.getText());

            return;
        } else if (btnOnOff.getText().equals("OFF")) {
            btnOnOff.setText("ON");
            gasImage.setImage(new Image(getClass().getResource("images/gasoff.png").toString()));
            gasvalue.setGasImage(getClass().getResource("images/gasoff.png").toString());
            gasvalue.setGasOnOff(btnOnOff.getText());

        }
    }

    private void handletrigger(MouseEvent e) {
        switchedOn.setValue(!switchedOn.getValue());
        
        if (trigger.getUserData().equals("true")) {
            gasvalue.setBtnAllSwitch(Boolean.valueOf(trigger.getUserData().toString()));
            
            trigger.setUserData("false");

            lblOnOff.setText("ON");
            lblOnOff.setAlignment(Pos.CENTER_RIGHT);
            String direction="Pos.CENTER_RIGHT";
            gasvalue.setLblOnOff(lblOnOff.getText());
            
            gasImage.setImage(new Image(getClass().getResource("images/gason.png").toString()));
            gasvalue.setGasImage(getClass().getResource("images/gason.png").toString());

            return;
        } else if (trigger.getUserData().equals("false")) {
            
            gasvalue.setBtnAllSwitch(Boolean.valueOf(trigger.getUserData().toString()));

            trigger.setUserData("true");

            lblOnOff.setText("OFF");
            lblOnOff.setAlignment(Pos.CENTER_LEFT);
            gasvalue.setLblOnOff(lblOnOff.getText());
            
            gasImage.setImage(new Image(getClass().getResource("images/gasoff.png").toString()));
            gasvalue.setGasImage(getClass().getResource("images/gasoff.png").toString());
        }
    }

}
