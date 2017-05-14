package securitydisplay;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;
import javafx.application.Platform;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
    @FXML
    private Button btnReset;

    private SimpleDateFormat sdf;
    private Socket socket;
    private TableView<Visiter> visiterTable;
    public static ObservableList<Visiter> list;
    private ObservableList visiterList;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        // 클라이언트 시작
        btnReset.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                startClient();
            }
        });

        // BorderPane 중앙정렬
        // StackPane 숨기기
        // visiterList 초기화 & 객체 넣기
        // StackPane에 감시카메라 이미지 넣기
//        list = FXCollections.observableArrayList();
        Visiter visiter = tableView.getSelectionModel().getSelectedItem();

        tableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Visiter>() {

            @Override
            public void changed(ObservableValue<? extends Visiter> observable, Visiter oldValue, Visiter newValue) {
                if (newValue != null) {
                    imageView.setImage(new Image(getClass().getResource("image/" + newValue.getImage()).toString()));
                }
            }
        });
    }

    private void startClient() {
        Thread thread = new Thread() {
            @Override
            public void run() {
                try {
                    socket = new Socket();
                    socket.connect(new InetSocketAddress("localhost", 50002));
                    
                    System.out.println("연결됨");
                    receive();
                } catch (Exception e) {
                    return;
                }
            }
        };
        thread.start();
    }

    private void receive() throws Exception {
        try {
            while (true) {
                InputStream is1 = socket.getInputStream();
                byte[] btyArr = new byte[100];
                int btyLength = btyArr.length;
                is1.read(btyArr);
                if (btyLength == -1) {
                    throw new Exception();
                }
                String str1 = new String(btyArr, 0, btyLength);

                InputStream is2 = socket.getInputStream();
                byte[] btyArr1 = new byte[100];
                int btyLength1 = btyArr1.length;
                is2.read(btyArr1);
                if (btyLength1 == -1) {
                    throw new Exception();
                }
                String str2 = new String(btyArr1, 0, btyLength1);

                Platform.runLater(() -> {
                    TableColumn tcVisiterName = tableView.getColumns().get(0);
                    tcVisiterName.setCellValueFactory(new PropertyValueFactory<Visiter, String>("visiter"));
                    tcVisiterName.setStyle("-fx-alignment: CENTER;");

                    TableColumn tcDate = tableView.getColumns().get(1);
                    tcDate.setCellValueFactory(new PropertyValueFactory<Visiter, String>("date"));
                    tcDate.setStyle("-fx-alignment: CENTER;");

                    System.out.println(str1);
                    System.out.println(str2);
                    list.add(new Visiter(str1, str2, "member01.png"));
                    tableView.setItems(list);
                });
            }
        } catch (IOException ioe) {

        }
    }
}
