/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.camera;

import client.camera.camerapackage.ViewerCanvas;
import java.awt.AWTException;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javax.imageio.ImageIO;
import org.eclipse.californium.core.CoapClient;
import org.eclipse.californium.core.CoapResponse;
import org.eclipse.californium.core.coap.MediaTypeRegistry;
import org.json.JSONObject;

/**
 * FXML Controller class
 *
 * @author KIM
 */
public class CameraLodingController implements Initializable {

	private int rightLeftAngle;
	private int updownAngle;
	@FXML
	private StackPane view;
	@FXML
	private Button up;
	@FXML
	private Button left;
	@FXML
	private Button down;
	@FXML
	private Button right;
	@FXML
	private Button center;
	@FXML
	private AnchorPane controllerPane;
	@FXML
	private Button btnCapture;
	@FXML
	private Button btnPlay;
	@FXML
	private Button btnClose;

	@FXML
	private AnchorPane closeBackground;

	ViewerCanvas camera;
	@FXML
	private Text leftright;
	@FXML
	private Text updown;
	@FXML
	private ProgressBar progressBar;

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		camera = new ViewerCanvas(292, 320);
		try {
			camera.setCurrentURL(new URL("http://192.168.3.44:50001/?action=stream"));
		} catch (MalformedURLException ex) {
		}
		view.getChildren().add(camera);
		////////////////////응답//////////////////////////////////////////////////
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("command", "status");
		String reqJson = jsonObject.toString();
		CoapClient coapClient = new CoapClient();
		coapClient.setURI("coap://192.168.3.44/camera");
		CoapResponse coapResponse = coapClient.post(reqJson, MediaTypeRegistry.APPLICATION_JSON);
		String resJson = coapResponse.getResponseText();
		jsonObject = new JSONObject(resJson);
		leftright.setText("rightLeftAngle:" + jsonObject.getString("leftright"));
		updown.setText("updownAngle:" + jsonObject.getString("updown"));
		rightLeftAngle = Integer.parseInt(jsonObject.getString("leftright"));
		updownAngle = Integer.parseInt(jsonObject.getString("updown"));
		/////////////////////////////////////////////////////////////////////////////////////

		btnPlay.setOnAction(e -> handlePlay(e));
		btnClose.setOnAction(e -> handleClose(e));
		up.setOnAction(e -> handleUp(e));
		down.setOnAction(e -> handleDown(e));
		left.setOnAction(e -> handleLeft(e));
		right.setOnAction(e -> handleRight(e));
		center.setOnAction(e -> handleCenter(e));
		btnCapture.setOnAction(e -> handleCapture(e));

	}

	public void handleCapture(ActionEvent e) {
		Point point = MouseInfo.getPointerInfo().getLocation();
		try {
			Robot robot = new Robot();
			Rectangle rectangle = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());

			BufferedImage image = robot.createScreenCapture(rectangle);
			Image myImage = SwingFXUtils.toFXImage(image, null);
			try {
				ImageIO.write(image, "jpg", new File("out.jpg"));
			} catch (IOException ex) {

			}

		} catch (AWTException ex) {
		}
	}

	public void handlePlay(ActionEvent e) {
		view.getChildren().clear();
		Thread thread = new Thread() {
			@Override
			public void run() {
				Platform.runLater(() -> {

					view.getChildren().add(progressBar);
				});
				try {
					Thread.sleep(1000);
					Platform.runLater(() -> {
						view.getChildren().add(camera);
					});
				} catch (InterruptedException ex) {
				}
			}
		};
		thread.start();

	}

	public void handleClose(ActionEvent e) {

		view.getChildren().clear();
		view.getChildren().add(closeBackground);

	}

	public void handleAngle(int rightLeftAngle, int updownAngle) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("command", "change");
		jsonObject.put("leftright", String.valueOf(rightLeftAngle));
		jsonObject.put("updown", String.valueOf(updownAngle));
		String reqJson = jsonObject.toString();

		CoapClient coapClient = new CoapClient();
		coapClient.setURI("coap://192.168.3.44/camera");
		CoapResponse coapResponse = coapClient.post(reqJson, MediaTypeRegistry.APPLICATION_JSON);
		String resJson = coapResponse.getResponseText();
		jsonObject = new JSONObject(resJson);
		coapClient.shutdown();
		leftright.setText("rightLeftAngle:" + jsonObject.getString("leftright"));
		updown.setText("updownAngle:" + jsonObject.getString("updown"));

	}

	public void handleUp(ActionEvent e) {
		updownAngle += 20;
		if (updownAngle >= 120) {
			updownAngle = 100;
		}
		handleAngle(rightLeftAngle, updownAngle);
	}

	public void handleDown(ActionEvent e) {
		updownAngle -= 20;
		if (updownAngle <= 20) {
			updownAngle = 20;
		}
		handleAngle(rightLeftAngle, updownAngle);
	}

	public void handleLeft(ActionEvent e) {
		rightLeftAngle += 20;
		if (rightLeftAngle > 170) {
			rightLeftAngle = 150;
		}

		handleAngle(rightLeftAngle, updownAngle);
	}

	public void handleRight(ActionEvent e) {
		rightLeftAngle -= 20;
		if (rightLeftAngle <= 20) {
			rightLeftAngle = 30;
		}
		handleAngle(rightLeftAngle, updownAngle);
	}

	public void handleCenter(ActionEvent e) {
		rightLeftAngle = 90;
		updownAngle = 10;
		handleAngle(rightLeftAngle, updownAngle);
	}

}
