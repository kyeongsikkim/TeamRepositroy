package securitydisplay;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Popup;
import javafx.stage.Stage;

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

	public static String password;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		btnDelete.setOnAction(event -> handleBtnDelete(event));
		btnConfirm.setOnAction(event -> {
			try {
				handleBtnConfirm(event);
			} catch (IOException ioe) {
				ioe.printStackTrace();
			}
		});
		btnCancel.setOnAction(event -> {
			try {
			handleBtnCancel(event); 
			} catch(IOException ioe) {
				// 개발 끝나면 지워주기
				ioe.printStackTrace();
			}
			});

		// 키패드 버튼 이벤트 처리(1~0번까지)
		btn1.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if (txtNumber.getText().length() > 5) {
					txtNumber.appendText("");
				}
				txtNumber.appendText("1");
			}
		});
		btn2.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if (txtNumber.getText().length() > 5) {
					txtNumber.appendText("");
				}
				txtNumber.appendText("2");
			}
		});
		btn3.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if (txtNumber.getText().length() > 5) {
					txtNumber.appendText("");
				}
				txtNumber.appendText("3");
			}
		});
		btn4.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if (txtNumber.getText().length() > 5) {
					txtNumber.appendText("");
				}
				txtNumber.appendText("4");
			}
		});
		btn5.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if (txtNumber.getText().length() > 5) {
					txtNumber.appendText("");
				}
				txtNumber.appendText("5");
			}
		});
		btn6.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if (txtNumber.getText().length() > 5) {
					txtNumber.appendText("");
				}
				txtNumber.appendText("6");
			}
		});
		btn7.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if (txtNumber.getText().length() > 5) {
					txtNumber.appendText("");
				}
				txtNumber.appendText("7");
			}
		});
		btn8.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if (txtNumber.getText().length() > 5) {
					txtNumber.appendText("");
				}
				txtNumber.appendText("8");
			}
		});
		btn9.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if (txtNumber.getText().length() > 5) {
					txtNumber.appendText("");
				}
				txtNumber.appendText("9");
			}
		});
		btn0.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if (txtNumber.getText().length() > 5) {
					txtNumber.appendText("");
				}
				txtNumber.appendText("0");
			}
		});
	}

	// 취소버튼 이벤트 처리
	private void handleBtnCancel(ActionEvent event) throws IOException {
		Stage primaryStage = (Stage) btnCancel.getScene().getWindow();
		Parent parent = FXMLLoader.load(getClass().getResource("security.fxml"));
		Scene scene = new Scene(parent);
		primaryStage.setScene(scene);
	}

	// 지움버튼 이벤트 처리
	private void handleBtnDelete(ActionEvent event) {
		txtNumber.undo();
	}

	// 확인버튼 이벤트 처리(설정된 비밀번호와 일치 시, 외출방범 설정)
	private void handleBtnConfirm(ActionEvent event) throws IOException {
		password = "1234";
		if (password.equals(txtNumber.getText())) {
			Parent parent = FXMLLoader.load(getClass().getResource("security.fxml"));
			Button btnExitSetting = (Button) parent.lookup("#btnExitSetting");
			CheckBox checkMotion = (CheckBox) parent.lookup("#checkMotion");
			CheckBox checkGas = (CheckBox) parent.lookup("#checkGas");
			CheckBox checkHeater = (CheckBox) parent.lookup("#checkHeater");
			Button btnLowEnter = (Button) parent.lookup("#btnLowEnter");
			Button btnHighEnter = (Button) parent.lookup("#btnHighEnter");
			Button btnLowLeave = (Button) parent.lookup("#btnLowLeave");
			Button btnHighLeave = (Button) parent.lookup("#btnHighLeave");

			btnExitSetting.setText("해제");
			MediaView mediaView = (MediaView) parent.lookup("#soundlock");
			Media media = new Media(getClass().getResource("media/외출방범이_설정되었습니다.mp3").toString());
			MediaPlayer mediaPlayer = new MediaPlayer(media);
			mediaView.setMediaPlayer(mediaPlayer);
			mediaPlayer.play();
			
			checkMotion.setDisable(true);
			checkGas.setDisable(true);
			checkHeater.setDisable(true);
			btnLowEnter.setDisable(true);
			btnHighEnter.setDisable(true);
			btnLowLeave.setDisable(true);
			btnHighLeave.setDisable(true);
			
			Stage primaryStage = (Stage) btnConfirm.getScene().getWindow();
			Scene scene = new Scene(parent);
			primaryStage.setScene(scene);

		} else {
			Stage stage = (Stage) btnConfirm.getScene().getWindow();
			Parent parent = FXMLLoader.load(getClass().getResource("errorpopup.fxml"));
			Popup popup = new Popup();
			popup.getContent().add(parent);
			
			popup.show(stage);
			
			popup.setHideOnEscape(true);
			popup.setAutoHide(true);
			txtNumber.setText("");
		}
	}
}
