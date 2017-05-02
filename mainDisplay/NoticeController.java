package mainDisplay;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.text.Font;
import javafx.stage.Popup;

public class NoticeController implements Initializable {

    @FXML
    private Button btnDelete;
    @FXML
    private Button btnOpen;
    @FXML
    private TableView<Notice> tableView;
    @FXML
    private Label labelNoticeName;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Font subFont = Font.loadFont(getClass().getResource("fonts/NotoSansCJKkr-DemiLight.otf").toExternalForm(), 12);
        btnDelete.setFont(subFont);
        btnOpen.setFont(subFont);
        labelNoticeName.setFont(subFont);

        tableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Notice>() {
            @Override
            public void changed(ObservableValue<? extends Notice> observable, Notice oldValue, Notice newValue) {
                Popup popup = new Popup();

                btnOpen.setOnAction((e) -> {
                    try {
                        Parent parent = FXMLLoader.load(getClass().getResource("noticePopup.fxml"));
                        Label labelTitle = (Label) parent.lookup("#labelTitle");
                        Label labelDate = (Label) parent.lookup("#labelDate");
                        TextArea areaNotice = (TextArea) parent.lookup("#areaNotice");
                        Button btnClose = (Button) parent.lookup("#btnClose");

                        labelTitle.setText("제목: " + newValue.getTitle());
                        labelDate.setText("일시: " + newValue.getDate());
                        areaNotice.setText(newValue.getContent());

                        popup.getContent().add(parent);
                        popup.setAutoHide(true);
                        popup.show(tableView.getScene().getWindow());
                        
                        btnClose.setOnAction((event) -> {
                            popup.hide();
                        });
                    } catch (IOException ex) {
                    }
                });
                
                btnDelete.setOnAction((e) -> {
                    MainDisplayController.list.remove(newValue);
                });
            }
        });
    }
}   