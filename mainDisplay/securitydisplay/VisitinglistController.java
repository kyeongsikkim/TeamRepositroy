package mainDisplay.securitydisplay;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

public class VisitinglistController implements Initializable {

	@FXML
	private TableView<Visiter> tableView;
	@FXML
	private ImageView imageView;
	@FXML
	private StackPane imageBackground;
	@FXML
	private Button btnClose;

	private SimpleDateFormat sdf;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		// BorderPane 중앙정렬

		// StackPane 숨기기

		// visiterList 초기화 & 객체 넣기
		sdf = new SimpleDateFormat("yyyy-MM-dd(E) HH:mm:ss");
		ObservableList visiterList = FXCollections.observableArrayList(
				new Visiter("택배", sdf.format(new Date()), "member01.png"),
				new Visiter("지인", sdf.format(new Date()), "member01.png"),
				new Visiter("기타", sdf.format(new Date()), "member02.png"));

		TableColumn tcVisiterName = tableView.getColumns().get(0);
		tcVisiterName.setCellValueFactory(new PropertyValueFactory("visiter"));
		tcVisiterName.setStyle("-fx-alignment: CENTER;");

		TableColumn tcDate = tableView.getColumns().get(1);
		tcDate.setCellValueFactory(new PropertyValueFactory("date"));
		tcDate.setStyle("-fx-alignment: CENTER;");

		tableView.setItems(visiterList);
		
		// StackPane에 감시카메라 이미지 넣기
		Visiter visiter = tableView.getSelectionModel().getSelectedItem();
		tableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Visiter>() {

			@Override
			public void changed(ObservableValue<? extends Visiter> observable, Visiter oldValue, Visiter newValue) {
				if (newValue != null) {
					imageView.setImage(new Image(getClass().getResource("image/" + newValue.getImage()).toString()));
				}
			}
		});

		// btnClose 버튼 이벤트 처리
		btnClose.setOnAction(event -> handlebtnClose(event));
	}

	private void handlebtnClose(ActionEvent event) {
		try {
			Parent parent = FXMLLoader.load(getClass().getResource("security_main.fxml"));
			ToggleButton btnReturn = (ToggleButton) parent.lookup("#btnCctv");
			btnReturn.setSelected(true);
			AnchorPane root = (AnchorPane) btnClose.getScene().getRoot();
			root.getChildren().clear();
			root.getChildren().add(parent);
		} catch (IOException ioe) {
			// 개발 끝나면 지워주기!
			ioe.printStackTrace();
		}
	}
}