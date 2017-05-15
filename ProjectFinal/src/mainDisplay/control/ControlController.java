package mainDisplay.control;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class ControlController implements Initializable {

    @FXML
    private ToggleGroup menuGroup;
    @FXML
    private ToggleButton btnLight;
    @FXML
    private ToggleButton btnGas;
    @FXML
    private ToggleButton btnHeat;
    @FXML
    private StackPane stackPane;

    static Light lightvalue = new Light();
    static Gas gasvalue = new Gas();
    static Heat heatvalue = new Heat();

    @Override
    public void initialize(URL url, ResourceBundle rb) {

//		btnLight.setOnAction(e->handleBtnLight(e));
//		btnGas.setOnAction(e->handleBtnGas(e));
//		btnHeat.setOnAction(e->handleBtnHeat(e));
       

        btnLight.setSelected(true);

        try {
            Parent parent = FXMLLoader.load(getClass().getResource("light.fxml"));
            stackPane.getChildren().add(parent);

        } catch (IOException ex) {
        }

        menuGroup.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            @Override
            public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {
                String fileName = newValue.getUserData().toString() + ".fxml";
                try {
                    stackPane.getChildren().clear();
                    Parent parent = FXMLLoader.load(getClass().getResource(fileName));
                    stackPane.getChildren().add(parent);

                } catch (IOException ex) {
                }

            }
        });

    }

//	private void handleBtnLight(ActionEvent e) {
//		try {
//			Parent parent=FXMLLoader.load(getClass().getResource("Light.fxml"));
//			stackPane.getChildren().add(parent);
//			
//		} catch (IOException ex) {
//			ex.printStackTrace();
//		}
//	}
//
//	private void handleBtnGas(ActionEvent e) {
//		try {
//			Parent parent=FXMLLoader.load(getClass().getResource("Gas.fxml"));
//			stackPane.getChildren().add(parent);
//			
//		} catch (IOException ex) {
//			ex.printStackTrace();
//		}
//	}
//
//	private void handleBtnHeat(ActionEvent e) {
//		try {
//			Parent parent=FXMLLoader.load(getClass().getResource("Heat.fxml"));
//			stackPane.getChildren().add(parent);
//			
//			
//		} catch (IOException ex) {
//			ex.printStackTrace();
//		}
//	}
}
