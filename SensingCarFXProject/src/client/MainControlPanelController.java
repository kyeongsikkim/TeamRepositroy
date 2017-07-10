package client;

import client.camera.camerapackage.ViewerCanvas;
import client.driving.MotorGaugeController;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import static javafx.scene.paint.Color.rgb;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Popup;
import javafx.util.Duration;
import org.apache.log4j.helpers.Loader;
import org.eclipse.californium.core.CoapClient;
import org.eclipse.californium.core.CoapHandler;
import org.eclipse.californium.core.CoapObserveRelation;
import org.eclipse.californium.core.CoapResponse;
import org.eclipse.californium.core.coap.MediaTypeRegistry;
import org.json.JSONObject;

public class MainControlPanelController implements Initializable {

	private MotorGaugeController motorGaugeController;

	private CoapObserveRelation coapObserveRelation;
	private SensorValue sensorValue;

	@FXML
	private Label labelTemperature;
	@FXML
	private Label labelTemperatureValue;
	@FXML
	private Label labelPhotoresistor;
	@FXML
	private Label labelPhotoresistorValue;
	@FXML
	private Label labelGas;
	@FXML
	private Label labelGasValue;
	@FXML
	private Label labelDistance;
	@FXML
	private Label labelDistanceValue;
	@FXML
	private Label labelTracking;
	@FXML
	private Circle labelTrackingValue;
	@FXML
	private Label labelDate;
	@FXML
	private Label labelTime;
	@FXML
	private VBox vboxCamera;
	@FXML
	private Label labelline0;
	@FXML
	private Label labelline1;
	@FXML
	private Label labelR;
	@FXML
	private Label labelG;
	@FXML
	private Label labelB;
	@FXML
	private Label labelSpeed;
	@FXML
	private Label labelDirection;
	@FXML
	private Label labelFrontAngle;
	@FXML
	private VBox vboxTemperature;
	@FXML
	private VBox vboxPicture;
	@FXML
	private VBox vboxBrightness;
	@FXML
	private VBox vboxRgb;
	@FXML
	private VBox vboxLcd;
	@FXML
	private VBox vboxDriving;
	@FXML
	private VBox vboxGas;
	@FXML
	private VBox vboxDistance;
	@FXML
	private AnchorPane primaryAnchorPane;
	@FXML
	private AnchorPane btnLaserOnOff;
	@FXML
	private Label lblLaserOnOff;
	@FXML
	private AnchorPane btnBuzzerOnOff;
	@FXML
	private Label lblBuzzerOnOff;
	@FXML
	private StackPane stackPane;

	private Button btnRgbReturn;
	private Button btnLcdReturn;
	private Button btnCameraReturn;

	private AnchorPane anchorPane;
	private ViewerCanvas cameraView;
	@FXML
	private Rectangle recRgbStatus;

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		sensorValue = SensorValue.getInstance();
		startDateTime();
		setTemperature();
		setBrightness();
		setGas();
		setDistance();
		setTracking();
		setCamera();
		setDefaultValue();

		vboxTemperature.setOnMousePressed(e -> handleVboxTemperaturePressed());
		vboxTemperature.setOnMouseReleased(e -> handleVboxTemperatureReleased());
		vboxBrightness.setOnMousePressed(e -> handleVboxBrightnessPressed());
		vboxBrightness.setOnMouseReleased(e -> handleVboxBrightnessReleased());
		vboxGas.setOnMousePressed(e -> handleVboxGasPressed());
		vboxGas.setOnMouseReleased(e -> handleVboxGasReleased());
		vboxDistance.setOnMousePressed(e -> handleVboxDistancePressed());
		vboxDistance.setOnMouseReleased(e -> handleVboxDistanceReleased());
		vboxRgb.setOnMousePressed(e -> handleVboxRgbPressed());
		vboxRgb.setOnMouseReleased(e -> handleVboxRgbReleased());
		vboxDriving.setOnMousePressed(e -> handleVboxDrivingPressed());
		vboxDriving.setOnMouseReleased(e -> handleVboxDrivingReleased());
		vboxCamera.setOnMousePressed(e -> handleVboxCameraPressed());
		vboxCamera.setOnMouseReleased(e -> handleVboxCameraReleased());
		vboxLcd.setOnMousePressed(e -> handleVboxLcdPressed());
		vboxLcd.setOnMouseReleased(e -> handleVboxLcdReleased());

		btnLaserOnOff.setOnMouseClicked(e -> handleBtnLaserOnOff(e));
		btnBuzzerOnOff.setOnMouseClicked(e -> handleBtnBuzzerOnOff(e));
		lblLaserOnOff.setText("OFF");
		lblLaserOnOff.setAlignment(Pos.CENTER);
		lblBuzzerOnOff.setText("OFF");
		lblBuzzerOnOff.setAlignment(Pos.CENTER);

	}

	private void startDateTime() {
		Thread timeThread = new Thread() {
			@Override
			public void run() {
				SimpleDateFormat sdf1 = new SimpleDateFormat("HH시 mm분 ss초");
				SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy년 MM월 dd일(E)");
				boolean stop = false;
				while (!stop) {
					String nowTime = sdf1.format(new Date());
					String nowDate = sdf2.format(new Date());
					Platform.runLater(() -> {
						labelTime.setText(nowTime);
						labelDate.setText(nowDate);
					});

					try {
						Thread.sleep(1000);
					} catch (InterruptedException ex) {
					}
				}
			}
		};
		timeThread.setDaemon(true);
		timeThread.start();
	}

	private void setTemperature() {
		CoapClient coapClient = new CoapClient();
		coapClient.setURI("coap://192.168.3.44/thermistorsensor");

		Thread tempThread = new Thread() {
			@Override
			public void run() {
				CoapObserveRelation coapObserveRelation = coapClient.observe(new CoapHandler() {
					@Override
					public void onLoad(CoapResponse response) {
						String text = response.getResponseText();
						JSONObject jsonObject = new JSONObject(text);
						Double doubleT = Double.parseDouble(jsonObject.getString("temperature"));
						Double temperature = (int) (doubleT * 10) / 10.0;
						sensorValue.setTemperature(temperature);

						Platform.runLater(() -> {
							labelTemperatureValue.setText(String.valueOf(temperature + "°C"));
						});
					}

					@Override
					public void onError() {

					}
				});
			}
		};
		tempThread.setDaemon(true);
		tempThread.start();
	}

	private void setBrightness() {
		CoapClient coapClient = new CoapClient();
		coapClient.setURI("coap://192.168.3.44/photoresistorsensor");

		Thread photoThread = new Thread() {
			@Override
			public void run() {
				CoapObserveRelation coapObserveRelation = coapClient.observe(new CoapHandler() {
					@Override
					public void onLoad(CoapResponse response) {
						String text = response.getResponseText();
						JSONObject jsonObject = new JSONObject(text);
						Double brightness = Double.parseDouble(jsonObject.getString("photoresistor"));
						sensorValue.setBrightness(brightness);

						Platform.runLater(() -> {
							labelPhotoresistorValue.setText(String.valueOf(brightness));
						});
					}

					@Override
					public void onError() {

					}
				});
			}
		};
		photoThread.setDaemon(true);
		photoThread.start();
	}

	private void setGas() {
		CoapClient coapClient = new CoapClient();
		coapClient.setURI("coap://192.168.3.44/gassensor");

		Thread gasThread = new Thread() {
			@Override
			public void run() {
				CoapObserveRelation coapObserveRelation = coapClient.observe(new CoapHandler() {
					@Override
					public void onLoad(CoapResponse response) {
						String text = response.getResponseText();
						JSONObject jsonObject = new JSONObject(text);
						Double gas = Double.parseDouble(jsonObject.getString("gas"));
						sensorValue.setGas(gas);

						Platform.runLater(() -> {
							labelGasValue.setText(String.valueOf(gas));
						});
					}

					@Override
					public void onError() {

					}
				});
			}
		};
		gasThread.setDaemon(true);
		gasThread.start();
	}

	private void setDistance() {
		CoapClient coapClient = new CoapClient();
		coapClient.setURI("coap://192.168.3.44/ultrasonicsensor");

		Thread disThread = new Thread() {
			@Override
			public void run() {
				CoapObserveRelation coapObserveRelation = coapClient.observe(new CoapHandler() {
					@Override
					public void onLoad(CoapResponse response) {
						String text = response.getResponseText();
						JSONObject jsonObject = new JSONObject(text);
						Double distance = Double.parseDouble(jsonObject.getString("distance"));
						sensorValue.setDistance(distance);

						Platform.runLater(() -> {
							labelDistanceValue.setText(String.valueOf(distance) + "cm");
						});
					}

					@Override
					public void onError() {

					}
				});
			}
		};
		disThread.setDaemon(true);
		disThread.start();
	}

	private void setTracking() {
		CoapClient coapClient = new CoapClient();
		coapClient.setURI("coap://192.168.3.44/trackingsensor");

		Thread trackingThread = new Thread() {
			@Override
			public void run() {
				CoapObserveRelation coapObserveRelation = coapClient.observe(new CoapHandler() {
					@Override
					public void onLoad(CoapResponse response) {
						String text = response.getResponseText();
						JSONObject jsonObject = new JSONObject(text);
						String tracking = jsonObject.getString("tracking");

						Platform.runLater(() -> {
							if (tracking.equals("black")) {
								labelTrackingValue.setFill(Color.BLACK);
							} else {
								labelTrackingValue.setFill(Color.WHITE);
							}

						});
					}

					@Override
					public void onError() {

					}
				});
			}
		};
		trackingThread.setDaemon(true);
		trackingThread.start();
	}

	private void setCamera() {
//        cameraView = new ViewerCanvas(255, 200);
//        try {
//            cameraView.setCurrentURL(new URL("http://192.168.3.44:50001/?action=stream"));
//        } catch (MalformedURLException ex) {
//        }
//        vboxCamera.getChildren().add(cameraView);
	}

	private void setDefaultValue() {
		Thread thread = new Thread() {
			@Override
			public void run() {
				while (true) {
					CoapClient coapClient = new CoapClient();
					JSONObject jsonObject = null;
					String json = null;
					CoapResponse coapResponse = null;
					// ---------------------------------------------------------
					jsonObject = new JSONObject();
					jsonObject.put("command", "status");
					json = jsonObject.toString();
					coapClient.setURI("coap://192.168.3.44/lcd");
					coapResponse = coapClient.post(json, MediaTypeRegistry.APPLICATION_JSON);
					json = coapResponse.getResponseText();
					jsonObject = new JSONObject(json);
					String lcdline0 = jsonObject.getString("line0");
					String lcdline1 = jsonObject.getString("line1");
					Platform.runLater(() -> {
						labelline0.setText("line0 : " + lcdline0);
						labelline1.setText("line1 : " + lcdline1);
					});

					//---------------------------------------------------------
					jsonObject = new JSONObject();
					jsonObject.put("command", "status");
					json = jsonObject.toString();
					coapClient.setURI("coap://192.168.3.44/rgbled");
					coapResponse = coapClient.post(json, MediaTypeRegistry.APPLICATION_JSON);
					json = coapResponse.getResponseText();
					jsonObject = new JSONObject(json);
					int r = Integer.parseInt(jsonObject.getString("red"));
					int g = Integer.parseInt(jsonObject.getString("green"));
					int b = Integer.parseInt(jsonObject.getString("blue"));
					Platform.runLater(() -> {
						labelR.setText("R : " + String.valueOf(r));
						labelG.setText("G : " + String.valueOf(g));
						labelB.setText("B : " + String.valueOf(b));
						recRgbStatus.setFill(rgb(r, g, b));
					});
					// ---------------------------------------------------------
					jsonObject = new JSONObject();
					jsonObject.put("command", "status");
					json = jsonObject.toString();
					coapClient.setURI("coap://192.168.3.44/fronttire");
					coapResponse = coapClient.post(json, MediaTypeRegistry.APPLICATION_JSON);
					json = coapResponse.getResponseText();
					jsonObject = new JSONObject(json);
					int angle = Integer.parseInt(jsonObject.getString("angle"));
					Platform.runLater(() -> {
						labelFrontAngle.setText("Angle : " + String.valueOf(angle) + "º");
					});

					// ---------------------------------------------------------
					jsonObject = new JSONObject();
					jsonObject.put("command", "status");
					json = jsonObject.toString();
					coapClient.setURI("coap://192.168.3.44/backtire");
					coapResponse = coapClient.post(json, MediaTypeRegistry.APPLICATION_JSON);
					json = coapResponse.getResponseText();
					jsonObject = new JSONObject(json);
					int speed = Integer.parseInt(jsonObject.getString("speed"));
					String direction = jsonObject.getString("direction");
					Platform.runLater(() -> {
						labelSpeed.setText("Speed : " + String.valueOf(speed));
						labelDirection.setText("Dir. : " + direction);
					});

					try {
						//------------------------------------------
						Thread.sleep(1000);
					} catch (InterruptedException ex) {}
				}
			}
		};
		thread.start();
	}

	private void setLaser(String status) {
		CoapClient coapClient = new CoapClient();

		JSONObject reqJsonObject = new JSONObject();
		reqJsonObject.put("command", "change");
		reqJsonObject.put("status", status);
		String reqJson = reqJsonObject.toString();

		coapClient.setURI("coap://192.168.3.44/laseremitter");
		CoapResponse coapResponse = coapClient.post(reqJson, MediaTypeRegistry.APPLICATION_JSON);
		String resJson = coapResponse.getResponseText();

		coapClient.shutdown();
	}

	private void setBuzzer(String status) {
		CoapClient coapClient = new CoapClient();

		JSONObject reqJsonObject = new JSONObject();
		reqJsonObject.put("command", "change");
		reqJsonObject.put("status", status);
		String reqJson = reqJsonObject.toString();

		coapClient.setURI("coap://192.168.3.44/buzzer");
		CoapResponse coapResponse = coapClient.post(reqJson, MediaTypeRegistry.APPLICATION_JSON);
		String resJson = coapResponse.getResponseText();

		coapClient.shutdown();
	}

	private void handleBtnLaserOnOff(MouseEvent e) {

		if (btnLaserOnOff.getUserData().equals("off")) {
			laserOnAnimation();
			setLaser("on");
		} else if (btnLaserOnOff.getUserData().equals("on")) {
			laserOffAnimation();
			setLaser("off");
		}
	}

	private void laserOnAnimation() {
		btnLaserOnOff.setUserData("on");

		KeyValue keyValue1 = new KeyValue(lblLaserOnOff.translateYProperty(), -75);
		KeyFrame keyFrame1 = new KeyFrame(Duration.seconds(0.5), keyValue1);

		Timeline timeline = new Timeline();
		timeline.getKeyFrames().add(keyFrame1);

		timeline.play();

		lblLaserOnOff.setAlignment(Pos.CENTER);
		lblLaserOnOff.setTextFill(Color.RED);
		lblLaserOnOff.setText("ON");
	}

	private void laserOffAnimation() {
		btnLaserOnOff.setUserData("off");

		KeyValue keyValue1 = new KeyValue(lblLaserOnOff.translateYProperty(), 0);
		KeyFrame keyFrame1 = new KeyFrame(Duration.seconds(0.5), keyValue1);

		Timeline timeline = new Timeline();
		timeline.getKeyFrames().add(keyFrame1);

		timeline.play();
		lblLaserOnOff.setTextFill(Color.BLACK);
		lblLaserOnOff.setAlignment(Pos.CENTER);
		lblLaserOnOff.setText("OFF");
	}

	private void handleBtnBuzzerOnOff(MouseEvent e) {
		if (btnBuzzerOnOff.getUserData().equals("off")) {
			buzzerOnAnimation();
			setBuzzer("on");
		} else if (btnBuzzerOnOff.getUserData().equals("on")) {
			buzzerOffAnimation();
			setBuzzer("off");
		}
	}

	private void buzzerOnAnimation() {
		btnBuzzerOnOff.setUserData("on");

		KeyValue keyValue1 = new KeyValue(lblBuzzerOnOff.translateYProperty(), -75);
		KeyFrame keyFrame1 = new KeyFrame(Duration.seconds(0.3), keyValue1);

		Timeline timeline = new Timeline();
		timeline.getKeyFrames().add(keyFrame1);

		timeline.play();

		lblBuzzerOnOff.setAlignment(Pos.CENTER);
		lblBuzzerOnOff.setTextFill(Color.RED);
		lblBuzzerOnOff.setText("ON");
	}

	private void buzzerOffAnimation() {
		btnBuzzerOnOff.setUserData("off");

		KeyValue keyValue1 = new KeyValue(lblBuzzerOnOff.translateYProperty(), 0);
		KeyFrame keyFrame1 = new KeyFrame(Duration.seconds(0.3), keyValue1);

		Timeline timeline = new Timeline();
		timeline.getKeyFrames().add(keyFrame1);

		timeline.play();
		lblBuzzerOnOff.setTextFill(Color.BLACK);
		lblBuzzerOnOff.setAlignment(Pos.CENTER);
		lblBuzzerOnOff.setText("OFF");
	}

	private void handleVboxTemperaturePressed() {
		KeyValue key1 = new KeyValue(vboxTemperature.translateXProperty(), 1);
		KeyValue key2 = new KeyValue(vboxTemperature.translateYProperty(), 4);
		KeyFrame frame1 = new KeyFrame(Duration.seconds(0.1), key1);
		KeyFrame frame2 = new KeyFrame(Duration.seconds(0.1), key2);
		Timeline timeline = new Timeline();
		timeline.getKeyFrames().addAll(frame1, frame2);
		timeline.play();
	}

	private void handleVboxTemperatureReleased() {
		KeyValue key1 = new KeyValue(vboxTemperature.translateXProperty(), 0);
		KeyValue key2 = new KeyValue(vboxTemperature.translateYProperty(), 0);
		KeyFrame frame1 = new KeyFrame(Duration.seconds(0.1), key1);
		KeyFrame frame2 = new KeyFrame(Duration.seconds(0.1), key2);
		Timeline timeline = new Timeline();
		timeline.getKeyFrames().addAll(frame1, frame2);
		timeline.play();

		Popup popup = new Popup();
		try {
			anchorPane = (AnchorPane) FXMLLoader.load(getClass().getResource("chart/thermistorChart.fxml"));
			popup.getContent().add(anchorPane);
			popup.setAutoHide(true);
			popup.show(AppMain.primaryStage);
		} catch (IOException ex) {
		}
	}

	private void handleVboxBrightnessPressed() {
		KeyValue key1 = new KeyValue(vboxBrightness.translateXProperty(), 1);
		KeyValue key2 = new KeyValue(vboxBrightness.translateYProperty(), 4);
		KeyFrame frame1 = new KeyFrame(Duration.seconds(0.1), key1);
		KeyFrame frame2 = new KeyFrame(Duration.seconds(0.1), key2);
		Timeline timeline = new Timeline();
		timeline.getKeyFrames().addAll(frame1, frame2);
		timeline.play();
	}

	private void handleVboxBrightnessReleased() {
		KeyValue key1 = new KeyValue(vboxBrightness.translateXProperty(), 0);
		KeyValue key2 = new KeyValue(vboxBrightness.translateYProperty(), 0);
		KeyFrame frame1 = new KeyFrame(Duration.seconds(0.1), key1);
		KeyFrame frame2 = new KeyFrame(Duration.seconds(0.1), key2);
		Timeline timeline = new Timeline();
		timeline.getKeyFrames().addAll(frame1, frame2);
		timeline.play();

		Popup popup = new Popup();
		try {
			anchorPane = (AnchorPane) FXMLLoader.load(getClass().getResource("chart/photoresistorChart.fxml"));
			popup.getContent().add(anchorPane);
			popup.setAutoHide(true);
			popup.show(AppMain.primaryStage);
		} catch (IOException ex) {
		}
	}

	private void handleVboxGasPressed() {
		KeyValue key1 = new KeyValue(vboxGas.translateXProperty(), 1);
		KeyValue key2 = new KeyValue(vboxGas.translateYProperty(), 4);
		KeyFrame frame1 = new KeyFrame(Duration.seconds(0.1), key1);
		KeyFrame frame2 = new KeyFrame(Duration.seconds(0.1), key2);
		Timeline timeline = new Timeline();
		timeline.getKeyFrames().addAll(frame1, frame2);
		timeline.play();
	}

	private void handleVboxGasReleased() {
		KeyValue key1 = new KeyValue(vboxGas.translateXProperty(), 0);
		KeyValue key2 = new KeyValue(vboxGas.translateYProperty(), 0);
		KeyFrame frame1 = new KeyFrame(Duration.seconds(0.1), key1);
		KeyFrame frame2 = new KeyFrame(Duration.seconds(0.1), key2);
		Timeline timeline = new Timeline();
		timeline.getKeyFrames().addAll(frame1, frame2);
		timeline.play();

		Popup popup = new Popup();
		try {
			anchorPane = (AnchorPane) FXMLLoader.load(getClass().getResource("chart/gasChart.fxml"));
			popup.getContent().add(anchorPane);
			popup.setAutoHide(true);
			popup.show(AppMain.primaryStage);
		} catch (IOException ex) {
		}
	}

	private void handleVboxDistancePressed() {
		KeyValue key1 = new KeyValue(vboxDistance.translateXProperty(), 1);
		KeyValue key2 = new KeyValue(vboxDistance.translateYProperty(), 4);
		KeyFrame frame1 = new KeyFrame(Duration.seconds(0.1), key1);
		KeyFrame frame2 = new KeyFrame(Duration.seconds(0.1), key2);
		Timeline timeline = new Timeline();
		timeline.getKeyFrames().addAll(frame1, frame2);
		timeline.play();
	}

	private void handleVboxDistanceReleased() {
		KeyValue key1 = new KeyValue(vboxDistance.translateXProperty(), 0);
		KeyValue key2 = new KeyValue(vboxDistance.translateYProperty(), 0);
		KeyFrame frame1 = new KeyFrame(Duration.seconds(0.1), key1);
		KeyFrame frame2 = new KeyFrame(Duration.seconds(0.1), key2);
		Timeline timeline = new Timeline();
		timeline.getKeyFrames().addAll(frame1, frame2);
		timeline.play();

		Popup popup = new Popup();
		try {
			anchorPane = (AnchorPane) FXMLLoader.load(getClass().getResource("chart/ultrasonicChart.fxml"));
			popup.getContent().add(anchorPane);
			popup.setAutoHide(true);
			popup.show(AppMain.primaryStage);
		} catch (IOException ex) {
		}
	}

	private void handleVboxRgbPressed() {
		KeyValue key1 = new KeyValue(vboxRgb.translateXProperty(), 1);
		KeyValue key2 = new KeyValue(vboxRgb.translateYProperty(), 4);
		KeyFrame frame1 = new KeyFrame(Duration.seconds(0.1), key1);
		KeyFrame frame2 = new KeyFrame(Duration.seconds(0.1), key2);
		Timeline timeline = new Timeline();
		timeline.getKeyFrames().addAll(frame1, frame2);
		timeline.play();
	}

	private void handleVboxRgbReleased() {
		KeyValue key1 = new KeyValue(vboxRgb.translateXProperty(), 0);
		KeyValue key2 = new KeyValue(vboxRgb.translateYProperty(), 0);
		KeyFrame frame1 = new KeyFrame(Duration.seconds(0.1), key1);
		KeyFrame frame2 = new KeyFrame(Duration.seconds(0.1), key2);
		Timeline timeline = new Timeline();
		timeline.getKeyFrames().addAll(frame1, frame2);
		timeline.play();

		try {
			anchorPane = (AnchorPane) FXMLLoader.load(getClass().getResource("rgblcd/rgb.fxml"));
			btnRgbReturn = (Button) anchorPane.lookup("#btnRgbReturn");
		} catch (IOException ex) {
		}

		stackPane.getChildren().add(anchorPane);
		btnRgbReturn.setOnAction(e -> handleBtnReturn());
	}

	private void handleVboxDrivingPressed() {
		KeyValue key1 = new KeyValue(vboxDriving.translateXProperty(), 1);
		KeyValue key2 = new KeyValue(vboxDriving.translateYProperty(), 4);
		KeyFrame frame1 = new KeyFrame(Duration.seconds(0.1), key1);
		KeyFrame frame2 = new KeyFrame(Duration.seconds(0.1), key2);
		Timeline timeline = new Timeline();
		timeline.getKeyFrames().addAll(frame1, frame2);
		timeline.play();
	}

	private void handleVboxDrivingReleased() {
		KeyValue key1 = new KeyValue(vboxDriving.translateXProperty(), 0);
		KeyValue key2 = new KeyValue(vboxDriving.translateYProperty(), 0);
		KeyFrame frame1 = new KeyFrame(Duration.seconds(0.1), key1);
		KeyFrame frame2 = new KeyFrame(Duration.seconds(0.1), key2);
		Timeline timeline = new Timeline();
		timeline.getKeyFrames().addAll(frame1, frame2);
		timeline.play();
		
		MotorGaugeController controller = new MotorGaugeController();
		controller.init();
		controller.start(AppMain.primaryStage);
	}

	private void handleVboxCameraPressed() {
		KeyValue key1 = new KeyValue(vboxCamera.translateXProperty(), 1);
		KeyValue key2 = new KeyValue(vboxCamera.translateYProperty(), 4);
		KeyFrame frame1 = new KeyFrame(Duration.seconds(0.1), key1);
		KeyFrame frame2 = new KeyFrame(Duration.seconds(0.1), key2);
		Timeline timeline = new Timeline();
		timeline.getKeyFrames().addAll(frame1, frame2);
		timeline.play();
	}

	private void handleVboxCameraReleased() {
		KeyValue key1 = new KeyValue(vboxCamera.translateXProperty(), 0);
		KeyValue key2 = new KeyValue(vboxCamera.translateYProperty(), 0);
		KeyFrame frame1 = new KeyFrame(Duration.seconds(0.1), key1);
		KeyFrame frame2 = new KeyFrame(Duration.seconds(0.1), key2);
		Timeline timeline = new Timeline();
		timeline.getKeyFrames().addAll(frame1, frame2);
		timeline.play();

		try {
			anchorPane = (AnchorPane) FXMLLoader.load(getClass().getResource("camera/cameraLoding.fxml"));
			btnCameraReturn = (Button) anchorPane.lookup("#btnCameraReturn");
		} catch (IOException ex) {
			ex.printStackTrace();
		}

		stackPane.getChildren().add(anchorPane);
		btnCameraReturn.setOnAction(e -> handleBtnReturn());
	}

	private void handleVboxLcdPressed() {
		KeyValue key1 = new KeyValue(vboxLcd.translateXProperty(), 1);
		KeyValue key2 = new KeyValue(vboxLcd.translateYProperty(), 4);
		KeyFrame frame1 = new KeyFrame(Duration.seconds(0.1), key1);
		KeyFrame frame2 = new KeyFrame(Duration.seconds(0.1), key2);
		Timeline timeline = new Timeline();
		timeline.getKeyFrames().addAll(frame1, frame2);
		timeline.play();
	}

	private void handleVboxLcdReleased() {
		KeyValue key1 = new KeyValue(vboxLcd.translateXProperty(), 0);
		KeyValue key2 = new KeyValue(vboxLcd.translateYProperty(), 0);
		KeyFrame frame1 = new KeyFrame(Duration.seconds(0.1), key1);
		KeyFrame frame2 = new KeyFrame(Duration.seconds(0.1), key2);
		Timeline timeline = new Timeline();
		timeline.getKeyFrames().addAll(frame1, frame2);
		timeline.play();

		try {
			anchorPane = (AnchorPane) FXMLLoader.load(getClass().getResource("rgblcd/lcd.fxml"));
			btnLcdReturn = (Button) anchorPane.lookup("#btnLcdReturn");
		} catch (IOException ex) {
			ex.printStackTrace();
		}

		stackPane.getChildren().add(anchorPane);
		btnLcdReturn.setOnAction(e -> handleBtnReturn());
	}

	private void handleBtnReturn() {
		stackPane.getChildren().remove(1);
	}
}
