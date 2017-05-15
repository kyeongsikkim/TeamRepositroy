package mainDisplay.securitydisplay;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.application.Platform;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
import javafx.scene.text.Font;

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
    private Socket socket;
    private TableView<Visiter> visiterTable;
    public static ObservableList<Visiter> list = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // 폰트 설정
          Font font = new Font(getClass().getResource("fonts/Seoulnamsan_EB.otf").toExternalForm(), 14);
          btnClose.setFont(font);
          
        // 클라이언트 시작
        startClient();

        // BorderPane 중앙정렬
        // StackPane 숨기기
        // visiterList 초기화 & 객체 넣기
        // StackPane에 감시카메라 이미지 넣기
        //Visiter visiter = tableView.getSelectionModel().getSelectedItem();
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
                    System.out.println("Client start");
                    socket = new Socket();
                    socket.connect(new InetSocketAddress("192.168.3.129", 50002));
                    receive();
                } catch (Exception e) {
                    return;
                }
            }
        };
        thread.start();
    }

    private void stopClient() {
        try {
            socket.close();
            System.out.println("Client close");
        } catch (IOException ioe) {
            // 개발 끝나면 지워주기
            ioe.printStackTrace();
        }
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

                    int number = (int) (Math.random() * 2) + 1;

                    list.add(new Visiter(str1, str2, "cctvlist0" + number + ".jpg"));
                    tableView.setItems(list);
                });
            }
        } catch (IOException ioe) {

        }
    }
}
