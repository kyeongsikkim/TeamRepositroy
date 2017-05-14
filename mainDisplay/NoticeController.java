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
    private Label labelNoticeName;
    @FXML
    private Button btnNoticeDelete;
    @FXML
    private Button btnNoticeOpen;
    @FXML
    private TableView<Notice> tableViewNotice;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Font subFont = Font.loadFont(getClass().getResource("fonts/NanumBarunGothic.ttf").toExternalForm(), 12);
        btnNoticeDelete.setFont(subFont);
        btnNoticeOpen.setFont(subFont);
        labelNoticeName.setFont(subFont);

        tableViewNotice.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Notice>() {
            @Override
            public void changed(ObservableValue<? extends Notice> observable, Notice oldValue, Notice newValue) {
                Popup popup = new Popup();

                btnNoticeOpen.setOnAction((e) -> {
                    try {
                        Parent parent = FXMLLoader.load(getClass().getResource("noticePopup.fxml"));
                        Label labelTitle = (Label) parent.lookup("#labelNoticePopupTitle");
                        Label labelDate = (Label) parent.lookup("#labelNoticePopupDate");
                        TextArea areaNotice = (TextArea) parent.lookup("#areaNoticePopup");
                        Button btnClose = (Button) parent.lookup("#btnNoticePopupClose");

                        labelTitle.setText("제목: " + newValue.getTitle());
                        labelDate.setText("일시: " + newValue.getDate());
                        areaNotice.setText(newValue.getContent());
						areaNotice.setEditable(false);

                        popup.getContent().add(parent);
                        popup.setAutoHide(true);
                        popup.show(AppMain.primaryStage);
                        
                        btnClose.setOnAction((event) -> {
                            popup.hide();
                        });
                    } catch (IOException ex) {
                    }
                });
                
                btnNoticeDelete.setOnAction((e) -> {
                    MainDisplayController.list.remove(newValue);
                });
            }
        });
    }
}   