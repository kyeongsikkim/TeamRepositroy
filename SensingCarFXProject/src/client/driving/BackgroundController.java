package client.driving;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

public class BackgroundController implements Initializable {
    
    @FXML
    private GridPane mainBackground;
    
    @FXML
    private StackPane speedGauge;
    
    @FXML
    private StackPane angleGauge;
    
    private MotorGaugeController motor;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
       
        motor = new MotorGaugeController();
        
    }
}