package securitydisplay;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Popup;
import javafx.stage.Stage;

public class SecurityController implements Initializable {

	//public static Scene main;

	@FXML
	private CheckBox checkMotion;
	@FXML
	private CheckBox checkGas;
	@FXML
	private CheckBox checkHeater;
	@FXML
	private ToggleButton btnExitSetting;
	@FXML
	private ToggleButton btnHouseSetting;
	@FXML
	private Button btnAptDoor;
	@FXML
	private Button btnDoor;
	@FXML
	private Button btnPlayground;
	@FXML
	private ToggleGroup setting;
	@FXML
	private Button btnHighEnter;
	@FXML
	private Button btnLowEnter;
	@FXML
	private Button btnHighLeave;
	@FXML
	private Button btnLowLeave;
	@FXML
	private Button btnVisit;
	@FXML
	private TextField txtGetInTime;
	@FXML
	private TextField txtGetoutTime;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		// 입실시간 조절버튼 이벤트처리
		btnHighEnter.setOnAction(event -> handleBtnHighEnter());
		btnLowEnter.setOnAction(event -> handleBtnLowEnter());

		// 퇴실시간 조절버튼 이벤트처리
		btnHighLeave.setOnAction(event -> handleBtnHighLeave());
		btnLowLeave.setOnAction(event -> handleBtnLowLeave());

		// 초기 입실 및 퇴실시간 설정
		txtGetInTime.setText(20 + "초");
		txtGetoutTime.setText(60 + "초");

		// 방문기록 리스트 버튼 이벤트 처리
		btnVisit.setOnAction(event -> handleBtnVisit(event));
		
		// 외출방범 및 재택방범 설정, 해제 속성바인딩
		// Bindings.bindBidirectional(btnExitSetting.selectedProperty(), btnHouseSetting.selectedProperty());
		btnExitSetting.selectedProperty().bindBidirectional(btnHouseSetting.selectedProperty());
		btnExitSetting.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				try {
					handleBtnExitSetting(event);
				} catch (IOException e) {
					// 개발 끝나면 지우기
					e.printStackTrace();
				}
			}
			
		});
		btnHouseSetting.setOnAction(event -> handleBtnHouseSetting(event));

		// 현관문 cctv 화면 버튼 이벤트 처리
		btnDoor.setOnAction(event -> {
			if (btnDoor.isFocused()) {
				try {
					handleDoorPopup(event);
				} catch (Exception e) {
					// 개발 끝나면 다른거 넣어주자!
					e.printStackTrace();
				}
			}
		});

		// 공동현관 cctv 화면 버튼 이벤트 처리
		btnAptDoor.setOnAction(event -> {
			if (btnAptDoor.isFocused()) {
				try {
					handleAptDoorPopup(event);
				} catch (Exception e) {
					// 개발 끝나면 다른거 넣어주자!
					e.printStackTrace();
				}
			}
		});

		// 놀이터 cctv 화면 버튼 이벤트 처리
		btnPlayground.setOnAction(event -> {
			if (btnPlayground.isFocused()) {
				try {
					handlePlaygroundPopup(event);
				} catch (Exception e) {
					// 개발 끝나면 다른거 넣어주자!
					e.printStackTrace();
				}
			}
		});
	}

	private void handleBtnVisit(ActionEvent event) {
		try {
			Parent visitinglist = FXMLLoader.load(getClass().getResource("visitinglist.fxml"));
			BorderPane root = (BorderPane) btnVisit.getScene().getRoot();
			root.getChildren().add(visitinglist);
		} catch(Exception e) {
			// 개발 끝나면 이거 지워주기!
			e.printStackTrace();
		}
	}

	private void handleBtnExitSetting(ActionEvent event) throws IOException {
		if (btnExitSetting.isFocused()) {
			
			Parent parent= (Parent) FXMLLoader.load(getClass().getResource("security.fxml"));
			MediaView mediaView = (MediaView) parent.lookup("#soundlock");
			Media media = new Media(getClass().getResource("media/audio.wav").toString());
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
			btnAptDoor.setDisable(true);
			btnDoor.setDisable(true);
			btnPlayground.setDisable(true);
			
			
		} else {
			System.out.println("해제되었습니다");
		}
	}

	private void handleBtnHouseSetting(ActionEvent event) {
		System.out.println("재택방범이 설정되었습니다");
	}

	// 퇴실시간 낮추기 버튼 처리코드
	private void handleBtnLowLeave() {

		String str = txtGetoutTime.getText();
		String[] str1 = str.split("초");
		int second = Integer.parseInt(str1[0]);
		txtGetoutTime.setText(second - 5 + "초");
		if (second <= 0) {
			txtGetoutTime.setText("0초");
		}
	}

	// 퇴실시간 높이기 버튼 처리코드
	private void handleBtnHighLeave() {
		String str = txtGetoutTime.getText();
		String[] str1 = str.split("초");
		int second = Integer.parseInt(str1[0]);
		txtGetoutTime.setText(second + 5 + "초");
		if (second >= 255) {
			txtGetoutTime.setText("255초");
		}
	}

	// 입실시간 낮추기 버튼 처리코드
	private void handleBtnLowEnter() {
		String str = txtGetInTime.getText();
		String[] str1 = str.split("초");
		int second = Integer.parseInt(str1[0]);
		txtGetInTime.setText(second - 5 + "초");
		if (second <= 0) {
			txtGetInTime.setText("0초");
		}
	}

	// 입실시간 높이기 버튼 처리코드
	private void handleBtnHighEnter() {
		String str = txtGetInTime.getText();
		String[] str1 = str.split("초");
		int second = Integer.parseInt(str1[0]);
		txtGetInTime.setText(second + 5 + "초");
		if (second >= 255) {
			txtGetInTime.setText("255초");
		}
	}

	// 공동현관 cctv popup창 설정
	private void handleAptDoorPopup(ActionEvent event) throws Exception {
		Stage primaryStage = (Stage) btnAptDoor.getScene().getWindow();
		Popup popup = new Popup();
		Parent parent = (Parent) FXMLLoader.load(getClass().getResource("aptdoor.fxml"));
		MediaView mediaView = (MediaView) parent.lookup("#cctv");
		Media media = new Media(getClass().getResource("media/video.mp4").toString());
		MediaPlayer mediaPlayer = new MediaPlayer(media);
		mediaView.setMediaPlayer(mediaPlayer);
		mediaPlayer.play();

		popup.getContent().add(parent);
		popup.setAutoHide(true);
		popup.show(primaryStage);
	}

	// 현관문 cctv popup창 설정
	private void handleDoorPopup(ActionEvent event) throws Exception {
		Stage primaryStage = (Stage) btnDoor.getScene().getWindow();
		Popup popup = new Popup();
		Parent parent = (Parent) FXMLLoader.load(getClass().getResource("door.fxml"));
		MediaView mediaView = (MediaView) parent.lookup("#cctv");
		Media media = new Media(getClass().getResource("media/video.mp4").toString());
		MediaPlayer mediaPlayer = new MediaPlayer(media);
		mediaView.setMediaPlayer(mediaPlayer);
		mediaPlayer.play();

		popup.getContent().add(parent);
		popup.setAutoHide(true);
		popup.show(primaryStage);
	}

	// 놀이터 cctv popup창 설정
	private void handlePlaygroundPopup(ActionEvent event) throws Exception {
		Stage primaryStage = (Stage) btnPlayground.getScene().getWindow();
		Popup popup = new Popup();
		Parent parent = (Parent) FXMLLoader.load(getClass().getResource("playground.fxml"));
		MediaView mediaView = (MediaView) parent.lookup("#cctv");
		Media media = new Media(getClass().getResource("media/video.mp4").toString());
		MediaPlayer mediaPlayer = new MediaPlayer(media);
		mediaView.setMediaPlayer(mediaPlayer);
		mediaPlayer.play();
		
		popup.getContent().add(parent);
		
		Button button = (Button) parent.lookup("#btnClose");
		button.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				popup.hide();
				mediaPlayer.stop();
			}
		});
		
		popup.show(primaryStage);
	}
}