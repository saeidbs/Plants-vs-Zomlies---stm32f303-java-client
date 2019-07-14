package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Control;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import sample.game.SaveLoadFile;
import sample.utills.Utill;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Optional;
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
        //primaryStage.getIcons().add(0,new Image("\\sample\\icon.jpg"));
        controller = new Controller();

        TextInputDialog textInputDialog = new TextInputDialog("COM4");

        textInputDialog.setTitle("CONFIGURE UART");
        textInputDialog.setHeaderText("Enter Your Uart port");
        textInputDialog.setContentText("PORT:");

        ((Stage)textInputDialog.getDialogPane().getScene().getWindow()).getIcons().add(0,new Image("\\sample\\icon.png"));

        ImageView imageView= new ImageView("\\sample\\alert-graphic.png");
        imageView.setFitWidth(20*Utill.screenUnit);
        imageView.setFitHeight(20*Utill.screenUnit);
        textInputDialog.setGraphic(imageView);
        Optional<String> result = textInputDialog.showAndWait();

        result.ifPresent(name -> {
            controller.setUart(name);
            controller.setGameBoard();

        });

        controller.showMenu();

        BufferedReader bufferedReader = controller.getUart().getReader();


        timer.schedule(new TimerTask() {
             String state="menu";
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
                                case "save":
                                    SaveLoadFile.save(dataString);
                                    break;
                                case "state":
                                    if (!array[0].equals(state)){
                                        switch (array[0]){
                                            case "menu":
                                                controller.getMenu().aboutGameButtonFire(false);
                                                break;
                                            case "about":
                                                controller.getMenu().aboutGameButtonFire(true);
                                                break;
                                            case "game":
                                                controller.getMenu().newGameButtonFire();
                                                break;

                                        }


                                        state=array[0];
                                    }

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
