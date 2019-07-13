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
    private static Timer timer = new Timer();

    @Override
    public void start(Stage primaryStage) throws Exception {
//        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
//        primaryStage.setTitle("Hello World");
//        primaryStage.setScene(new Scene(root, 300, 275));
//        primaryStage.show();
        controller = new Controller();
        controller.showMenu();

        BufferedReader bufferedReader = controller.getUart().getReader();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                try {

                    if (bufferedReader.ready()) {
                        String string = bufferedReader.readLine();

                        System.out.println("micro:"+string+"********");
                        controller.getGameBoard().setInformationText(string);

                        String dataString=string.substring(string.indexOf(":")+1);
                        String[] array = {};

                        if(!string.isEmpty())
                            array=dataString.split(",");
///*
                            switch (string.substring(0,string.indexOf(":"))){
                                case "pc":
                                    controller.getGameBoard().creatPlant(getInt(array[0]), getInt(array[1]), getInt(array[2]));
                                    break;

                                case "pr":
                                    controller.getGameBoard().removePlant(getInt(array[0]),getInt(array[1]));
                                    break;
                                case "zc":
                                    controller.getGameBoard().creatZombie(getInt(array[0]),getInt(array[1]),getInt(array[2]));
                                    break;
                                case "zm":
                                    controller.getGameBoard().moveZombie(getInt(array[0]),getInt(array[1]),getInt(array[2]),getInt(array[3]));
                                    break;
                                case "zr":
                                    controller.getGameBoard().removeZombie(getInt(array[0]),getInt(array[1]));
                                    break;
                                case "bc":
                                    controller.getGameBoard().creatBonus(getInt(array[0]),getInt(array[1]),getInt(array[2]));
                                    break;
                                case "br":
                                    controller.getGameBoard().removeBonus(getInt(array[0]),getInt(array[1]));
                                    break;
                                case "l":
                                    controller.getGameBoard().setTemperatureLabel(array[0]);
                                    break;
                                case "s":
                                    controller.getGameBoard().setScoreLabel(array[0]);
                                    break;
                                case "r":
                                    controller.getGameBoard().setRoundLabel(array[0]);
                                    break;
                                case "t":
                                    controller.getGameBoard().setTimeLabel(array[0]);
                                    break;
                                case "ls":
                                    controller.getGameBoard().setLifeLabel(array[0]);
                                    break;
                                case "pe":
                                    controller.getGameBoard().setPlantEnable(getInt(array[0]),getInt(array[1]));
                                    break;
                            }


//*/
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }, 0, 10);


    }

    private int getInt(String string){
        return Integer.valueOf(string);
    }


    public static void main(String[] args) {
        launch(args);
    }
}
