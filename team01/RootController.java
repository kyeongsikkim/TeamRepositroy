package ch17.team01;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class RootController implements Initializable {

    @FXML
    private TextField textArea;
    @FXML
    private Button btn0;
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
    private Button btnSharp;

    @FXML
    private ToggleGroup menu;
    @FXML
    private ToggleButton call1;
    @FXML
    private ToggleButton call2;
    @FXML
    private ToggleButton call3;

    @FXML
    private Button btnBack;
    @FXML
    private ToggleButton btnCall;

    @FXML
    private Label lblRequest;
    @FXML
    private Label lblState;

    @FXML
    private StackPane stackPane;
    @FXML
    private Label lblTime;
    @FXML
    private ToggleGroup callToggle;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        menu.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            @Override
            public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {

                textArea.clear();
                if (newValue == call1) {
                    lblRequest.setText("전화번호를 입력해주세요.");
                    btnStar.setText("*");
                    btnSharp.setText("#");
                    btnStar.setOnAction(event -> {
                        textArea.appendText("*");
                    });
                    btnSharp.setOnAction(event -> {
                        textArea.appendText("#");
                    });
                    System.out.println("call1 호출중...");
                } else if (newValue == call2) {
                    lblRequest.setText("동과 호수를 입력해주세요.");
                    btnStar.setText("동");
                    btnSharp.setText("호");
                    btnStar.setOnAction(event -> {
                        textArea.appendText("동");
                    });
                    btnSharp.setOnAction(event -> {
                        textArea.appendText("호");
                    });
                    System.out.println("call2 호출중...");
                } else if (newValue == call3) {

                    System.out.println("call3 호출중...");

                    try {
                        Parent parent = FXMLLoader.load(getClass().getResource("secure.fxml"));
                        Scene scene = new Scene(parent);
                        Stage primaryStage = (Stage) call3.getScene().getWindow();
                        primaryStage.setScene(scene);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }

        });

        callToggle.selectedToggleProperty().addListener(new ChangeListener<Toggle>()  {
            @Override
            public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {
                if (newValue == btnCall) {
                    System.out.println("전화걸기");
                    lblState.setText("연결 중...");
                    btnCall.setText("호출 종료");
                } else {
                    System.out.println("전화끊기");
                    btnCall.setText("호출");
                    //lblState.setText("연결 종료...");
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

        btnBack.setOnAction(event -> {
            textArea.undo();
            textArea.undo();
        });

    }
}
