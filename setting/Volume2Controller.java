/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mainDisplay.setting;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import static mainDisplay.setting.RootController.mediaPlayer;
import static mainDisplay.setting.RootController.media;
import static mainDisplay.setting.RootController.sta;

/**
 * FXML Controller class
 *
 * @author KIM
 */
public class Volume2Controller implements Initializable {

	@FXML
	private StackPane stack;
	@FXML
	private Button btnAccept;
	@FXML
	private Button btnLkind;
	@FXML
	private Button btnRkind;
	@FXML
	private Slider sliderCall;
	@FXML
	private ImageView img1;
	@FXML
	private Slider sliderMusic;
	@FXML
	private ImageView img2;
	@FXML
	private Button btnLmusic;
	@FXML
	private TextField txtMusic;
	@FXML
	private Button btnRmusic;

	private double slidersoundValue1 = sta.getSliderCall2();
	private double slidersoundValue2 = sta.getSliderMusic2();
	private String MusicAddress = sta.getMusicAddress3();

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		if (MusicAddress == null) {
			MusicAddress = "media/bell1.mp3";
		}
		if (sta.getTxtMusic2() != null) {
			txtMusic.setText(sta.getTxtMusic2());
		}
		if (sta.getSliderCall2() == 0) {
			img1.setImage(new Image(sta.getImag12()));
		}
		if (sta.getSliderMusic2() == 0) {
			img2.setImage(new Image(sta.getImg22()));
		}

//		btnRkind.setOnAction(new EventHandler<ActionEvent>() {
//			@Override
//			public void handle(ActionEvent event) {
//				try {
//					stack.getChildren().remove(0);
//					Parent parent = FXMLLoader.load(getClass().getResource("volume2.fxml"));
//					stack.getChildren().add(parent);		
//				} catch (IOException ex) {
//					System.out.print("하하");
//				}
//			}
//		});
		btnLkind.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				try {
					if (mediaPlayer != null) {
						mediaPlayer.dispose();
					}
					stack.getChildren().remove(0);
					Parent parent = FXMLLoader.load(getClass().getResource("volume1.fxml"));
					stack.getChildren().add(parent);
				} catch (IOException ex) {
					System.out.print("하하");
				}

			}
		});

		sliderCall.setValue(sta.getSliderCall2());
		sliderCall.valueProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {

				if ((sliderCall.getValue()) == 0) {
					Image setImage = new Image(getClass().getResource("images/speakerremove.png").toString());
					sta.setImag12(getClass().getResource("images/speakerremove.png").toString());
					img1.setImage(setImage);
				} else {
					Image setImage = new Image(getClass().getResource("images/speaker.png").toString());
					sta.setImag12(getClass().getResource("images/speaker.png").toString());
					img1.setImage(setImage);
				}
				slidersoundValue1 = newValue.doubleValue();

			}

		});
		sliderMusic.setValue(sta.getSliderMusic2());
		sliderMusic.valueProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {

				if ((sliderMusic.getValue()) == 0) {
					Image setImage = new Image(getClass().getResource("images/speakerremove.png").toString());
					sta.setImg22(getClass().getResource("images/speakerremove.png").toString());
					img2.setImage(setImage);
				} else {
					Image setImage = new Image(getClass().getResource("images/speaker.png").toString());
					sta.setImg22(getClass().getResource("images/speaker.png").toString());
					img2.setImage(setImage);
				}
				slidersoundValue2 = newValue.doubleValue();
				if(mediaPlayer!=null){
					mediaPlayer.dispose();
				}
				media = new Media(getClass().getResource(MusicAddress).toString());
				mediaPlayer = new MediaPlayer(media);
				mediaPlayer.play();
				mediaPlayer.setVolume(slidersoundValue2 / 100.0);
			}

		});
		btnRmusic.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if (mediaPlayer != null) {
					mediaPlayer.dispose();
				}
				String str = txtMusic.getText();
				if (str.equals("벨소리1")) {
					txtMusic.setText("벨소리2");
					MusicAddress = "media/bell2.mp3";
					media = new Media(getClass().getResource("media/bell2.mp3").toString());
					mediaPlayer = new MediaPlayer(media);
					mediaPlayer.play();
				} else if (str.equals("벨소리2")) {
					txtMusic.setText("벨소리3");
					MusicAddress = "media/bell3.mp3";
					media = new Media(getClass().getResource("media/bell3.mp3").toString());
					mediaPlayer = new MediaPlayer(media);
					mediaPlayer.play();

				} else {
					txtMusic.setText("벨소리3");
					media = new Media(getClass().getResource("media/bell3.mp3").toString());
					mediaPlayer = new MediaPlayer(media);
					mediaPlayer.play();
				}
			}
		});
		btnLmusic.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				String str = txtMusic.getText();
				if (str.equals("벨소리3")) {
					txtMusic.setText("벨소리2");
					MusicAddress = "media/bell2.mp3";
					media = new Media(getClass().getResource("media/bell2.mp3").toString());
					mediaPlayer = new MediaPlayer(media);
					mediaPlayer.play();
				} else if (str.equals("벨소리2")) {
					txtMusic.setText("벨소리1");
					MusicAddress = "media/bell1.mp3";
					media = new Media(getClass().getResource("media/bell1.mp3").toString());
					mediaPlayer = new MediaPlayer(media);
					mediaPlayer.play();
				} else {
					txtMusic.setText("벨소리1");
					MusicAddress = "media/bell1.mp3";
					media = new Media(getClass().getResource("media/bell1.mp3").toString());
					mediaPlayer = new MediaPlayer(media);
					mediaPlayer.play();
				}
			}
		});
		btnAccept.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				sta.setTxtMusic(txtMusic.getText());
				sta.setSliderCall(slidersoundValue1);
				sta.setSliderMusic(slidersoundValue2);
				sta.setMusicAddress3(MusicAddress);
			}
		});

	}
}
