package securitydisplay;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;

public class Security_mainController implements Initializable {

    @FXML
    private ToggleGroup menu;

    @FXML
    private ToggleButton btnExitSettingTab;

    @FXML
    private ToggleButton btnCctv;

    @FXML
    private StackPane securityContents;
    DataSaved data = new DataSaved();
    public static String txtGetinTime;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // togglebutton 텍스트 폰트 설정
        Font font = Font.loadFont(getClass().getResource("fonts/NotoSansCJKkr-Medium.otf").toExternalForm(), 14);
        btnExitSettingTab.setFont(font);
        
        
        
        // togglebutton 속성 감시(외출방범, 재택방범, CCTV)
        menu.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {

            @Override
            public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {

                if (newValue == btnExitSettingTab) {
                    handle();
                    btnCctv.setDisable(false);
                    btnExitSettingTab.setDisable(true);
                    if (!securityContents.getChildren().isEmpty()) {
                        securityContents.getChildren().remove(0);
                        //if (CctvsettingController.mediaPlayer.isAutoPlay()) {
                        //    CctvsettingController.mediaPlayer.stop();
                        //}
                    }
                    try {
                        Parent parent = FXMLLoader.load(getClass().getResource("exitsetting.fxml"));
                        securityContents.getChildren().add(parent);
                    } catch (IOException ioe) {
                        // 개발 끝나면 지워주기
                        ioe.printStackTrace();
                    }

                } else if (newValue == btnCctv) {
                    btnExitSettingTab.setDisable(false);
                    btnCctv.setDisable(true);
                    if (!securityContents.getChildren().isEmpty()) {
                        securityContents.getChildren().remove(0);
                    }
                    try {
                        Parent parent = FXMLLoader.load(getClass().getResource("cctvsetting.fxml"));
                        securityContents.getChildren().add(parent);
                    } catch (IOException ioe) {
                        // 개발 끝나면 지워주기
                        ioe.printStackTrace();
                    }

                }
            }
        });
    }
    
    public void handle() {
        if (CctvsettingController.mediaPlayerApt != null) {
            CctvsettingController.mediaPlayerApt.dispose();
        }
        if (CctvsettingController.mediaPlayerDoor != null) {
            CctvsettingController.mediaPlayerDoor.dispose();
        }
        if (CctvsettingController.mediaPlayerPlayground != null) {
            CctvsettingController.mediaPlayerPlayground.dispose();
        }
    }
}
