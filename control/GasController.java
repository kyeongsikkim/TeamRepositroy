/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch17.control;

import static ch17.control.ControlController.gasvalue;
import static ch17.control.ControlController.lightvalue;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;

/**
 * FXML Controller class
 *
 * @author Administrator
 */
public class GasController implements Initializable {

    @FXML
    private BorderPane gas;
    @FXML
    private ToggleButton btnOnOff;
    @FXML
    private ImageView gasImage;

    //private boolean endOfMedia;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if (gasvalue.getGasImage()==null) {
            gasImage.setImage(new Image(getClass().getResource("images/gas_off.png").toString()));
        } else{
            gasImage.setImage(new Image(gasvalue.getGasImage()));
        }
        
        if (gasvalue.getGasOnOff()!= null) {
            btnOnOff.setText(gasvalue.getGasOnOff());
        } 
        
        btnOnOff.setOnAction(e -> handleBtnOnOffAction(e));

    }

    private void handleBtnOnOffAction(ActionEvent e) {
        if (btnOnOff.getText().equals("ON")) {
            btnOnOff.setText("OFF");
            gasImage.setImage(new Image(getClass().getResource("images/gas_on.png").toString()));
            gasvalue.setGasImage(getClass().getResource("images/gas_on.png").toString());
            gasvalue.setGasOnOff(btnOnOff.getText());

            return;
        } else if (btnOnOff.getText().equals("OFF")) {
            btnOnOff.setText("ON");
            gasImage.setImage(new Image(getClass().getResource("images/gas_off.png").toString()));
            gasvalue.setGasImage(getClass().getResource("images/gas_off.png").toString());
            gasvalue.setGasOnOff(btnOnOff.getText());

        }
    }

}
