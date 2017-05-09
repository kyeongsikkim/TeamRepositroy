/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch17.control;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.StackPane;

/**
 * FXML Controller class
 *
 * @author Administrator
 */
public class ControlController implements Initializable {

	@FXML
	private ToggleGroup menuGroup;
	@FXML
	private ToggleButton btnLight;
	@FXML
	private ToggleButton btnGas;
	@FXML
	private ToggleButton btnHeat;
	@FXML
	private StackPane stackPane;

	/**
	 * Initializes the controller class.
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		
		btnLight.setOnAction(e->handleBtnLight(e));
		btnGas.setOnAction(e->handleBtnGas(e));
		btnHeat.setOnAction(e->handleBtnHeat(e));
                
                menuGroup.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
                    @Override
                    public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {
                        String fileName=newValue.getUserData().toString()+".fxml";
                        try {
                            stackPane.getChildren().clear();
                            Parent parent = FXMLLoader.load(getClass().getResource(fileName));
                            stackPane.getChildren().add(parent);
                            
                        } catch (IOException ex) {}
                      
                    }
                });
               
	}	

	private void handleBtnLight(ActionEvent e) {
		try {
			Parent parent=FXMLLoader.load(getClass().getResource("light.fxml"));
			stackPane.getChildren().add(parent);
			
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	private void handleBtnGas(ActionEvent e) {
		try {
			Parent parent=FXMLLoader.load(getClass().getResource("gas.fxml"));
			stackPane.getChildren().add(parent);
			
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	private void handleBtnHeat(ActionEvent e) {
		try {
			Parent parent=FXMLLoader.load(getClass().getResource("heat.fxml"));
			stackPane.getChildren().add(parent);
			
			
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	
	
	
}
