package mainDisplay;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class ElevatorPopupController implements Initializable {

    private Media media;
    private MediaPlayer mediaPlayer;

    @FXML
    private Button btnElevatorUp;
    @FXML
    private Button btnElevatorDown;
    @FXML
    private Label labelDisplay;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btnElevatorUp.setOnAction(e -> handleBtnElevatorUp(e));
        btnElevatorDown.setOnAction(e -> handleBtnElevatorDown(e));
        
        btnElevatorUp.setFocusTraversable(false);
        btnElevatorDown.setFocusTraversable(false);
    }

    private void handleBtnElevatorUp(ActionEvent e) {
        media = new Media(getClass().getResource("sounds/elevatorUp.mp3").toString());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setRate(1.1);
        
        Thread elevThread = new Thread() {
            @Override
            public void run() {
                while (true) {
                    int floor = Integer.parseInt(labelDisplay.getText());
                    if (floor < 12) {
                        try {
                            Thread.sleep(700);
                        } catch (InterruptedException ex) {
                        }
                        Platform.runLater(() -> {
                            labelDisplay.setText(String.valueOf(floor + 1));
                        });
                    } else if (floor > 12) {
                        try {
                            Thread.sleep(700);
                        } catch (InterruptedException ex) {
                        }
                        Platform.runLater(() -> {
                            labelDisplay.setText(String.valueOf(floor - 1));
                        });
                    } else {
                        Platform.runLater(() -> {
                            mediaPlayer.play();
                        });
                        break;
                    }
                }
            }

        };
        elevThread.setDaemon(true);
        elevThread.start();
        
        
    }

    private void handleBtnElevatorDown(ActionEvent e) {
        media = new Media(getClass().getResource("sounds/elevatorDown.mp3").toString());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setRate(1.1);

        Thread elevThread = new Thread() {
            @Override
            public void run() {
                while (true) {
                    int floor = Integer.parseInt(labelDisplay.getText());
                    if (floor < 12) {
                        try {
                            Thread.sleep(700);
                        } catch (InterruptedException ex) {
                        }
                        Platform.runLater(() -> {
                            labelDisplay.setText(String.valueOf(floor + 1));
                        });
                    } else if (floor > 12) {
                        try {
                            Thread.sleep(700);
                        } catch (InterruptedException ex) {
                        }
                        Platform.runLater(() -> {
                            labelDisplay.setText(String.valueOf(floor - 1));
                        });
                    } else {
                        Platform.runLater(() -> {
                            mediaPlayer.play();
                        });
                        break;
                    }
                }
            }

        };
        elevThread.setDaemon(true);
        elevThread.start();
    }

}
