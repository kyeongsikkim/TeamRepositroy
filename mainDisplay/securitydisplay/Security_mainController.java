package mainDisplay.securitydisplay;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.Iterator;
import java.util.ResourceBundle;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.util.Duration;
import static mainDisplay.AppMain.mainAnchorPane;

public class Security_mainController implements Initializable {

    @FXML
    private ToggleGroup menu;
    @FXML
    private ToggleButton btnExitSettingTab;
    @FXML
    private ToggleButton btnCctv;
    @FXML
    private StackPane securityContents;
    @FXML
    private Button btnExit;
    @FXML
    private AnchorPane securityAnchorPane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //
        // togglebutton 텍스트 폰트 설정
        Font font = Font.loadFont(getClass().getResource("fonts/NanumBarunGothic.ttf").toExternalForm(), 14);
        btnExitSettingTab.setFont(font);
        // togglebutton 속성 감시(외출방범, 재택방범, CCTV)
        menu.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            @Override
            public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {
                if (newValue == btnExitSettingTab) {
                    if (!securityContents.getChildren().isEmpty()) {
                        securityContents.getChildren().clear();
                    }
                    try {
                        Parent parent = FXMLLoader.load(getClass().getResource("exitsetting.fxml"));
                        securityContents.getChildren().add(parent);
                    } catch (IOException ioe) {
                    }
                } else if (newValue == btnCctv) {
                    if (!securityContents.getChildren().isEmpty()) {
                        securityContents.getChildren().clear();
                    }
                    try {
                        Parent parent = FXMLLoader.load(getClass().getResource("cctvsetting.fxml"));
                        securityContents.getChildren().add(parent);
                    } catch (IOException ioe) {
                    }
                }
            }
        });
        //btnExit
        btnExit.setOnAction(e -> handleBtnExit(e));
    }

    private void handleBtnExit(ActionEvent e) {
        Button btnHome = (Button) mainAnchorPane.lookup("#btnHome");
        btnHome.toBack();
        
        KeyValue keyValue = new KeyValue(securityAnchorPane.opacityProperty(), 0);
        KeyFrame keyFrame = new KeyFrame(Duration.seconds(0.5), keyValue);
        Timeline timeline = new Timeline();
        timeline.getKeyFrames().add(keyFrame);
        timeline.play();

        timeline.statusProperty().addListener(new ChangeListener<Animation.Status>() {
            @Override
            public void changed(ObservableValue<? extends Animation.Status> observable, Animation.Status oldValue, Animation.Status newValue) {
                StackPane mainStackPane = (StackPane) mainAnchorPane.lookup("#stackPane");

                mainStackPane.getChildren().remove(1);
            }
        });

//		timeline.onFinishedProperty().addListener(new ChangeListener<EventHandler<ActionEvent>>() {
//			@Override
//			public void changed(ObservableValue<? extends EventHandler<ActionEvent>> observable, EventHandler<ActionEvent> oldValue, EventHandler<ActionEvent> newValue) {
//				StackPane mainStackPane = (StackPane) mainAnchorPane.lookup("#stackPane");
//				mainStackPane.getChildren().remove(1);
//			}
//		});
    }
}
