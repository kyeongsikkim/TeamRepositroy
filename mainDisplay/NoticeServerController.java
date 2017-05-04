package mainDisplay;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class NoticeServerController implements Initializable {
    private ExecutorService executorService;
    private ServerSocket serverSocket;
    private List<Client> connections = new Vector<Client>();
 
    @FXML
    private TextField textTitle;
    @FXML
    private Button btnStartStop;
    @FXML
    private Button btnSend;
    @FXML
    private TextArea textContent;
    @FXML
    private Label labelStatus;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btnStartStop.setOnAction(e -> handleBtnStartStop(e));
        btnSend.setOnAction(e -> handleBtnSend(e));
    }    
    
    private void handleBtnStartStop(ActionEvent e) {
        if (btnStartStop.getText().equals("start")) {
            startServer();
        } else if (btnStartStop.getText().equals("stop")) {
            stopServer();
        }
    }
    
    private void startServer() {
        executorService = Executors.newFixedThreadPool(100);

        try {
            serverSocket = new ServerSocket();
            serverSocket.bind(new InetSocketAddress("localhost", 50001));
        } catch (IOException ex) {
            stopServer();
            return;
        }
        
        Runnable acceptTask = () -> {
            Platform.runLater(() -> {
                btnStartStop.setText("stop");
                labelStatus.setText("연결 중");
            });
            while (true) {
                try {
                    Socket socket = serverSocket.accept();
                    Client client = new Client(socket);
                    connections.add(client);
                } catch (IOException ex) {
                    stopServer();
                    break;
                }
            }
        };
        executorService.submit(acceptTask);
    }
    
    public void stopServer() {
        try {
            Iterator<Client> iterator = connections.iterator();
            while (iterator.hasNext()) {
                Client client = iterator.next();
                client.socket.close();
                iterator.remove();
            }
            executorService.shutdownNow();
            serverSocket.close();
        } catch (IOException ex) {}
        
        Platform.runLater(() -> {
            btnStartStop.setText("start");
            labelStatus.setText("연결되지 않음");
        });
    }

    private void handleBtnSend(ActionEvent e) {
        String title = textTitle.getText();
        String content = textContent.getText();
        
        for (Client client : connections) {
            client.send(title, content);
        }
    }
    
    class Client {
        Socket socket;

        public Client(Socket socket) {
            this.socket = socket;
        }

        void send(String title, String content) {
            try {
                OutputStream os = socket.getOutputStream();
                byte[] byteArr = title.getBytes();
                os.write(byteArr);
                os.flush();
                
                OutputStream os2 = socket.getOutputStream();
                byte[] byteArr2 = content.getBytes();
                os2.write(byteArr2);
                os2.flush();
                
                Platform.runLater(() -> {
                    textTitle.clear();
                    textContent.clear();
                });
            } catch (IOException ex) {
                connections.remove(Client.this);
                try {
                    socket.close();
                } catch (IOException ex1) {
                }
            }
        }

    }
}
