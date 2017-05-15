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
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;
import static mainDisplay.setting.RootController.sta;

/**
 * FXML Controller class
 *
 * @author KIM
 */
public class AdminController implements Initializable {

    @FXML
    private Button btnOk;
    @FXML
    private Button btnCancle;
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
    private TextField txtPass;
    @FXML
    private Button btnFind;
    @FXML
    private CheckBox checkMotion;
    @FXML
    private Button btnDelete;
    String password = "";

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Font font1 = Font.loadFont(getClass().getResource("fonts/08SeoulNamsanEB.ttf").toExternalForm(), 16);
        btnOk.setFont(font1);
        btnCancle.setFont(font1);
        btnFind.setFont(font1);
        txtPass.setFont(font1);
        
        if (sta.getTxtPass() != null) {
            if (sta.isCheckMotion() == false) {
                txtPass.setText("");
                for (int i = 0; i < sta.getTxtPass().length(); i++) {
                    txtPass.appendText("*");
                }
            } else {
                checkMotion.selectedProperty().set(true);
                txtPass.setText(sta.getTxtPass());
            }
            password = sta.getTxtPass();
        } else {
            txtPass.setText("최대6자리");
        }
        btn1.setOnAction(e -> {
            if (txtPass.getText().length() >= 6) {
            } else {
                if (txtPass.getText().equals("최대6자리")) {
                    txtPass.setText("");
                }
                if (checkMotion.selectedProperty().get() == true) {
                    txtPass.appendText("1");
                    password += "1";
                } else {
                    txtPass.appendText("*");
                    password += "1";
                }
            }
        });
        btn2.setOnAction(e -> {
            if (txtPass.getText().length() >= 6) {
            } else {
                if (txtPass.getText().equals("최대6자리")) {
                    txtPass.setText("");
                }
                if (checkMotion.selectedProperty().get() == true) {
                    txtPass.appendText("2");
                    password += "2";
                } else {
                    txtPass.appendText("*");
                    password += "2";
                }
            }
        });
        btn3.setOnAction(e -> {
            if (txtPass.getText().length() >= 6) {
            } else {
                if (txtPass.getText().equals("최대6자리")) {
                    txtPass.setText("");
                }
                if (checkMotion.selectedProperty().get() == true) {
                    txtPass.appendText("3");
                    password += "3";
                } else {
                    txtPass.appendText("*");
                    password += "3";
                }
            }
        });
        btn4.setOnAction(e -> {
            if (txtPass.getText().length() >= 6) {
            } else {
                if (txtPass.getText().equals("최대6자리")) {
                    txtPass.setText("");
                }
                if (checkMotion.selectedProperty().get() == true) {
                    txtPass.appendText("4");
                    password += "4";
                } else {
                    txtPass.appendText("*");
                    password += "4";
                }
            }
        });
        btn5.setOnAction(e -> {
            if (txtPass.getText().length() >= 6) {
            } else {
                if (txtPass.getText().equals("최대6자리")) {
                    txtPass.setText("");
                }
                if (checkMotion.selectedProperty().get() == true) {
                    txtPass.appendText("5");
                    password += "5";
                } else {
                    txtPass.appendText("*");
                    password += "5";
                }
            }
        });
        btn6.setOnAction(e -> {
            if (txtPass.getText().length() >= 6) {
            } else {
                if (txtPass.getText().equals("최대6자리")) {
                    txtPass.setText("");
                }
                if (checkMotion.selectedProperty().get() == true) {
                    txtPass.appendText("6");
                    password += "6";
                } else {
                    txtPass.appendText("*");
                    password += "6";
                }
            }
        });
        btn7.setOnAction(e -> {
            if (txtPass.getText().length() >= 6) {
            } else {
                if (txtPass.getText().equals("최대6자리")) {
                    txtPass.setText("");
                }
                if (checkMotion.selectedProperty().get() == true) {
                    txtPass.appendText("7");
                    password += "7";

                } else {
                    txtPass.appendText("*");
                    password += "7";

                }
            }
        });
        btn8.setOnAction(e -> {
            if (txtPass.getText().length() >= 6) {
            } else {
                if (txtPass.getText().equals("최대6자리")) {
                    txtPass.setText("");
                }
                if (checkMotion.selectedProperty().get() == true) {
                    txtPass.appendText("8");
                    password += "8";

                } else {
                    txtPass.appendText("*");
                    password += "8";

                }
            }
        });
        btn9.setOnAction(e -> {
            if (txtPass.getText().length() >= 6) {
            } else {
                if (txtPass.getText().equals("최대6자리")) {
                    txtPass.setText("");
                }
                if (checkMotion.selectedProperty().get() == true) {
                    txtPass.appendText("9");
                    password += "9";

                } else {
                    txtPass.appendText("*");
                    password += "9";

                }
            }
        });
        btn0.setOnAction(e -> {
            if (txtPass.getText().length() >= 6) {
            } else {
                if (txtPass.getText().equals("최대6자리")) {
                    txtPass.setText("");
                }
                if (checkMotion.selectedProperty().get() == true) {
                    txtPass.appendText("0");
                    password += "0";

                } else {
                    txtPass.appendText("*");
                    password += "0";

                }
            }
        });
        btnCancle.setOnAction(e -> {
            txtPass.setText("최대6자리");
            password = "";
            sta.setTxtPass("cancle");
        });
        btnOk.setOnAction(e -> {
            sta.setTxtPass(password);
            sta.setCheckMotion(checkMotion.selectedProperty().get());
        });
        btnDelete.setOnAction(e -> {
           
            int length = password.length();
            if (length > 0) {
                password = password.substring(0, length - 1);
                txtPass.setText(txtPass.getText().substring(0, length - 1));
            }

        });
        btnFind.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (checkMotion.selectedProperty().get() == true) {
                    checkMotion.selectedProperty().set(false);
                } else {
                    checkMotion.selectedProperty().set(true);
                }

            }
        });
        checkMotion.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (newValue == true) {
                    txtPass.setText(password);
                } else {
                    txtPass.setText("");
                    for (int i = 0; i < password.length(); i++) {
                        txtPass.appendText("*");
                    }
                }
            }

        });

    }
}
