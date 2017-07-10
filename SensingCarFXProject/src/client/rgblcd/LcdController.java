package client.rgblcd;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import org.eclipse.californium.core.CoapClient;
import org.eclipse.californium.core.CoapResponse;
import org.eclipse.californium.core.coap.MediaTypeRegistry;
import org.json.JSONObject;

public class LcdController implements Initializable {

    @FXML
    private AnchorPane lcd;
    @FXML
    private TextField line0;
    @FXML
    private TextField line1;
    @FXML
    private Button sendBtn;

    private boolean flag0 = false;
    private boolean flag1 = false;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
//        Font font = Font.loadFont(getClass().getResource("../fonts/5x8_lcd.ttf").toExternalForm(), 20);
//        line0.setFont(font);
//        line1.setFont(font);

        //line0.setStyle("-fx-font-family: Lilly; -fx-font-size: 20;");
 
        line0.setOnMouseClicked(e -> {
            flag0 = true;
            line0.clear();
        });

        line1.setOnMouseClicked(e -> {
            flag1 = true;
            line1.clear();
        });

        sendBtn.setOnAction((event) -> {
            if (flag0 == true && flag1 == false) {
                 line1.setText("");
            }else if(flag0 == false && flag1 == true) {
                line0.setText("");
            }
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("command", "change");
            jsonObject.put("line0", line0.getText());
            jsonObject.put("line1", line1.getText());
            String reqJson = jsonObject.toString();

            CoapClient coapClient = new CoapClient();
            coapClient.setURI("coap://192.168.3.44/lcd");
            CoapResponse coapResponse = coapClient.post(reqJson, MediaTypeRegistry.APPLICATION_JSON);
            String resJson = coapResponse.getResponseText();
            coapClient.shutdown();
        });

    }

}