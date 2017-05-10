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
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;

/**
 * FXML Controller class
 *
 * @author Administrator
 */
public class HeatController implements Initializable {

	@FXML
	private BorderPane heat;
	@FXML
	private Button btnPower;
	@FXML
	private Button btnOut;
	@FXML
	private Button btnHotwater;
	@FXML
	private Label lblTemp;
	@FXML
	private Label lblTempControl;
	@FXML
	private ImageView btnPowerImg;
	@FXML
	private ImageView btnOutImg;
	@FXML
	private ImageView btnHotwaterImg;
	@FXML
	private BorderPane heatChange;

	/**
	 * Initializes the controller class.
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		btnPower.setOnAction(e -> handleBtnPower(e));
		btnOut.setOnAction(e -> handleBtnOut(e));
		btnHotwater.setOnAction(e -> handleBtnHotwater(e));
		
	}

	private void handleBtnPower(ActionEvent e) {
		if (btnPower.getId().equals("true")) {
			btnPower.setId("false");
			btnPowerImg.setImage(new Image(getClass().getResource("images/heat_power_on.png").toString()));
			return;
		} else if (btnPower.getId().equals("false")) {
			btnPower.setId("true");
			btnPowerImg.setImage(new Image(getClass().getResource("images/heat_power_off.png").toString()));
		}
	}

	private void handleBtnOut(ActionEvent e) {
		if (btnOut.getId().equals("true")) {
			btnOut.setId("false");
			btnOutImg.setImage(new Image(getClass().getResource("images/heat_out.png").toString()));
			return;
		} else if (btnOut.getId().equals("false")) {
			btnOut.setId("true");
			btnOutImg.setImage(new Image(getClass().getResource("images/heat_out.png").toString()));
		}
	}

	private void handleBtnHotwater(ActionEvent e) {
		if (btnHotwater.getId().equals("true")) {
			btnHotwater.setId("false");
			btnHotwaterImg.setImage(new Image(getClass().getResource("images/heat_hotwater.png").toString()));
			return;
		} else if (btnHotwater.getId().equals("false")) {
			btnHotwaterImg.setId("true");
			btnHotwaterImg.setImage(new Image(getClass().getResource("images/heat_hotwater.png").toString()));
		}
	}

}
