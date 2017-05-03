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
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

public class PasswordController implements Initializable {

	@FXML
	private Button btnDelete;

	@FXML
	private Button btnConfirm;

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
		// 키패드 버튼 이벤트 처리(1~0번까지)
		btn1.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				txtNumber.appendText("1");
			}
		});
		btn2.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				txtNumber.appendText("2");
			}
		});
		btn3.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				txtNumber.appendText("3");
			}
		});
		btn4.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				txtNumber.appendText("4");
			}
		});
		btn5.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				txtNumber.appendText("5");
			}
		});
		btn6.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				txtNumber.appendText("6");
			}
		});
		btn7.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				txtNumber.appendText("7");
			}
		});
		btn8.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				txtNumber.appendText("8");
			}
		});
		btn9.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				txtNumber.appendText("9");
			}
		});
		btn0.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				txtNumber.appendText("0");
			}
		});
	}

	// 지움버튼 이벤트 처리
	private void handleBtnDelete(ActionEvent event) {
		txtNumber.undo();
	}

	// 확인버튼 이벤트 처리
	private void handleBtnConfirm(ActionEvent event) throws IOException {
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
		Media media = new Media(getClass().getResource("media/외출설정이_되었습니다.mp3").toString());
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
	}
}
