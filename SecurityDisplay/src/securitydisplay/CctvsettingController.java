package securitydisplay;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

public class CctvsettingController implements Initializable {
	
	@FXML
	private MediaView mediaView;
	
	@FXML
	private Button btnApt;
	
	@FXML
	private Button btnDoor;
	
	@FXML
	private Button btnPlayground;
	
	@FXML
	private Button btnList;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		btnApt.setOnAction(event -> handleBtnApt(event));
		btnDoor.setOnAction(event -> handleBtnDoor(event));
		btnPlayground.setOnAction(event -> handleBtnPlayground(event));
		btnList.setOnAction(event -> handleBtnList(event));
	}

	// 방문목록 버튼 이벤트 처리
	private void handleBtnList(ActionEvent event) {
		try {
			Parent visitinglist = FXMLLoader.load(getClass().getResource("visitinglist.fxml"));
			AnchorPane root = (AnchorPane) btnList.getScene().getRoot();
			root.getChildren().add(visitinglist);
		} catch (Exception e) {
			// 개발 끝나면 이거 지워주기!
			e.printStackTrace();
		}
	}

	// 놀이터 버튼 이벤트 처리
	private void handleBtnPlayground(ActionEvent event) {
		Media media = new Media(getClass().getResource("media/video.mp4").toString());
		MediaPlayer mediaPlayer = new MediaPlayer(media);
		mediaView.setMediaPlayer(mediaPlayer);
		mediaPlayer.play();
		
		if(btnDoor.isPressed() || btnApt.isPressed()) {
			mediaPlayer.stop();
		}
	}

	// 현관버튼 이벤트 처리
	private void handleBtnDoor(ActionEvent event) {
		Media media = new Media(getClass().getResource("media/video.mp4").toString());
		MediaPlayer mediaPlayer = new MediaPlayer(media);
		mediaView.setMediaPlayer(mediaPlayer);
		mediaPlayer.play();
		
		if(btnPlayground.isPressed() || btnApt.isPressed()) {
			mediaPlayer.stop();
		}
	}

	// 공동현관 버튼 이벤트 처리
	private void handleBtnApt(ActionEvent event) {
		Media media = new Media(getClass().getResource("media/video.mp4").toString());
		MediaPlayer mediaPlayer = new MediaPlayer(media);
		mediaView.setMediaPlayer(mediaPlayer);
		mediaPlayer.play();
		
		if(btnDoor.isPressed() || btnPlayground.isPressed()) {
			mediaPlayer.stop();
		}
	}
}
