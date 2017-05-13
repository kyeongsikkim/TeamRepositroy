/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch17.control;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 *I
 * @author Administrator
 */
public class AppMain extends Application{
    
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent parent=FXMLLoader.load(AppMain.class.getResource("control.fxml")); //fxml불러옴 
        
        //Parent parent=FXMLLoader.load(getClass().getResource("root.fxml"));
        //parent.getStylesheets().add(getClass().getResource("root.css").toString());
        
        Scene scene=new Scene(parent); //HBox를 받는거랑 같음(Parent 상속받으니까)/Hbox로 바꿔도됨
        primaryStage.setScene(scene);
        primaryStage.setTitle("Control");
        
        primaryStage.show();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
