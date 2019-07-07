package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Control;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import static sample.utills.Utill.controller;

public class Main extends Application {
    private static Timer timer=new Timer();
    @Override
    public void start(Stage primaryStage) throws Exception{
//        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
//        primaryStage.setTitle("Hello World");
//        primaryStage.setScene(new Scene(root, 300, 275));
//        primaryStage.show();
       controller=new Controller();
       controller.showMenu();
        BufferedReader bufferedReader=controller.getUart().getReader();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                try {

                    if(bufferedReader.ready()) {
                    String string=bufferedReader.readLine();

                    if (string.startsWith("pc:")){
                        String[]array=string.substring(4).split(",");
                        System.out.println(array);

                        controller.getGameBoard().creatPlant(Integer.valueOf(array[2]),Integer.valueOf(array[0]),Integer.valueOf(array[1]));
                    }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        },0,50);


    }


    public static void main(String[] args) {
        launch(args);
    }
}
