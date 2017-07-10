package client.chart;

import client.SensorValue;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import org.eclipse.californium.core.CoapClient;
import org.eclipse.californium.core.CoapHandler;
import org.eclipse.californium.core.CoapObserveRelation;
import org.eclipse.californium.core.CoapResponse;
import org.json.JSONObject;

public class GasChartController implements Initializable {

    @FXML
    private AnchorPane chartPane;

    private List<Circle> circleList = new ArrayList<Circle>();
    private List<Double> gasList = new ArrayList<Double>();
    private List<String> dateList = new ArrayList<String>();

    private CoapClient coapClient;
    private CoapObserveRelation coapObserveRelation;
    
    private SensorValue sensorValue;
    
    @FXML
    private Label ylabel1;
    @FXML
    private Label ylabel2;
    @FXML
    private Label ylabel3;
    @FXML
    private Label ylabel4;
    @FXML
    private Label ylabel5;
    @FXML
    private Label ylabel6;
    @FXML
    private Label ylabel7;
    @FXML
    private Label ylabel8;
    @FXML
    private Label ylabel9;
    @FXML
    private Label ylabel10;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //set dummy data
        for (int i = 0; i < 10; i++) {
            dateList.add("");
        }
        //create sensorvalue singleton
        sensorValue = SensorValue.getInstance();
        //run drawChart();
        drawChart();
    }

    private void drawChart() {
        coapClient = new CoapClient();
        coapClient.setURI("coap://192.168.3.44/gassensor");

        Thread thread = new Thread() {
            @Override
            public void run() {
                coapObserveRelation = coapClient.observe(new CoapHandler() {
                    @Override
                    public void onLoad(CoapResponse response) {
                        String text = response.getResponseText();
                        JSONObject jsonObject = new JSONObject(text);
                        Double gas = Double.parseDouble(jsonObject.getString("gas"));

                        sensorValue.setTemperature(gas);
                        
                        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");

                        if (gasList.size() < 10) {
                            gasList.add(gas);
                        } else {
                            gasList.remove(0);
                            gasList.add(gas);
                        }

                        dateList.remove(0);
                        dateList.add(sdf.format(new Date()));

                        circleList.clear();
                        for (int i = 0; i < gasList.size(); i++) {
                            Circle circle = new Circle();
                            circle.setRadius(5);
                            circle.setFill(Color.GREEN);
                            circle.setCenterX(550 - (50 * (gasList.size() - i - 1)));
                            circle.setCenterY(350 - ((300 / 255) * gasList.get(i)));
                            circleList.add(circle);
                        }

                        Path path = new Path();
                        path.setStroke(Color.GREEN);
                        path.setStrokeWidth(2);

                        if (circleList.size() > 1) {
                            MoveTo start = new MoveTo();
                            start.setX(circleList.get(0).getCenterX());
                            start.setY(circleList.get(0).getCenterY());
                            path.getElements().add(start);
                            for (int i = 1; i < circleList.size(); i++) {
                                LineTo line = new LineTo();
                                line.setX(circleList.get(i).getCenterX());
                                line.setY(circleList.get(i).getCenterY());
                                path.getElements().add(line);
                            }
                        }

                        Platform.runLater(() -> {
                            chartPane.getChildren().clear();
                            for (int i = 0; i < circleList.size(); i++) {
                                chartPane.getChildren().add(circleList.get(i));
                            }
                            chartPane.getChildren().add(path);

                            ylabel1.setText(dateList.get(0));
                            ylabel2.setText(dateList.get(1));
                            ylabel3.setText(dateList.get(2));
                            ylabel4.setText(dateList.get(3));
                            ylabel5.setText(dateList.get(4));
                            ylabel6.setText(dateList.get(5));
                            ylabel7.setText(dateList.get(6));
                            ylabel8.setText(dateList.get(7));
                            ylabel9.setText(dateList.get(8));
                            ylabel10.setText(dateList.get(9));

                        });
                    }

                    @Override
                    public void onError() {

                    }
                });
            }
        };
        thread.setDaemon(true);
        thread.start();
    }
}
