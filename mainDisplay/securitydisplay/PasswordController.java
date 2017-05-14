package mainDisplay.securitydisplay;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;

public class PasswordController implements Initializable {

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnConfirm;

    @FXML
    private Button btnCancel;

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
    private Button btn0;

    @FXML
    private TextField txtNumber;

    public static String password = "1234";

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // 폰트 설정 코드
        Font font = new Font(getClass().getResource("fonts/08SeoulNamsanEB.ttf").toExternalForm(), 14);
        btnDelete.setFont(font);
        btnConfirm.setFont(font);
        btnCancel.setFont(font);
        btn1.setFont(font);
        btn2.setFont(font);
        btn3.setFont(font);
        btn4.setFont(font);
        btn5.setFont(font);
        btn6.setFont(font);
        btn7.setFont(font);
        btn8.setFont(font);
        btn9.setFont(font);
        btn0.setFont(font);
        
        btnDelete.setOnAction(event -> handleBtnDelete(event));

        // 키패드 버튼 이벤트 처리(0~9번까지)
        btn1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (txtNumber.getText().length() > 5) {
                    txtNumber.appendText("");
                } else {
                    txtNumber.appendText("1");
                }
            }
        });
        btn2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (txtNumber.getText().length() > 5) {
                    txtNumber.appendText("");
                } else {
                    txtNumber.appendText("2");
                }
            }
        });
        btn3.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (txtNumber.getText().length() > 5) {
                    txtNumber.appendText("");
                } else {
                    txtNumber.appendText("3");
                }
            }
        });
        btn4.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (txtNumber.getText().length() > 5) {
                    txtNumber.appendText("");
                } else {
                    txtNumber.appendText("4");
                }
            }
        });
        btn5.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (txtNumber.getText().length() > 5) {
                    txtNumber.appendText("");
                } else {
                    txtNumber.appendText("5");
                }
            }
        });
        btn6.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (txtNumber.getText().length() > 5) {
                    txtNumber.appendText("");
                } else {
                    txtNumber.appendText("6");
                }
            }
        });
        btn7.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (txtNumber.getText().length() > 5) {
                    txtNumber.appendText("");
                } else {
                    txtNumber.appendText("7");
                }
            }
        });
        btn8.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (txtNumber.getText().length() > 5) {
                    txtNumber.appendText("");
                } else {
                    txtNumber.appendText("8");
                }
            }
        });
        btn9.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (txtNumber.getText().length() > 5) {
                    txtNumber.appendText("");
                } else {
                    txtNumber.appendText("9");
                }
            }
        });
        btn0.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (txtNumber.getText().length() > 5) {
                    txtNumber.appendText("");
                } else {
                    txtNumber.appendText("0");
                }
            }
        });
    }

    // 지움버튼 이벤트 처리
    private void handleBtnDelete(ActionEvent event) {
        txtNumber.undo();
    }
}
