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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.util.Duration;
import static mainDisplay.control.ControlController.heatvalue;

public class HeatController implements Initializable {

    @FXML
    private BorderPane heat;

    @FXML
    private Label lblTemp;
    @FXML
    private Label lblTempControl;

    @FXML
    private BorderPane heatChange;
    
    @FXML
    private Rectangle background1;
    @FXML
    private Circle trigger1;
    @FXML
    private Rectangle background2;
    @FXML
    private Circle trigger2;
    @FXML
    private Rectangle background3;
    @FXML
    private Circle trigger3;
    
    private BooleanProperty switchedOn = new SimpleBooleanProperty(heatvalue.getBtnAllSwitch());
    private TranslateTransition translateAnimation = new TranslateTransition(Duration.seconds(0.25));
    private FillTransition fillAnimation = new FillTransition(Duration.seconds(0.25));
    private ParallelTransition animation = new ParallelTransition(translateAnimation, fillAnimation);

    private BooleanProperty switchedOn2 = new SimpleBooleanProperty(false);
    private TranslateTransition translateAnimation2 = new TranslateTransition(Duration.seconds(0.25));
    private FillTransition fillAnimation2 = new FillTransition(Duration.seconds(0.25));
    private ParallelTransition animation2 = new ParallelTransition(translateAnimation2, fillAnimation2);
    
    private BooleanProperty switchedOn3 = new SimpleBooleanProperty(false);
    private TranslateTransition translateAnimation3 = new TranslateTransition(Duration.seconds(0.25));
    private FillTransition fillAnimation3 = new FillTransition(Duration.seconds(0.25));
    private ParallelTransition animation3 = new ParallelTransition(translateAnimation3, fillAnimation3);
    @FXML
    private Button btnLivingroom;
    @FXML
    private Button btnRoom1;
    @FXML
    private Button btnRoom2;
    @FXML
    private Button btnRoom3;
    @FXML
    private Button btnTempUp;
    @FXML
    private Button btnTempDown;
    @FXML
    private Label lblPower;
    @FXML
    private Label lblOut;
    @FXML
    private Label lblHotwater;
    @FXML
    private Label lblNowTemp;
    @FXML
    private Label lblTempC;
    @FXML
    private Label lblTempControlC;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Font font1 = Font.loadFont(getClass().getResource("fonts/08SeoulNamsanEB.ttf").toExternalForm(), 20);
        Font font2 = Font.loadFont(getClass().getResource("fonts/08SeoulNamsanEB.ttf").toExternalForm(), 22);
        Font font3 = Font.loadFont(getClass().getResource("fonts/08SeoulNamsanEB.ttf").toExternalForm(), 27);
        
        btnLivingroom.setFont(font1);
        btnRoom1.setFont(font1);
        btnRoom2.setFont(font1);
        btnRoom3.setFont(font1);
        lblPower.setFont(font2);
        lblOut.setFont(font2);
        lblHotwater.setFont(font2);
        
        lblNowTemp.setFont(font3);
        lblTemp.setFont(font3);
        lblTempC.setFont(font3);
        lblTempControl.setFont(font3);
        lblTempControlC.setFont(font3);
        
        if (heatvalue.getBtnAllSwitch() == false) {
            trigger1.setUserData("false");

            trigger2.setDisable(true);
            background2.setDisable(true);
            trigger3.setDisable(true);
            background3.setDisable(true);
            btnLivingroom.setDisable(true);
            btnRoom1.setDisable(true);
            btnRoom2.setDisable(true);
            btnRoom3.setDisable(true);
            btnTempUp.setDisable(true);
            btnTempDown.setDisable(true);
        } else {
            trigger1.setUserData("true");
            trigger1.setLayoutX(175);
            background1.setFill(Color.LIGHTSALMON);

            trigger2.setDisable(false);
            background2.setDisable(false);
            trigger3.setDisable(false);
            background3.setDisable(false);
            btnLivingroom.setDisable(false);
            btnRoom1.setDisable(false);
            btnRoom2.setDisable(false);
            btnRoom3.setDisable(false);
            btnTempUp.setDisable(false);
            btnTempDown.setDisable(false);
            
            System.out.println("d=ddd");
            
        }
        
        if (heatvalue.getLblTemp()== null || heatvalue.getLblTemp().equals("OFF")) {
            lblTemp.setText("25");
           
        } else {
            
        }
        
        btnLivingroom.setOnAction(e -> handleBtnLivingroom(e));
        btnRoom1.setOnAction(e -> handleBtnroom1(e));
        btnRoom2.setOnAction(e -> handleBtnroom2(e));
        btnRoom3.setOnAction(e -> handleBtnroom3(e));
        
        lblTempControl.setText(lblTemp.getText());
        btnTempUp.setOnAction(e -> handleBtnTempup(e));
        btnTempDown.setOnAction(e -> handleBtnTempdown(e));
        
        translateAnimation.setNode(trigger1);
        fillAnimation.setShape(background1);
        translateAnimation2.setNode(trigger2);
        fillAnimation2.setShape(background2);
        translateAnimation3.setNode(trigger3);
        fillAnimation3.setShape(background3);

        switchedOn.addListener((obs, oldState, newState) -> {
            boolean isOn = newState.booleanValue();
            translateAnimation.setByX(isOn ?  50 : -50);
            fillAnimation.setFromValue(isOn ? Color.WHITE : Color.LIGHTSALMON);
            fillAnimation.setToValue(isOn ? Color.LIGHTSALMON : Color.WHITE);
            animation.play();
        });
        switchedOn2.addListener((obs, oldState, newState) -> {
            boolean isOn2 = newState.booleanValue();
            translateAnimation2.setToX(isOn2 ? 100 - 50 : 0);
            fillAnimation2.setFromValue(isOn2 ? Color.WHITE : Color.LIGHTSALMON);
            fillAnimation2.setToValue(isOn2 ? Color.LIGHTSALMON : Color.WHITE);
            animation2.play();
        });
        switchedOn3.addListener((obs, oldState, newState) -> {
            boolean isOn3 = newState.booleanValue();
            translateAnimation3.setToX(isOn3 ? 100 - 50 : 0);
            fillAnimation3.setFromValue(isOn3 ? Color.WHITE : Color.LIGHTSALMON);
            fillAnimation3.setToValue(isOn3 ? Color.LIGHTSALMON : Color.WHITE);
            animation3.play();
        });

        trigger1.setOnMouseClicked(e ->handletrigger1(e));
        background1.setOnMouseClicked(e ->handletrigger1(e));

        
        
        trigger2.setOnMouseClicked(event -> {switchedOn2.set(!switchedOn2.get());});
        background2.setOnMouseClicked(event -> {switchedOn2.set(!switchedOn2.get());});

        trigger3.setOnMouseClicked(event -> {switchedOn3.set(!switchedOn3.get());});
        background3.setOnMouseClicked(event -> {switchedOn3.set(!switchedOn3.get());});
    }

    private void handleBtnLivingroom(ActionEvent e) {
        int Temp=(int)(Math.random()*9)+17;
        lblTemp.setText(String.valueOf(Temp));
        lblTempControl.setText(lblTemp.getText());
        
        heatvalue.setLblTemp(lblTemp.getText());
        
    }

    private void handleBtnroom1(ActionEvent e) {
        int Temp1=(int)(Math.random()*9)+17;
        lblTemp.setText(String.valueOf(Temp1));
        lblTempControl.setText(lblTemp.getText());
    }

    private void handleBtnroom2(ActionEvent e) {
        int Temp2=(int)(Math.random()*9)+17;
        lblTemp.setText(String.valueOf(Temp2));
        lblTempControl.setText(lblTemp.getText());
    }

    private void handleBtnroom3(ActionEvent e) {
        int Temp3=(int)(Math.random()*9)+17;
        lblTemp.setText(String.valueOf(Temp3));
        lblTempControl.setText(lblTemp.getText());
    }

    private void handleBtnTempup(ActionEvent e) {
        int tempchange = Integer.parseInt(lblTempControl.getText());
        lblTempControl.setText(String.valueOf(tempchange + 1));
    }

    private void handleBtnTempdown(ActionEvent e) {
        int tempchange = Integer.parseInt(lblTempControl.getText());
        lblTempControl.setText(String.valueOf(tempchange - 1));
    }

    private void handletrigger1(MouseEvent e) {
        switchedOn.setValue(!switchedOn.getValue());
        
        if (trigger1.getUserData().equals("false")){
            trigger1.setUserData("true");
            heatvalue.setBtnAllSwitch(Boolean.valueOf(trigger1.getUserData().toString()));

            trigger2.setDisable(false);
            background2.setDisable(false);
            trigger3.setDisable(false);
            background3.setDisable(false);
            btnLivingroom.setDisable(false);
            btnRoom1.setDisable(false);
            btnRoom2.setDisable(false);
            btnRoom3.setDisable(false);
            btnTempUp.setDisable(false);
            btnTempDown.setDisable(false);
        }
        else if(trigger1.getUserData().equals("true")){
            trigger1.setUserData("false");
            heatvalue.setBtnAllSwitch(Boolean.valueOf(trigger1.getUserData().toString()));

            
            trigger2.setDisable(true);
            background2.setDisable(true);
            trigger3.setDisable(true);
            background3.setDisable(true);
            btnLivingroom.setDisable(true);
            btnRoom1.setDisable(true);
            btnRoom2.setDisable(true);
            btnRoom3.setDisable(true);
            btnTempUp.setDisable(true);
            btnTempDown.setDisable(true);
            
            
        }
    }
       
}

  

    


