package sample;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Control;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Pair;
import sample.game.Result;
import sample.game.SaveLoadFile;
import sample.game.plant.LevelOnePlant;
import sample.game.plant.LevelThreePlant;
import sample.game.plant.LevelTwoPlant;
import sample.game.plant.Plant;
import sample.game.zombie.*;
import sample.utills.Utill;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.*;

import static sample.utills.Utill.controller;

public class Main extends Application {
    private static Timer timer = new Timer();
    private Result result=new Result();

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

        ((Stage) textInputDialog.getDialogPane().getScene().getWindow()).getIcons().add(0, new Image("\\sample\\icon.png"));

        ImageView imageView = new ImageView("\\sample\\alert-graphic.png");
        imageView.setFitWidth(20 * Utill.screenUnit);
        imageView.setFitHeight(20 * Utill.screenUnit);
        textInputDialog.setGraphic(imageView);
        Optional<String> result = textInputDialog.showAndWait();

        result.ifPresent(name -> {
            controller.setUart(name);
            controller.setGameBoard();

        });

        controller.showMenu();

        BufferedReader bufferedReader = controller.getUart().getReader();


        timer.schedule(new TimerTask() {
            String state = "menu";

            @Override
            public void run() {
                try {

                    if (bufferedReader.ready()) {
                        String string = bufferedReader.readLine();

                        System.out.println("micro:" + string + "********");
                        controller.getGameBoard().setInformationText(string);

                        String dataString = string.substring(string.indexOf(":") + 1);
                        String[] array = {};

                        if (!string.isEmpty())
                            array = dataString.split(",");
///*
                        switch (string.substring(0, string.indexOf(":"))) {
                            case "pc":
                                controller.getGameBoard().creatPlant(getInt(array[0]), getInt(array[1]), getInt(array[2]));
                                break;

                            case "pr":
                                controller.getGameBoard().removePlant(getInt(array[0]), getInt(array[1]));
                                break;
                            case "zc":
                                controller.getGameBoard().creatZombie(getInt(array[0]), getInt(array[1]), getInt(array[2]));
                                break;
                            case "zm":
                                controller.getGameBoard().moveZombie(getInt(array[0]), getInt(array[1]), getInt(array[2]), getInt(array[3]));
                                break;
                            case "zr":
                                controller.getGameBoard().removeZombie(getInt(array[0]), getInt(array[1]));
                                break;
                            case "bc":
                                controller.getGameBoard().creatBonus(getInt(array[0]), getInt(array[1]), getInt(array[2]));
                                break;
                            case "br":
                                controller.getGameBoard().removeBonus(getInt(array[0]), getInt(array[1]));
                                break;
                            case "l":
                                controller.getGameBoard().setTemperatureLabel(array[0]);
                                break;
                            case "s":
                                controller.getGameBoard().setScoreLabel(array[0]);
                               Main.this.result.setScoreLabel(getInt(array[0]));
                                break;
                            case "r":
                                controller.getGameBoard().setRoundLabel(array[0]);
                                Zombie.setZombieSpeed(getInt(array[0]));
                                break;
                            case "t":
                                controller.getGameBoard().setTimeLabel(Long.valueOf(array[0]));
                                controller.getGameBoard().setPlantEnable(Long.valueOf(array[0]));
                                break;
                            case "ls":
                                controller.getGameBoard().setLifeLabel(array[0]);
                                break;
                            case "pe":
                                controller.getGameBoard().setplantCanBeUsed(getInt(array[0]), Long.valueOf(array[1]));
                                break;
                            case "start_time_game":
                                controller.getGameBoard().setStartTimeGame(Long.valueOf(array[0]));
                                break;
                            case "save":
                                SaveLoadFile.save(dataString);
                                break;
                            case "sync":
                                syncMap(array);
                                break;
                            case "state":
                                if (!array[0].equals(state)) {
                                    switch (array[0]) {
                                        case "menu":
                                            controller.getMenu().aboutGameButtonFire(false);
                                            break;
                                        case "about":
                                            controller.getMenu().aboutGameButtonFire(true);
                                            break;
                                        case "game":
                                            controller.getMenu().newGameButtonFire();
                                            break;
                                        case "load_game":
                                            controller.getMenu().loadGameButtonFire();
                                            break;
                                        case "game_over":
                                            Platform.runLater(new Runnable() {
                                                @Override
                                                public void run() {
                                                 //   System.out.println("saeid bahmani");
                                                    controller.getGameBoard().close();
                                                    Main.this.result.setResultLabel(false);
                                                    Main.this.result.show();
                                                }
                                            });
                                            break;
                                        case "win":
                                        Platform.runLater(new Runnable() {
                                            @Override
                                            public void run() {
                                                controller.getGameBoard().close();
                                                Main.this.result.setResultLabel(true);
                                                Main.this.result.show();
                                            }
                                        });
                                            break;
                                    }


                                    state = array[0];
                                }

                        }


//*/
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();

                }
            }
        }, 0, 10);


    }
    private void syncMap(String[] array){
        controller.getGameBoard().clearMap();



//         Map<Pair<Integer, Integer>, Zombie> tempZombieMap = new HashMap<>();
//         Map<Pair<Integer, Integer>, Plant> tempPlantMap = new HashMap<>();
        for (int i=1;i<=3*getInt(array[0]);i+=3){
//            int row=getInt(array[i+1]);
//            int column=getInt(array[i+2]);
//            Plant plant = null;
//            switch (getInt(array[i])) {
//                case 1:
//                    plant = new LevelOnePlant(row, column);
//                    break;
//                case 2:
//                    plant = new LevelTwoPlant(row, column);
//                    break;
//                case 3:
//                    plant = new LevelThreePlant(row, column);
//                    break;
//            }
//            tempPlantMap.put(new Pair<>(row,column),plant);
          controller.getGameBoard().creatPlant(getInt(array[i]),getInt(array[i+1]),getInt(array[i+2]));
        }

//        controller.getGameBoard().getPlantMap().forEach((pair, plant) -> {
//            if (!tempPlantMap.containsKey(pair)) {
//                controller.getGameBoard().removePlant(pair.getKey(), pair.getValue());
//               // System.out.println("for aval");
//            }
//        });
//        tempPlantMap.forEach((pair,plant)->{
//            if (!controller.getGameBoard().getPlantMap().containsKey(pair))
//                controller.getGameBoard().creatPlant(plant.getKind(),pair.getKey(),pair.getValue());
//        });


        for (int i=3*getInt(array[0])+2;i<array.length;i+=3){
            controller.getGameBoard().creatZombie(getInt(array[i]),getInt(array[i+1]),getInt(array[i+2]));
        }
//        for (int i=3*getInt(array[0])+2;i<array.length;i+=3) {
//            int row = getInt(array[i + 1]);
//            int column = getInt(array[i + 2]);
//            Zombie zombie = null;
//            switch (getInt(array[i])) {
//                case 1:
//                    zombie = new LevelOneZombie(row, column);
//                    break;
//                case 2:
//                    zombie = new LevelTwoZombie(row, column);
//                    break;
//                case 3:
//                    zombie = new LevelThreeZombie(row, column);
//                    break;
//                case 4:
//                    zombie = new LevelFourZombie(row, column);
//                    break;
//
//            }
//            tempZombieMap.put(new Pair<>(row, column), zombie);
//        }
//
//
//
//        controller.getGameBoard().getZombieMap().forEach((pair, zombie) -> {
//            if (tempZombieMap.get(pair)==null) {
//                System.out.println("for dovom");
//                controller.getGameBoard().removeZombie(pair.getKey(), pair.getValue());
//            }
//        });
//        tempZombieMap.forEach((pair,zombie)->{
//            if (controller.getGameBoard().getZombieMap().get(pair)==null) {
//                System.out.println("for aval");
//                controller.getGameBoard().creatZombie(zombie.getKind(), pair.getKey(), pair.getValue());
//            }
//        });

    }

    private int getInt(String string) {
        return Integer.valueOf(string);
    }


    public static void main(String[] args) {
        launch(args);
    }
}
