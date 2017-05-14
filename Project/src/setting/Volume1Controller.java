/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package setting;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class Volume1Controller implements Initializable {

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
	@FXML
	private StackPane stack;

    private double slidersoundValue1=sta.getSliderCall1();
	private double slidersoundValue2=sta.getSliderMusic1();
	
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		if (sta.getTxtMusic1() != null) {
			txtMusic.setText(sta.getTxtMusic1());
		}
		if(sta.getSliderCall1()==0)
		{
			img1.setImage(new Image(sta.getImag11()));
		}
		if(sta.getSliderMusic1()==0){
			img2.setImage(new Image(sta.getImg21()));
		}
		
		btnRkind.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				try {
					stack.getChildren().remove(0);
					Parent parent = FXMLLoader.load(getClass().getResource("volume2.fxml"));
					stack.getChildren().add(parent);		
				} catch (IOException ex) {
					System.out.print("하하");
				}
			}
		});
		btnLkind.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
					try {	
				    stack.getChildren().remove(0);
					Parent parent= FXMLLoader.load(getClass().getResource("volume.fxml"));
					stack.getChildren().add(parent);
				} catch (IOException ex) {
						System.out.print("하하");
				}
					
			}
		});
		
		sliderCall.setValue(sta.getSliderCall1());
		sliderCall.valueProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {

				if ((sliderCall.getValue()) == 0) {
					Image setImage = new Image(getClass().getResource("images/speakerremove.png").toString());
					sta.setImag11(getClass().getResource("images/speakerremove.png").toString());
					img1.setImage(setImage);
				} else {
					Image setImage = new Image(getClass().getResource("images/speaker.png").toString());
					sta.setImag11(getClass().getResource("images/speaker.png").toString());
					img1.setImage(setImage);
				}
				    slidersoundValue1=newValue.doubleValue();
					 System.out.println(slidersoundValue1);

			}

		});
		sliderMusic.setValue(sta.getSliderMusic1());
		sliderMusic.valueProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {

				if ((sliderMusic.getValue()) == 0) {
					Image setImage = new Image(getClass().getResource("images/speakerremove.png").toString());
					sta.setImg21(getClass().getResource("images/speakerremove.png").toString());
					img2.setImage(setImage);
				} else {
					Image setImage = new Image(getClass().getResource("images/speaker.png").toString());
					sta.setImg21(getClass().getResource("images/speaker.png").toString());
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
				sta.setTxtMusic1(txtMusic.getText());
				System.out.println(slidersoundValue1);
				System.out.println(slidersoundValue2);
				sta.setSliderCall1(slidersoundValue1);
				sta.setSliderMusic1(slidersoundValue2);
				}
		});
		
	}
}