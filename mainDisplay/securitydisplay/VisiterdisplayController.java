package mainDisplay.securitydisplay;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
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

public class VisiterdisplayController implements Initializable {

    @FXML
    private Button btnDelivery;
    @FXML
    private Button btnEtc;
    @FXML
    private Button btnClose;

    private ExecutorService executorService;
    private ServerSocket serverSocket;
    private List<Client> connections = new Vector<Client>();
    private SimpleDateFormat sdf;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        // 클라이언트 시작
        startServer();
        btnDelivery.setOnAction(event -> handleBtnDelivery(event));
        btnEtc.setOnAction(event -> handleBtnEtc(event));
    }

    // '택배'버튼 텍스트 정보전송
    private void handleBtnDelivery(ActionEvent event) {
        String visiter = btnDelivery.getText();
        sdf = new SimpleDateFormat("yyyy-MM-dd(E) HH:mm:ss");
        String date = sdf.format(new Date());
        for (Client client : connections) {
            client.send(visiter, date);
        }
    }
    
    // '기타버튼 텍스트 정보전송
    private void handleBtnEtc(ActionEvent event) {
        String visiter = btnEtc.getText();
        sdf = new SimpleDateFormat("yyyy-MM-dd(E) HH:mm:ss");
        String date = sdf.format(new Date());
        for (Client client : connections) {
            client.send(visiter, date);
        }
    }

    private void startServer() {
        executorService = Executors.newFixedThreadPool(100);
        try {
            serverSocket = new ServerSocket();
            serverSocket.bind(new InetSocketAddress("192.168.3.129", 50002));
            System.out.println("Server is running...");
        } catch (IOException ex) {
            System.out.println("1");
        }

        Runnable acceptTask = () -> {
            while (true) {
                try {
                    Socket socket = serverSocket.accept();
                    Client client = new Client(socket);
                    connections.add(client);
                    System.out.println("Connection success");
                } catch (IOException ioe) {
                }
            }
        };
        executorService.submit(acceptTask);
    }

    class Client {

        Socket socket;

        public Client(Socket socket) {
            this.socket = socket;
        }

        void send(String visiter, String date) {
            try {
                OutputStream os = socket.getOutputStream();
                byte[] byteArr = visiter.getBytes();
                os.write(byteArr);
                os.flush();

                OutputStream os1 = socket.getOutputStream();
                byte[] byteArr1 = date.getBytes();
                os1.write(byteArr1);
                os1.flush();
            } catch (IOException ex) {
                connections.remove(Client.this);
                try {
                    socket.close();
                } catch (IOException ioe) {
                    System.out.println("3");
                }
            }
        }
    }
}
