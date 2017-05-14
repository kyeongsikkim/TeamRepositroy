/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mainDisplay.setting;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.text.Font;
import static mainDisplay.setting.RootController.sta;

/**
 * FXML Controller classs
 *
 * @author KIM
 */
public class SceneController implements Initializable {

    @FXML
    private ToggleGroup select;
    @FXML
    private Slider sliderLight;
    @FXML
    private Button btnSaveModeDown;
    @FXML
    private TextField txtSaveMode;
    @FXML
    private Button btnSaveModeUp;
    @FXML
    private RadioButton btnBackground1;
    @FXML
    private RadioButton btnBackground2;
    @FXML
    private RadioButton btnBackground3;
    @FXML
    private Button btnAccept;

    double sliderLightValue = sta.getSliderLight();
    boolean RadionButton = sta.isRadionbutton1();
    boolean RadionButton1 = sta.isRadionbutton2();
    boolean RadionButton2 = sta.isRadionbutton3();
    @FXML
    private Label lbl1;
    @FXML
    private Label lbl2;
    @FXML
    private Label lbl3;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //font
        Font font1 = Font.loadFont(getClass().getResource("fonts/08SeoulNamsanEB.ttf").toExternalForm(), 20);
        Font font2 = Font.loadFont(getClass().getResource("fonts/08SeoulNamsanEB.ttf").toExternalForm(), 12);
        Font font3 = Font.loadFont(getClass().getResource("fonts/08SeoulNamsanEB.ttf").toExternalForm(), 40);
        Font font4 = Font.loadFont(getClass().getResource("fonts/08SeoulNamsanEB.ttf").toExternalForm(), 16);
        
        lbl1.setFont(font1);
        lbl2.setFont(font1);
        lbl3.setFont(font1);
        btnAccept.setFont(font4);
        btnBackground1.setFont(font2);
        btnBackground2.setFont(font2);
        btnBackground3.setFont(font2);
        txtSaveMode.setFont(font3);
        //
        if (sta.getTxtSaveMode() != null) {
            txtSaveMode.setText(sta.getTxtSaveMode());
        }
        btnBackground1.selectedProperty().set(sta.isRadionbutton1());
        btnBackground2.selectedProperty().set(sta.isRadionbutton2());
        btnBackground3.selectedProperty().set(sta.isRadionbutton3());

        select.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            @Override
            public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {
                RadionButton = btnBackground1.selectedProperty().get();
                RadionButton1 = btnBackground2.selectedProperty().get();
                RadionButton2 = btnBackground3.selectedProperty().get();

            }

        });
        btnSaveModeUp.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String[] str = txtSaveMode.getText().split("분");
                int min = Integer.parseInt(str[0]);
                if (min == 10 || min == 7) {
                    if (min == 10) {
                        txtSaveMode.setText(min + 5 + "분");
                    } else {
                        txtSaveMode.setText(min + 3 + "분");
                    }
                } else if (min >= 15) {
                    txtSaveMode.setText(15 + "분");
                } else if (min >= 1) {
                    txtSaveMode.setText(min + 2 + "분");
                }
            }
        });
        btnSaveModeDown.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String[] str = txtSaveMode.getText().split("분");
                int min = Integer.parseInt(str[0]);
                if (min == 15 || min == 10) {
                    if (min == 15) {
                        txtSaveMode.setText(min - 5 + "분");
                    } else {
                        txtSaveMode.setText(min - 3 + "분");
                    }
                } else if (min >= 3) {
                    txtSaveMode.setText(min - 2 + "분");
                } else if (min <= 1) {
                    txtSaveMode.setText(1 + "분");
                }
            }
        });

        btnAccept.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                sta.setTxtSaveMode(txtSaveMode.getText());
                sta.setSliderLight(sliderLightValue);
                sta.setRadionbutton1(RadionButton);
                sta.setRadionbutton2(RadionButton1);
                sta.setRadionbutton3(RadionButton2);

            }
        });
        sliderLight.setValue(sta.getSliderLight());
        sliderLight.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                sliderLightValue = newValue.doubleValue();

            }

        });
    }
}
