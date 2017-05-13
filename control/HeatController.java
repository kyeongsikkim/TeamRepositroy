/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch17.control;

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
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author Administrator
 */
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
    
    private BooleanProperty switchedOn = new SimpleBooleanProperty(false);
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

    public BooleanProperty switchedOnProperty() {
        return switchedOn;
    }
    public BooleanProperty switchedOnProperty2() {
        return switchedOn2;
    }
    public BooleanProperty switchedOnProperty3() {
        return switchedOn3;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
//        Font font1 = Font.loadFont(getClass().getResource("fonts/08SeoulNamsanEB.ttf").toExternalForm(), 16);
//        Font font2 = Font.loadFont(getClass().getResource("fonts/08SeoulNamsanEB.ttf").toExternalForm(), 20);
//        Font font3 = Font.loadFont(getClass().getResource("fonts/08SeoulNamsanEB.ttf").toExternalForm(), 27);
//        
//        lblPower.setFont(font1);
//        lblOut.setFont(font2);
//        lblHotwater.setFont(font3);
        
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
            translateAnimation.setToX(isOn ? 100 - 50 : 0);
            fillAnimation.setFromValue(isOn ? Color.WHITE : Color.LIGHTGREEN);
            fillAnimation.setToValue(isOn ? Color.WHEAT : Color.WHITE);
            animation.play();
        });
        switchedOn2.addListener((obs, oldState, newState) -> {
            boolean isOn2 = newState.booleanValue();
            translateAnimation2.setToX(isOn2 ? 100 - 50 : 0);
            fillAnimation2.setFromValue(isOn2 ? Color.WHITE : Color.LIGHTGREEN);
            fillAnimation2.setToValue(isOn2 ? Color.CORAL : Color.WHITE);
            animation2.play();
        });
        switchedOn3.addListener((obs, oldState, newState) -> {
            boolean isOn3 = newState.booleanValue();
            translateAnimation3.setToX(isOn3 ? 100 - 50 : 0);
            fillAnimation3.setFromValue(isOn3 ? Color.WHITE : Color.LIGHTGREEN);
            fillAnimation3.setToValue(isOn3 ? Color.TOMATO : Color.WHITE);
            animation3.play();
        });

        trigger1.setOnMouseClicked(event -> {switchedOn.set(!switchedOn.get());});
        background1.setOnMouseClicked(event -> {switchedOn.set(!switchedOn.get());});

        trigger2.setOnMouseClicked(event -> {switchedOn2.set(!switchedOn2.get());});
        background2.setOnMouseClicked(event -> {switchedOn2.set(!switchedOn2.get());});

        trigger3.setOnMouseClicked(event -> {switchedOn3.set(!switchedOn3.get());});
        background3.setOnMouseClicked(event -> {switchedOn3.set(!switchedOn3.get());});
    }

    private void handleBtnLivingroom(ActionEvent e) {
        int Temp=(int)(Math.random()*20)+1;
        lblTemp.setText(String.valueOf(Temp));
        
    }

    private void handleBtnroom1(ActionEvent e) {
        int Temp1=(int)(Math.random()*20)+1;
        lblTemp.setText(String.valueOf(Temp1));
        lblTempControl.setText(lblTemp.getText());
    }

    private void handleBtnroom2(ActionEvent e) {
        int Temp2=(int)(Math.random()*20)+1;
        lblTemp.setText(String.valueOf(Temp2));
        lblTempControl.setText(lblTemp.getText());
    }

    private void handleBtnroom3(ActionEvent e) {
        int Temp3=(int)(Math.random()*20)+1;
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
       
}

  

    


