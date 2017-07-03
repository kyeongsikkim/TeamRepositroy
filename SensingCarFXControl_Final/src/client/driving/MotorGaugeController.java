package client.driving;

import client.SensorValue;
import eu.hansolo.medusa.Gauge;
import java.util.Random;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.stage.Stage;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.Border;
import javafx.scene.layout.GridPane;
import org.eclipse.californium.core.CoapClient;
import org.eclipse.californium.core.coap.MediaTypeRegistry;
import org.json.JSONObject;

public class MotorGaugeController extends Application {

    private SpeedGauge speedGauge;
    private AngleGauge angleGauge;
    private CoapClient coapClientS;
    private CoapClient coapClientA;
    private JSONObject jsonObjectS;
    private JSONObject jsonObjectA;
    private Button btn1;
    private Button btn2;
    private Button btn3;
    private Button btn4;
    private String jsonS;
    private String jsonA;
    private SensorValue sensorValue;
//    private long lastTimerCall;
//    private AnimationTimer timer;
//    private static final Random RND = new Random();

    public MotorGaugeController() {
        coapClientS = new CoapClient();
        coapClientA = new CoapClient();
        sensorValue = SensorValue.getInstance();
    }

    @Override
    public void init() {
        speedGauge = new SpeedGauge();
        angleGauge = new AngleGauge();
        btn1 = new Button();
        btn2 = new Button();
        btn3 = new Button();
        btn4 = new Button();

        coapClientS.setURI("coap://192.168.3.44/backtire");
        coapClientA.setURI("coap://192.168.3.44/fronttire");

        speedGauge.getSpeedGauge().setValue((int) sensorValue.getBacktireSpeed());
        angleGauge.getAngleGauge().setValue((int) sensorValue.getFronttireAngle());

//        lastTimerCall = System.nanoTime();
//        timer = new AnimationTimer() {
//            @Override
//            public void handle(long now) {
//                if (now > lastTimerCall + 3_000_000_000l) {
//                    speedGauge.getMotorGauge().setValue(RND.nextDouble() * 150 + 100);
//                    speedGauge.getOilGauge().setValue(RND.nextDouble() * 100);
//                    lastTimerCall = now;
//                }
//            }
//        };

        speedGauge.getSpeedGauge().valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                jsonObjectS = new JSONObject();
                jsonObjectS.put("command", "change");
                if (sensorValue.getBacktireDirection() == null) {
                    jsonObjectS.put("direction", "forward");
                } else {
                    jsonObjectS.put("direction", sensorValue.getBacktireDirection());
                }
                jsonObjectS.put("speed", String.valueOf(newValue.intValue()));
                sensorValue.setBacktireDirection(jsonObjectS.getString("direction"));
                sensorValue.setBacktireSpeed(newValue.intValue());
                jsonS = jsonObjectS.toString();
                System.out.println(jsonS);
                postS();
                shutDownS();
            }
        });

        angleGauge.getAngleGauge().valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                jsonObjectA = new JSONObject();
                jsonObjectA.put("command", "change");
                jsonObjectA.put("angle", String.valueOf(newValue.intValue()));
                jsonA = jsonObjectA.toString();
                sensorValue.setFronttireAngle(newValue.intValue());
                postA();
                shutDownA();
            }
        });

        btn1.setOnAction(e -> handleBtnForward(e));
        btn2.setOnAction(e -> handleBtnBackward(e));
        btn3.setOnAction(e -> {
            try {
                handleBtnAccelerate(e);
            } catch (InterruptedException ex) {
            }
        });
        btn4.setOnAction(e -> handleBtnBreak(e));
    }

    @Override
    public void start(Stage stage) {
        GridPane pane = new GridPane();
        GridPane pane1 = new GridPane();
        GridPane pane2 = new GridPane();
        btn1.setText("forward");
        btn2.setText("backward");
        btn3.setText("Accel");
        btn4.setText("Break");
        //pane.setStyle("-fx-background-image: url('../images/background1.jpeg')");
        btn1.setPrefWidth(150);
        btn1.setPrefHeight(40);
        btn2.setPrefWidth(150);
        btn2.setPrefHeight(40);
        btn3.setPrefWidth(130);
        btn3.setPrefHeight(40);
        btn4.setPrefWidth(130);
        btn4.setPrefHeight(40);
        pane1.add(btn1, 0, 0);
        pane1.add(btn2, 0, 1);
        pane2.add(btn3, 0, 0);
        pane2.add(btn4, 0, 1);
        pane.setPrefWidth(800);
        pane.setPrefHeight(480);
        pane.add(pane1, 0, 0);
        pane.add(speedGauge, 1, 0);
        pane.add(angleGauge, 2, 0);
        pane.add(pane2, 3, 0);
        pane.setAlignment(Pos.CENTER);
        pane1.setAlignment(Pos.CENTER);
        pane2.setAlignment(Pos.CENTER);
        pane.setHgap(30);
        pane1.setVgap(30);
        pane2.setVgap(30);
        pane.setPadding(new Insets(10));
        pane.setBackground(new Background(new BackgroundFill(Gauge.DARK_COLOR, CornerRadii.EMPTY, Insets.EMPTY)));
        pane.setId("gridGauge");

        Scene scene = new Scene(pane);

        stage.setTitle("SensingCar Motor");
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void stop() {
        System.exit(0);
    }

    public void postS() {
        coapClientS.post(jsonS, MediaTypeRegistry.APPLICATION_JSON);
    }

    public void postA() {
        coapClientA.post(jsonA, MediaTypeRegistry.APPLICATION_JSON);
    }

    public void shutDownS() {
        coapClientS.shutdown();
    }

    public void shutDownA() {
        coapClientA.shutdown();
    }

    public void handleBtnForward(ActionEvent e) {
        sensorValue.setBacktireDirection(btn1.getText());
        jsonObjectS = new JSONObject();
        jsonObjectS.put("command", "change");
        jsonObjectS.put("direction", btn1.getText());
        jsonObjectS.put("speed", String.valueOf((int) speedGauge.getSpeedGauge().getValue()));
        jsonS = jsonObjectS.toString();
        System.out.println(jsonS);
        postS();
        shutDownS();
    }
    //{"command":"change","speed":"0","direction":"forward"}

    private void handleBtnBackward(ActionEvent e) {
        sensorValue.setBacktireDirection(btn2.getText());
        jsonObjectS = new JSONObject();
        jsonObjectS.put("command", "change");
        jsonObjectS.put("direction", btn2.getText());
        jsonObjectS.put("speed", String.valueOf((int) speedGauge.getSpeedGauge().getValue()));
        jsonS = jsonObjectS.toString();
        postS();
        shutDownS();
    }

    private void handleBtnAccelerate(ActionEvent e) throws InterruptedException {
        int speed = 0;
        for (int i = 0; i < 5; i++) {
            speed = (int) sensorValue.getBacktireSpeed() + 200;
            Thread.sleep(1000);
        }
        sensorValue.setBacktireSpeed(speed);
        jsonObjectS = new JSONObject();
        jsonObjectS.put("command", "change");
        jsonObjectS.put("direction", sensorValue.getBacktireDirection());
        jsonObjectS.put("speed", String.valueOf(speed));
        jsonS = jsonObjectS.toString();
        postS();
        shutDownS();
    }

    private void handleBtnBreak(ActionEvent e) {
        sensorValue.setBacktireSpeed(0);
        jsonObjectS = new JSONObject();
        jsonObjectS.put("command", "change");
        jsonObjectS.put("direction", sensorValue.getBacktireDirection());
        jsonObjectS.put("speed", String.valueOf(sensorValue.getBacktireSpeed()));
        jsonS = jsonObjectS.toString();
        postS();
        speedGauge.getSpeedGauge().setValue(0.0);
        shutDownS();
    }

    public static void main(String[] args) throws InterruptedException {
        launch(args);
    }
}
