package mainDisplay.receive;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.util.Duration;

public class ReceivePopupController implements Initializable {

    @FXML
    private Pane select;
    @FXML
    private Button btn1;
    @FXML
    private Button btn2;
    @FXML
    private Button btn3;
    @FXML
    private Label lblReceive;
    @FXML
    private ToggleButton accept;
    @FXML
    private Button reject;
    @FXML
    private Label title;
    @FXML
    private Label title2;
    @FXML
    private ToggleGroup callState;

    @FXML
    private Label lblCallTime;

    private Timeline timeline;
    private Duration time = Duration.ZERO;
    private boolean active;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {       
        Font font1 = Font.loadFont(getClass().getResource("fonts/08SeoulNamsanEB.ttf").toExternalForm(), 20);
        Font font2 = Font.loadFont(getClass().getResource("fonts/08SeoulNamsanEB.ttf").toExternalForm(), 25);
        btn1.setFont(font1);
        btn2.setFont(font1);
        btn3.setFont(font1);
        title.setFont(font2);
        title2.setFont(font1);
        lblReceive.setFont(font2);
        accept.setFont(font1);
        reject.setFont(font1);

        DropShadow ds1 = new DropShadow();
        ds1.setColor(Color.web("#00ffff"));
        ds1.setRadius(20);
        ds1.setSpread(0.3);
        btn1.setEffect(ds1);
        btn2.setEffect(ds1);
        btn3.setEffect(ds1);
        title.setEffect(ds1);
        title2.setEffect(ds1);
        lblReceive.setEffect(ds1);
        lblCallTime.setEffect(ds1);

        DropShadow ds2 = new DropShadow();
        ds2.setColor(Color.web("#ccccff"));
        ds2.setRadius(10);
        ds2.setSpread(0.3);
        accept.setEffect(ds2);
        reject.setEffect(ds2);

        btn1.setOnAction(event -> {
            lblReceive.setText("010-1234-5678");
        });

        btn2.setOnAction(event -> {
            lblReceive.setText("108동 2402호");
        });

        btn3.setOnAction(event -> {
            lblReceive.setText("경비실입니다. ");
        });

        callState.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            @Override
            public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {
                if (newValue == accept) {
                    title2.setText("통화중...");
                    reject.setDisable(true);
                } else {
                    title2.setText("통화 종료");
                    accept.setDisable(true);
                    reject.setText("나가기");
                    reject.setDisable(false);
                }
            }
        });

        accept.setOnAction(eve -> {
            accept.setText(isActive() ? "통화" : "통화 종료");
            start();
        });

    }

    public void start() {
        if (active) {
            timeline.stop();
            active = false;
            lblCallTime.setText(makeText(time));
            return;
        }
        active = true;
        if (timeline == null) {
            timeline = new Timeline(
                    new KeyFrame(Duration.millis(100),
                            e2 -> {
                                if (!active) {
                                    return;
                                }
                                final Duration duration = ((KeyFrame) e2.getSource()).getTime();
                                time = time.add(duration);
                                lblCallTime.setText(makeText(time));
                            }
                    )
            );
        }
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    private String makeText(final Duration duration) {
        return String.format("%02d:%02d",
                (long) (duration.toMinutes() % 60.0),
                (long) (duration.toSeconds() % 60.0)
        );
    }

    public void reset() {
        time = Duration.ZERO;
        lblCallTime.setText(makeText(time));
    }

    public boolean isActive() {
        return active;
    }

}
