package securitydisplay;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;

public class PlaygroundController implements Initializable {

	@FXML private Button close;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		close.setOnAction(event -> closeStage(event));
	}

	private void closeStage(ActionEvent event) {
		
	}
}