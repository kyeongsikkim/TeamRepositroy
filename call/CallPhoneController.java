package mainDisplay.call;

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
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;
import javafx.scene.text.Font;
import javafx.util.Duration;

public class CallPhoneController implements Initializable {

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
    private Button btnStar;
    @FXML
    private Button btn0;
    @FXML
    private Button btnSharp;
    @FXML
    private Label lblRequest;
    @FXML
    private Label lblState;
    @FXML
    private TextField textArea;
    @FXML
    private Button btnBack;
    @FXML
    private ToggleButton btnCall;
    @FXML
    private ToggleGroup callToggle;
    @FXML
    private TilePane dial;
    @FXML
    private ImageView imgCall;

    private Timeline timeline;
    private Duration time = Duration.ZERO;
    private boolean active;
    @FXML
    private Label lblCallTime;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Font font1 = Font.loadFont(getClass().getResource("fonts/08SeoulNamsanEB.ttf").toExternalForm(), 20);
        Font font2 = Font.loadFont(getClass().getResource("fonts/08SeoulNamsanEB.ttf").toExternalForm(), 18);
        Font font3 = Font.loadFont(getClass().getResource("fonts/08SeoulNamsanEB.ttf").toExternalForm(), 27);
        lblRequest.setFont(font1);
        lblState.setFont(font2);
        lblCallTime.setFont(font2);
        textArea.setFont(font3);

        callToggle.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            @Override
            public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {

                if (newValue == btnCall) {
                    start();
                    System.out.println("전화걸기");
                    lblState.setText("연결 중...");
                    imgCall.setImage(new Image(getClass().getResource("images/disconnect.png").toString()));
                    btnBack.setDisable(true);
                    dial.setDisable(true);
                } else {
                    reset();
                    active = false;
                    lblCallTime.setText("       ");
                    System.out.println("전화끊기");
                    btnBack.setDisable(false);
                    imgCall.setImage(new Image(getClass().getResource("images/connect.png").toString()));
                    lblState.setText("             ");
                    textArea.clear();
                    dial.setDisable(false);

                }
            }
        });

        btn0.setOnAction(event -> {
            textArea.appendText("0");
        });
        btn1.setOnAction(event -> {
            textArea.appendText("1");
        });
        btn2.setOnAction(event -> {
            textArea.appendText("2");
        });
        btn3.setOnAction(event -> {
            textArea.appendText("3");
        });
        btn4.setOnAction(event -> {
            textArea.appendText("4");
        });
        btn5.setOnAction(event -> {
            textArea.appendText("5");
        });
        btn6.setOnAction(event -> {
            textArea.appendText("6");
        });
        btn7.setOnAction(event -> {
            textArea.appendText("7");
        });
        btn8.setOnAction(event -> {
            textArea.appendText("8");
        });
        btn9.setOnAction(event -> {
            textArea.appendText("9");
        });
        btnStar.setOnAction(event -> {
            textArea.appendText(btnStar.getText());
        });
        btnSharp.setOnAction(event -> {
            textArea.appendText(btnSharp.getText());
        });

        btnBack.setOnAction(event -> {
            textArea.undo();
            textArea.undo();
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
