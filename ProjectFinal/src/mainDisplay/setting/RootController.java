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
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 * FXML Controller class
 *
 * @author KIM
 */
public class RootController implements Initializable {

	@FXML
	private ToggleButton scene;
	@FXML
	private ToggleButton volume;
	@FXML
	private ToggleButton admin;
	@FXML
	private ToggleGroup select;
	@FXML
	private StackPane stack;
	 
	public static Media media;
	public static MediaPlayer mediaPlayer;
	
	static Status sta=Status.getInstacne();

	/**
	 * Initializes the controller class.
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		try {
			Parent parent=FXMLLoader.load(getClass().getResource("scene.fxml"));
			stack.getChildren().add(parent);
			
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		//toogle 그룹의 속성감시 한다.
		select.selectedToggleProperty().addListener(new ChangeListener<Toggle>(){
			@Override
			public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {
					if(mediaPlayer!=null){
						mediaPlayer.dispose();
					}
				   if (newValue == scene) {
                    try {
                        stack.getChildren().remove(0);
                        Parent parent = FXMLLoader.load(getClass().getResource("scene.fxml"));
                        stack.getChildren().add(parent);
                    } catch (IOException ex) {
                    }
                } else if (newValue == volume) {
                    try {
                        stack.getChildren().remove(0);
                        Parent parent = FXMLLoader.load(getClass().getResource("volume.fxml"));
                        stack.getChildren().add(parent);
                    } catch (IOException ex) {
                    }
                } else if (newValue == admin) {
                    try {
                        stack.getChildren().remove(0);
                        Parent parent = FXMLLoader.load(getClass().getResource("admin.fxml"));
                        stack.getChildren().add(parent);
                    } catch (IOException ex) {
                    }
                }
            }

        });
    }

}

