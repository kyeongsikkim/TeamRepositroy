package client.rgblcd;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Ellipse;
import org.eclipse.californium.core.CoapClient;
import org.eclipse.californium.core.CoapResponse;
import org.eclipse.californium.core.coap.MediaTypeRegistry;
import org.json.JSONObject;

public class RgbController implements Initializable {

    @FXML
    private Ellipse bulbColor;
    @FXML
    private Button b0;
    @FXML
    private Button b1;
    @FXML
    private Button b2;
    @FXML
    private Button b3;
    @FXML
    private Button b4;
    @FXML
    private Button b5;
    @FXML
    private Button b6;
    @FXML
    private Button b7;
    @FXML
    private Button b8;
    @FXML
    private Button b9;
    @FXML
    private Button b10;
    @FXML
    private Button b11;
    @FXML
    private Button b12;
    @FXML
    private Button b13;
    @FXML
    private Button b14;
    @FXML
    private Button b15;
    @FXML
    private Button b16;
    @FXML
    private Button b17;
    @FXML
    private Button b18;
    @FXML
    private Button b19;
    @FXML
    private Button b20;
    @FXML
    private Button b21;
    @FXML
    private Button b22;
    @FXML
    private Button b23;
    @FXML
    private Button b24;
    @FXML
    private Button b25;
    @FXML
    private Button b26;
    @FXML
    private Button b27;
    @FXML
    private Button b28;
    @FXML
    private Button b29;
    @FXML
    private Button b30;
    @FXML
    private Button b31;
    @FXML
    private Button b32;
    @FXML
    private Button b33;
    @FXML
    private Button b34;
    @FXML
    private Button b35;
    @FXML
    private Button b36;
    @FXML
    private Button b37;
    @FXML
    private Button b38;
    @FXML
    private Button b39;
    @FXML
    private Button b40;
    @FXML
    private Button b41;
    @FXML
    private Button b42;
    @FXML
    private Button b43;
    @FXML
    private Button b44;
    @FXML
    private Button b45;
    @FXML
    private Button b46;
    @FXML
    private Button b47;
    @FXML
    private Button b48;
    @FXML
    private Button b49;
    @FXML
    private Button b50;
    @FXML
    private Button b51;
    @FXML
    private Button b52;
    @FXML
    private Button b53;
    @FXML
    private Button b54;
    @FXML
    private Button b55;
    @FXML
    private Button b56;
    @FXML
    private Button b57;
    @FXML
    private Button b58;
    @FXML
    private Button b59;
    @FXML
    private Button b60;
    @FXML
    private Button b61;
    @FXML
    private Button b62;
    @FXML
    private Button b63;
    @FXML
    private Button b64;
    @FXML
    private Button b65;
    @FXML
    private Button b66;
    @FXML
    private Button b67;
    @FXML
    private Button b68;
    @FXML
    private Button b69;
    @FXML
    private AnchorPane rgb;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        b0.setOnAction(e -> {
            bulbColor.setStyle("-fx-fill: rgb(246, 239, 227);");
            handleRGB(246, 239, 227);
        });

        b1.setOnAction(e -> {
            bulbColor.setStyle("-fx-fill: rgb(255, 230, 230);");
            handleRGB(255, 230, 230);
        });

        b2.setOnAction(e -> {
            bulbColor.setStyle("-fx-fill: rgb(217, 179, 255);");
            handleRGB(217, 179, 255);
        });

        b3.setOnAction(e -> {
            bulbColor.setStyle("-fx-fill:rgb(255, 128, 128);");
            handleRGB(255, 128, 128);
        });

        b4.setOnAction(e -> {
            bulbColor.setStyle("-fx-fill: rgb(255, 170, 0);");
            handleRGB(255, 170, 0);
        });

        b5.setOnAction(e -> {
            bulbColor.setStyle("-fx-fill: rgb(255, 255, 204);");
            handleRGB(255, 255, 204);
        });

        b6.setOnAction(e -> {
            bulbColor.setStyle("-fx-fill:rgb(0, 204, 0);");
            handleRGB(0, 204, 0);
        });

        b7.setOnAction(e -> {
            bulbColor.setStyle("-fx-fill: rgb(0, 0, 255);");
            handleRGB(0, 0, 255);
        });

        b8.setOnAction(e -> {
            bulbColor.setStyle("-fx-fill: rgb(153, 179, 255);");
            handleRGB(153, 179, 255);
        });

        b9.setOnAction(e -> {
            bulbColor.setStyle("-fx-fill: rgb(223, 159, 159);");
            handleRGB(223, 159, 159);
        });

        b10.setOnAction(e -> {
            bulbColor.setStyle("-fx-fill: rgb(242, 221, 217);");
            handleRGB(242, 221, 217);
        });

        b11.setOnAction(e -> {
            bulbColor.setStyle("-fx-fill: rgb(255, 204, 204);");
            handleRGB(255, 204, 204);
        });

        b12.setOnAction(e -> {
            bulbColor.setStyle("-fx-fill:rgb(213, 128, 255);");
            handleRGB(213, 128, 255);
        });

        b13.setOnAction(e -> {
            bulbColor.setStyle("-fx-fill:rgb(255, 153, 128);");
            handleRGB(255, 153, 128);
        });

        b14.setOnAction(e -> {
            bulbColor.setStyle("-fx-fill: rgb(255, 204, 102);");
            handleRGB(255, 204, 102);
        });

        b15.setOnAction(e -> {
            bulbColor.setStyle("-fx-fill:rgb(255, 255, 153);");
            handleRGB(255, 255, 153);
        });

        b16.setOnAction(e -> {
            bulbColor.setStyle("-fx-fill: rgb(0, 204, 153);");
            handleRGB(0, 204, 153);
        });

        b17.setOnAction(e -> {
            bulbColor.setStyle("-fx-fill:rgb(128, 0, 255);");
            handleRGB(128, 0, 255);
        });

        b18.setOnAction(e -> {
            bulbColor.setStyle("-fx-fill: rgb(153, 153, 255);");
            handleRGB(153, 153, 255);
        });

        b19.setOnAction(e -> {
            bulbColor.setStyle("-fx-fill: rgb(153, 128, 102);");
            handleRGB(153, 128, 102);
        });

        b20.setOnAction(e -> {
            bulbColor.setStyle("-fx-fill: rgb(231, 195, 182);");
            handleRGB(231, 195, 182);
        });

        b21.setOnAction(e -> {
            bulbColor.setStyle("-fx-fill: rgb(255, 229, 204);");
            handleRGB(255, 229, 204);
        });

        b22.setOnAction(e -> {
            bulbColor.setStyle("-fx-fill: rgb(170, 128, 255);");
            handleRGB(170, 128, 255);
        });

        b23.setOnAction(e -> {
            bulbColor.setStyle("-fx-fill: rgb(255, 77, 136);");
            handleRGB(255, 77, 136);
        });

        b24.setOnAction(e -> {
            bulbColor.setStyle("-fx-fill: rgb(255, 133, 26);");
            handleRGB(255, 133, 26);
        });

        b25.setOnAction(e -> {
            bulbColor.setStyle("-fx-fill: rgb(255,255,0);");
            handleRGB(255, 255, 0);
        });

        b26.setOnAction(e -> {
            bulbColor.setStyle("-fx-fill: rgb(0, 128, 96);");
            handleRGB(0, 128, 96);
        });

        b27.setOnAction(e -> {
            bulbColor.setStyle("-fx-fill: rgb(191, 0, 255);");
            handleRGB(191, 0, 255);
        });

        b28.setOnAction(e -> {
            bulbColor.setStyle("-fx-fill: rgb(204, 153, 255);");
            handleRGB(204, 153, 255);
        });

        b29.setOnAction(e -> {
            bulbColor.setStyle("-fx-fill: rgb(153, 153, 102);");
            handleRGB(153, 153, 102);
        });

        b30.setOnAction(e -> {
            bulbColor.setStyle("-fx-fill: rgb(221, 184, 152);");
            handleRGB(221, 184, 152);
        });

        b31.setOnAction(e -> {
            bulbColor.setStyle("-fx-fill: rgb(255, 215, 179);");
            handleRGB(255, 215, 179);
        });

        b32.setOnAction(e -> {
            bulbColor.setStyle("-fx-fill: rgb(255, 102, 217);");
            handleRGB(255, 102, 217);
        });

        b33.setOnAction(e -> {
            bulbColor.setStyle("-fx-fill: rgb(255, 128, 170);");
            handleRGB(255, 128, 170);
        });

        b34.setOnAction(e -> {
            bulbColor.setStyle("-fx-fill: rgb(255, 184, 77);");
            handleRGB(255, 184, 77);
        });

        b35.setOnAction(e -> {
            bulbColor.setStyle("-fx-fill: rgb(213, 255, 128);");
            handleRGB(213, 255, 128);
        });

        b36.setOnAction(e -> {
            bulbColor.setStyle("-fx-fill: rgb(45, 134, 45);");
            handleRGB(45, 134, 45);
        });

        b37.setOnAction(e -> {
            bulbColor.setStyle("-fx-fill: rgb(99, 31, 122);");
            handleRGB(99, 31, 122);
        });

        b38.setOnAction(e -> {
            bulbColor.setStyle("-fx-fill:rgb(230, 153, 255);");
            handleRGB(230, 153, 255);
        });

        b39.setOnAction(e -> {
            bulbColor.setStyle("-fx-fill: rgb(102, 153, 102);");
            handleRGB(102, 153, 102);
        });

        b40.setOnAction(e -> {
            bulbColor.setStyle("-fx-fill:rgb(209, 189, 164);");
            handleRGB(209, 189, 164);
        });

        b41.setOnAction(e -> {
            bulbColor.setStyle("-fx-fill:rgb(255, 201, 153);");
            handleRGB(255, 201, 153);
        });

        b42.setOnAction(e -> {
            bulbColor.setStyle("-fx-fill: rgb(153, 0, 153);");
            handleRGB(153, 0, 153);
        });

        b43.setOnAction(e -> {
            bulbColor.setStyle("-fx-fill: rgb(255, 128, 128);");
            handleRGB(255, 128, 128);
        });

        b44.setOnAction(e -> {
            bulbColor.setStyle("-fx-fill: rgb(255, 133, 51);");
            handleRGB(255, 133, 51);
        });

        b45.setOnAction(e -> {
            bulbColor.setStyle("-fx-fill: rgb(153, 204, 0);");
            handleRGB(153, 204, 0);
        });

        b46.setOnAction(e -> {
            bulbColor.setStyle("-fx-fill: rgb(45, 134, 112);");
            handleRGB(45, 134, 112);
        });

        b47.setOnAction(e -> {
            bulbColor.setStyle("-fx-fill:rgb(31, 99, 122);");
            handleRGB(31, 99, 122);
        });

        b48.setOnAction(e -> {
            bulbColor.setStyle("-fx-fill: rgb(255, 153, 255);");
            handleRGB(255, 153, 255);
        });

        b49.setOnAction(e -> {
            bulbColor.setStyle("-fx-fill: rgb(128, 102, 153);");
            handleRGB(128, 102, 153);
        });

        b50.setOnAction(e -> {
            bulbColor.setStyle("-fx-fill: rgb(255, 218, 179);");
            handleRGB(255, 218, 179);
        });
        b51.setOnAction(e -> {
            bulbColor.setStyle("-fx-fill:rgb(255, 179, 179);");
            handleRGB(255, 179, 179);
        });

        b52.setOnAction(e -> {
            bulbColor.setStyle("-fx-fill:rgb(204, 102, 153);");
            handleRGB(204, 102, 153);
        });

        b53.setOnAction(e -> {
            bulbColor.setStyle("-fx-fill:rgb(255, 71, 26);");
            handleRGB(255, 71, 26);
        });

        b54.setOnAction(e -> {
            bulbColor.setStyle("-fx-fill: rgb(179, 71, 0);");
            handleRGB(179, 71, 0);
        });

        b55.setOnAction(e -> {
            bulbColor.setStyle("-fx-fill:rgb(153, 153, 51);");
            handleRGB(153, 153, 51);
        });

        b56.setOnAction(e -> {
            bulbColor.setStyle("-fx-fill:rgb(45, 112, 134);");
            handleRGB(45, 112, 134);
        });

        b57.setOnAction(e -> {
            bulbColor.setStyle("-fx-fill: rgb(31, 122, 99);");
            handleRGB(31, 122, 99);
        });

        b58.setOnAction(e -> {
            bulbColor.setStyle("-fx-fill: rgb(255, 153, 204);");
            handleRGB(255, 153, 204);
        });

        b59.setOnAction(e -> {
            bulbColor.setStyle("-fx-fill: rgb(153, 102, 140);");
            handleRGB(153, 102, 140);
        });

        b60.setOnAction(e -> {
            bulbColor.setStyle("-fx-fill:rgb(255, 194, 102);");
            handleRGB(255, 194, 102);
        });
        b61.setOnAction(e -> {
            bulbColor.setStyle("-fx-fill: rgb(255, 179, 255);");
            handleRGB(255, 179, 255);
        });

        b62.setOnAction(e -> {
            bulbColor.setStyle("-fx-fill:rgb(153, 0, 51);");
            handleRGB(153, 0, 51);
        });

        b63.setOnAction(e -> {
            bulbColor.setStyle("-fx-fill: rgb(255, 0, 0);");
            handleRGB(255, 0, 0);
        });

        b64.setOnAction(e -> {
            bulbColor.setStyle("-fx-fill: rgb(204, 102, 0);");
            handleRGB(204, 102, 0);
        });

        b65.setOnAction(e -> {
            bulbColor.setStyle("-fx-fill:rgb(46, 184, 46);");
            handleRGB(46, 184, 46);
        });

        b66.setOnAction(e -> {
            bulbColor.setStyle("-fx-fill: rgb(45, 67, 134);");
            handleRGB(45, 67, 134);
        });

        b67.setOnAction(e -> {
            bulbColor.setStyle("-fx-fill: rgb(0, 102, 51);");
            handleRGB(0, 102, 51);
        });

        b68.setOnAction(e -> {
            bulbColor.setStyle("-fx-fill: rgb(255, 153, 153);");
            handleRGB(255, 153, 153);
        });

        b69.setOnAction(e -> {
            bulbColor.setStyle("-fx-fill:rgb(153, 102, 102);");
            handleRGB(153, 102, 102);
        });

        getInitialValue();

    }

    private void getInitialValue() {
        CoapClient coapClient = new CoapClient();
        JSONObject jsonObject = new JSONObject();
        String json = null;
        CoapResponse coapResponse = null;

        jsonObject.put("command", "status");
        json = jsonObject.toString();
        coapClient.setURI("coap://192.168.3.44/rgbled");
        coapResponse = coapClient.post(json, MediaTypeRegistry.APPLICATION_JSON);
        json = coapResponse.getResponseText();
        jsonObject = new JSONObject(json);
        String red = jsonObject.getString("red");
        String green = jsonObject.getString("green");
        String blue = jsonObject.getString("blue");
        bulbColor.setStyle("-fx-fill: rgb("+red+","+ green+","+ blue+");");
    }

    public void handleRGB(int red, int green, int blue) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("command", "change");
        jsonObject.put("red", String.valueOf(red));
        jsonObject.put("green", String.valueOf(green));
        jsonObject.put("blue", String.valueOf(blue));
        String reqJson = jsonObject.toString();

        CoapClient coapClient = new CoapClient();
        coapClient.setURI("coap://192.168.3.44/rgbled");
        CoapResponse coapResponse = coapClient.post(reqJson, MediaTypeRegistry.APPLICATION_JSON);
        String resJson = coapResponse.getResponseText();
        coapClient.shutdown();
    }

}
