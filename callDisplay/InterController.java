package callDisplay;

import java.net.URL;
import java.util.ResourceBundle;
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
import javafx.scene.layout.TilePane;

public class InterController implements Initializable {

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

 
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        callToggle.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            @Override
            public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {
                if (newValue == btnCall) {
                    System.out.println("전화걸기");
                    lblState.setText("연결 중...");
                    btnCall.setText("호출 종료");
                    btnBack.setDisable(true);
                    dial.setDisable(true);
                    
                } else {
                    System.out.println("전화끊기");
                    btnBack.setDisable(false);
                    dial.setDisable(false);
                    btnCall.setText("호출");
                    lblState.setText("             ");
                    textArea.clear();
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
            textArea.appendText("동");
        });
        btnSharp.setOnAction(event -> {
            textArea.appendText("호");
        });

        btnBack.setOnAction(event -> {
            textArea.undo();
            textArea.undo();
        });
    }

}
