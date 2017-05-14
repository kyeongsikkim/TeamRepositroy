<<<<<<< HEAD:Project/src/setting/VolumeController.java
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package setting;

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
import static setting.RootController.sta;

/**
 * FXML Controller class
 *
 * @author KIM
 */
public class VolumeController implements Initializable {

	@FXML
	private StackPane stack;
	@FXML
	private ImageView img1;
	@FXML
	private ImageView img2;
	@FXML
	private Button btnAccept;
	@FXML
	private Button btnLkind;
	@FXML
	private Button btnRkind;
	@FXML
	private Slider sliderCall;
	@FXML
	private Slider sliderMusic;
	@FXML
	private Button btnLmusic;
	@FXML
	private Button btnRmusic;
	@FXML
	private TextField txtMusic;
	@FXML
	private TextField txtKind;
	
	private double slidersoundValue1=sta.getSliderCall();
	private double slidersoundValue2=sta.getSliderMusic();
	
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		if (sta.getTxtMusic() != null) {
			txtMusic.setText(sta.getTxtMusic());
		}
		if(sta.getSliderCall()==0)
		{
			img1.setImage(new Image(sta.getImag1()));
		}
		if(sta.getSliderMusic()==0){
			img2.setImage(new Image(sta.getImg2()));
		}
		
		btnRkind.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				try {
					stack.getChildren().remove(0);
					Parent parent = FXMLLoader.load(getClass().getResource("volume1.fxml"));
					stack.getChildren().add(parent);	
					
					
				} catch (IOException ex) {
					ex.printStackTrace();
				}
			}
		});
		
		sliderCall.setValue(sta.getSliderCall());
		sliderCall.valueProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {

				if ((sliderCall.getValue()) == 0) {
					Image setImage = new Image(getClass().getResource("images/speakerremove.png").toString());
					sta.setImag1(getClass().getResource("images/speakerremove.png").toString());
					img1.setImage(setImage);
				} else {
					Image setImage = new Image(getClass().getResource("images/speaker.png").toString());
					sta.setImag1(getClass().getResource("images/speaker.png").toString());
					img1.setImage(setImage);
				}
				    slidersoundValue1=newValue.doubleValue();
					 System.out.println(slidersoundValue1);

			}

		});
		sliderMusic.setValue(sta.getSliderMusic());
		sliderMusic.valueProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {

				if ((sliderMusic.getValue()) == 0) {
					Image setImage = new Image(getClass().getResource("images/speakerremove.png").toString());
					sta.setImg2(getClass().getResource("images/speakerremove.png").toString());
					img2.setImage(setImage);
				} else {
					Image setImage = new Image(getClass().getResource("images/speaker.png").toString());
					sta.setImg2(getClass().getResource("images/speaker.png").toString());
					img2.setImage(setImage);
				}
				 slidersoundValue2=newValue.doubleValue();	
				 System.out.println(slidersoundValue2);
			}

		});
		btnRmusic.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				String str = txtMusic.getText();
				if (str.equals("도어벨소리")) {
					txtMusic.setText("교향곡");
				} else if (str.equals("교향곡")) {
					txtMusic.setText("댄스곡");
				} else {
					txtMusic.setText("댄스곡");
				}
			}
		});
		btnLmusic.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				String str = txtMusic.getText();
				if (str.equals("교향곡")) {
					txtMusic.setText("도어벨소리");
				} else if (str.equals("댄스곡")) {
					txtMusic.setText("교향곡");
				} else {
					txtMusic.setText("도어벨소리");
				}
			}
		});
		btnAccept.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				sta.setTxtMusic(txtMusic.getText());
				System.out.println(slidersoundValue1);
				System.out.println(slidersoundValue2);
				sta.setSliderCall(slidersoundValue1);
				sta.setSliderMusic(slidersoundValue2);
				}
		});
		
	}
}
=======
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package setting;

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
import static setting.RootController.*;




import static setting.RootController.sta;

/**
 * FXML Controller class
 *
 * @author KIM
 */
public class VolumeController implements Initializable {

	@FXML
	private StackPane stack;
	@FXML
	private ImageView img1;
	@FXML
	private ImageView img2;
	@FXML
	private Button btnAccept;
	@FXML
	private Button btnLkind;
	@FXML
	private Button btnRkind;
	@FXML
	private Slider sliderCall;
	@FXML
	private Slider sliderMusic;
	@FXML
	private Button btnLmusic;
	@FXML
	private Button btnRmusic;
	@FXML
	private TextField txtMusic;
	@FXML
	private TextField txtKind;
	
	private double slidersoundValue1=sta.getSliderCall();
	private double slidersoundValue2=sta.getSliderMusic();
	private String MusicAddress=sta.getMusicAddress1();
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		if (sta.getTxtMusic() != null) {
			txtMusic.setText(sta.getTxtMusic());
		}
		if(sta.getSliderCall()==0)
		{
			img1.setImage(new Image(sta.getImag1()));
		}
		if(sta.getSliderMusic()==0){
			img2.setImage(new Image(sta.getImg2()));
		}
		
		btnRkind.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				try {
					stack.getChildren().remove(0);
					Parent parent = FXMLLoader.load(getClass().getResource("volume1.fxml"));
					stack.getChildren().add(parent);	
					
					
				} catch (IOException ex) {
					ex.printStackTrace();
				}
			}
		});
		
		sliderCall.setValue(sta.getSliderCall());
		sliderCall.valueProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				if ((sliderCall.getValue()) == 0) {
					Image setImage = new Image(getClass().getResource("images/speakerremove.png").toString());
					sta.setImag1(getClass().getResource("images/speakerremove.png").toString());
					img1.setImage(setImage);
				} else {
					Image setImage = new Image(getClass().getResource("images/speaker.png").toString());
					sta.setImag1(getClass().getResource("images/speaker.png").toString());
					img1.setImage(setImage);
				}
			
				slidersoundValue1=newValue.doubleValue();

			}

		});
		sliderMusic.setValue(sta.getSliderMusic());
		sliderMusic.valueProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {

				if ((sliderMusic.getValue()) == 0) {
					Image setImage = new Image(getClass().getResource("images/speakerremove.png").toString());
					sta.setImg2(getClass().getResource("images/speakerremove.png").toString());
					img2.setImage(setImage);
				} else {
					Image setImage = new Image(getClass().getResource("images/speaker.png").toString());
					sta.setImg2(getClass().getResource("images/speaker.png").toString());
					img2.setImage(setImage);
				}
				slidersoundValue2=newValue.doubleValue();	
				media=new Media(getClass().getResource(MusicAddress).toString());
				mediaPlayer=new MediaPlayer(media);
				mediaPlayer.play();
				mediaPlayer.setVolume(newValue.doubleValue());
				
				
			}

		});
		btnRmusic.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				mediaPlayer.dispose();
				String str = txtMusic.getText();
				if (str.equals("도어벨소리")) {
					txtMusic.setText("교향곡");
					MusicAddress="audio.wav";
					media=new Media(getClass().getResource("audio.wav").toString());
					mediaPlayer=new MediaPlayer(media);
					mediaPlayer.play();
				} else if (str.equals("교향곡")) {
					txtMusic.setText("댄스곡");
					media=new Media(getClass().getResource("audio123.wav").toString());
					mediaPlayer=new MediaPlayer(media);
					mediaPlayer.play();
				
				} else {
					//txtMusic.setText("댄스곡");
					}
			}
		});
		btnLmusic.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				String str = txtMusic.getText();
				if (str.equals("교향곡")) {
					txtMusic.setText("도어벨소리");
				} else if (str.equals("댄스곡")) {
					txtMusic.setText("교향곡");
				} else {
					txtMusic.setText("도어벨소리");
				}
			}
		});
		btnAccept.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				sta.setTxtMusic(txtMusic.getText());
				sta.setSliderCall(slidersoundValue1);
				sta.setSliderMusic(slidersoundValue2);
				sta.setMusicAddress1(MusicAddress);
				}
		});
		
	}
}
>>>>>>> 204c4a11bd172e49c9b224ae46a7eb0ec75b15ec:setting/VolumeController.java
