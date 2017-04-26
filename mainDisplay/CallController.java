/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mainDisplay;

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
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Administrator
 */
public class CallController implements Initializable {

	@FXML
	private TextArea txtDisplay;
	@FXML
	private Button btn1;
	@FXML
	private Button btn2;
	@FXML
	private Button btn3;
	@FXML
	private Button btn4;
	@FXML
	private Button btn5;
	@FXML
	private Button btn6;
	@FXML
	private Button btn7;
	@FXML
	private Button btn8;
	@FXML
	private Button btn9;
	@FXML
	private Button btnSharp;
	@FXML
	private Button btn0;
	@FXML
	private Button btnStar;
	@FXML
	private Button btnCass;
	@FXML
	private Button btnCancle;
	@FXML
	private Button btnOut;

	/**
	 * Initializes the controller class.
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		btn1.setOnAction(e -> handleBtnNumber());
		btn2.setOnAction(e -> handleBtnNumber());
		btn3.setOnAction(e -> handleBtnNumber());
		btn4.setOnAction(e -> handleBtnNumber());
		btn5.setOnAction(e -> handleBtnNumber());
		btn6.setOnAction(e -> handleBtnNumber());
		btn7.setOnAction(e -> handleBtnNumber());
		btn8.setOnAction(e -> handleBtnNumber());
		btn9.setOnAction(e -> handleBtnNumber());
		btn0.setOnAction(e -> handleBtnNumber());
		btnSharp.setOnAction(e -> handleBtnNumber());
		btnStar.setOnAction(e -> handleBtnNumber());
		btnCass.setOnAction(e -> handleBtnNumber());
		btnCancle.setOnAction(e -> handleBtnNumber());
		btnOut.setOnAction(e -> handleBtnOut());
		
//		btn1.onActionProperty().addListener(new ChangeListener<EventHandler<ActionEvent>>() {
//			@Override
//			public void changed(ObservableValue<? extends EventHandler<ActionEvent>> observable, EventHandler<ActionEvent> oldValue, EventHandler<ActionEvent> newValue) {
//				txtDisplay.appendText("1");
//			}
//		});
//		
//		btn2.pressedProperty().addListener(new ChangeListener<Boolean>() {
//			@Override
//			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
//				txtDisplay.appendText("2");
//			}
//			
//		});
	}	

	private void handleBtnNumber() {
		if(btn1.isFocused()) {
			txtDisplay.appendText("1");
		} else if(btn2.isFocused()) {
			txtDisplay.appendText("2");
		} else if(btn3.isFocused()) {
			txtDisplay.appendText("3");
		} else if(btn4.isFocused()) {
			txtDisplay.appendText("4");
		} else if(btn5.isFocused()) {
			txtDisplay.appendText("5");
		} else if(btn6.isFocused()) {
			txtDisplay.appendText("6");
		} else if(btn7.isFocused()) {
			txtDisplay.appendText("7");
		} else if(btn8.isFocused()) {
			txtDisplay.appendText("8");
		} else if(btn9.isFocused()) {
			txtDisplay.appendText("9");
		} else if(btn0.isFocused()) {
			txtDisplay.appendText("0");
		} else if(btnSharp.isFocused()) {
			txtDisplay.appendText("#");
		} else if(btnStar.isFocused()) {
			txtDisplay.appendText("*");
		}  else if(btnCass.isFocused()) {
			txtDisplay.setText("전화 거는 중...........");
		} else if(btnCancle.isFocused()) {
			txtDisplay.clear();
		}
	}

	private void handleBtnOut() {
		try {
	    Parent parent = FXMLLoader.load(getClass().getResource("main.fxml"));
	    Scene scene = new Scene(parent);
	
	    Stage primaryStage = (Stage) btnOut.getScene().getWindow();
	    primaryStage.setScene(scene);
	    primaryStage.show();
	    
	} catch (IOException ex) {}
	}
	
}
