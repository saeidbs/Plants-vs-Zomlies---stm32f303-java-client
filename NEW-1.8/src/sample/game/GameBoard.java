package sample.game;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.MotionBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Pair;
import sample.Uart;
import sample.game.bonus.Bonus;
import sample.game.bonus.LevelOneBonus;
import sample.game.bonus.LevelThreeBonus;
import sample.game.bonus.LevelTwoBonus;
import sample.game.plant.LevelOnePlant;
import sample.game.plant.LevelThreePlant;
import sample.game.plant.LevelTwoPlant;
import sample.game.plant.Plant;
import sample.game.zombie.*;
import sample.utills.Utill;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


public class GameBoard extends Stage {
    private Label timeLabel;
    private Label roundLabel;
    private Label scoreLabel;
    private Label lifeLabel;
    private Label informationLable;
    private Text informationText;
    private Label temperatureLabel;
    private Button saveButton;
    private static Pane pane;
    private int plantSelectedID = 1;
    private Map<Pair<Integer, Integer>, Zombie> zombieMap = new HashMap<>();
    private Map<Pair<Integer, Integer>, Plant> plantMap = new HashMap<>();
    private Bonus bonus;
    private MotionBlur motionBlur = new MotionBlur();
    private LevelOnePlant tempLevelOnePlant;
    private LevelTwoPlant tempLevelTwoPlant;
    private LevelThreePlant tempLevelThreePlant;
    private Uart uart;
    private BufferedWriter bufferedWriter;


    private GameBoard() {

        pane = new Pane();
        VBox leftVBox = new VBox();

        timeLabel = new Label("TIME LABEL");
        roundLabel = new Label("ROUND LABEL");
        informationText = new Text("informationLabelinformationLabelinformationLabelinformationLabelinformationLabelinformationLabel\n");
        //  informationText.prefHeight(Utill.screenHeight);
//        for (int i=0;i<7;i++){
//            informationText.setText(informationText.getText()+informationText.getText());
//        }
        //  informationText.setWrapText(true);

//        informationText.setMaxWidth(Utill.pageSize/6);
//        informationText.setMaxHeight(Utill.pageSize/6);

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setFitToHeight(true);
        scrollPane.setFitToWidth(true);
        scrollPane.setContent(informationText);

        informationLable = new Label("uart receive:");

        leftVBox.getChildren().addAll(timeLabel, roundLabel, informationLable, scrollPane);
        leftVBox.setAlignment(Pos.CENTER);
        leftVBox.setSpacing(2 * Utill.screenUnit);
        leftVBox.setLayoutX(0);
        leftVBox.setLayoutY(0);
        leftVBox.setPrefSize(Utill.pageSize / 6.5 - 30, Utill.screenHeight);
        leftVBox.setAlignment(Pos.TOP_CENTER);
        leftVBox.setPadding(new Insets(5 * Utill.screenUnit, 0, 0, 0));


        Pane root = new Pane();

        pane.setLayoutX(leftVBox.getPrefWidth());
        pane.setLayoutY(0);
        pane.setPrefSize(Utill.zombieFitWidth * 20, Utill.screenHeight);

        pane.setBackground(new Background(new BackgroundImage(
                new Image("\\sample\\Wiki-background.jpg"),
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(Utill.pageSize / 1.25 - 30, Utill.screenHeight, true, true, true, true))));

        pane.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                //  pane.getChildren().add(new LevelOnePlant((int)mouseEvent.getX(),(int)mouseEvent.getY()));
                boardGameClick(mouseEvent);
            }
        });


        VBox rightVBox = new VBox();
        scoreLabel = new Label("SCORE LABEL");
        lifeLabel = new Label("LIFE LABEL");
        temperatureLabel = new Label("TEMPERATURE LABEL");
        saveButton = new Button("SAVE BUTTON");

        DropShadow dropShadow = new DropShadow();
        saveButton.addEventFilter(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                saveButton.setEffect(dropShadow);
            }
        });

        saveButton.addEventFilter(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                saveButton.setEffect(null);
            }
        });

        saveButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {

                new Result(true,5000).show();
                creatBonus(1, 3, 3);
                creatBonus(1, 3, 15);
//                creatPlant(1,3,8);
//                creatZombie(1,1,7);
//                removeZombie(0,19);
//                removePlant(3,19);
//               moveZombie(1,7,2,7);


                TextInputDialog textInputDialog = new TextInputDialog(Utill.defaultName);
                ((Stage)textInputDialog.getDialogPane().getScene().getWindow()).getIcons().add(0,new Image("\\sample\\icon.png"));

                textInputDialog.setTitle(Utill.saveGameTitle);
                textInputDialog.setHeaderText(Utill.headerDialogInputNewGame);
                textInputDialog.setContentText(Utill.contexDialogInputNewGame);
                ImageView imageView= new ImageView("\\sample\\alert-graphic.png");
                imageView.setFitWidth(20*Utill.screenUnit);
                imageView.setFitHeight(20*Utill.screenUnit);
                textInputDialog.setGraphic(imageView);


                Optional<String> result = textInputDialog.showAndWait();

                result.ifPresent(name -> {
                    System.out.println(name);

                });


            }
        });


        VBox subRightVbox = new VBox();


        tempLevelOnePlant = new LevelOnePlant(0, 0);
        tempLevelOnePlant.setFitHeight(Utill.plantFitHeight * 0.6);
        tempLevelOnePlant.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                System.out.println("plant 1 " + mouseEvent.getX() + " , " + mouseEvent.getY());
                plantVboxClick(1);
            }
        });

        tempLevelTwoPlant = new LevelTwoPlant(0, 0);
        tempLevelTwoPlant.setFitHeight(Utill.plantFitHeight * 0.6);
        tempLevelTwoPlant.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                System.out.println("plant 2 " + mouseEvent.getX() + " , " + mouseEvent.getY());
                plantVboxClick(2);
            }
        });


        tempLevelThreePlant = new LevelThreePlant(0, 0);
        tempLevelThreePlant.setFitHeight(Utill.plantFitHeight * 0.6);
        tempLevelThreePlant.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                System.out.println("plant 3 " + mouseEvent.getX() + " , " + mouseEvent.getY());
                plantVboxClick(3);
            }
        });


        subRightVbox.getChildren().addAll(tempLevelOnePlant, tempLevelTwoPlant, tempLevelThreePlant);
        subRightVbox.setAlignment(Pos.CENTER);
        subRightVbox.setSpacing(2 * Utill.screenUnit);
        subRightVbox.setPrefSize(129, Utill.screenHeight / 3);


        rightVBox.getChildren().addAll(scoreLabel, lifeLabel, temperatureLabel, subRightVbox, saveButton);
        //rightVBox.setAlignment(Pos.CENTER);
        rightVBox.setSpacing(2 * Utill.screenUnit);
        rightVBox.setLayoutX(pane.getLayoutX() + pane.getPrefWidth());
        rightVBox.setLayoutY(0);
        rightVBox.setPrefSize(129, Utill.screenHeight);
        rightVBox.setAlignment(Pos.TOP_CENTER);
        rightVBox.setPadding(new Insets(5 * Utill.screenUnit, 0, 0, 0));

        root.getChildren().addAll(leftVBox, pane, rightVBox);


        Scene scene = new Scene(root, Utill.pageSize, Utill.screenHeight);
        this.setScene(scene);
        this.setResizable(false);
        this.getIcons().add(0,new Image("\\sample\\icon.png"));
    }


    private void plantVboxClick(int kind) {
        switch (kind) {
            case 1:
            case 2:
            case 3:
                plantSelectedID = kind;
                break;
        }

    }

    private void boardGameClick(MouseEvent mouseEvent) {
        try {

            if (Bonus.clicked) {
                Bonus.clicked = false;
                //  bufferedWriter.write("");


            } else {
               // System.out.println("kiram to masoud ");
//                bufferedWriter.write("pc:" + plantSelectedID+","+Plant.ytoRow((int) mouseEvent.getY()) + "," + Plant.xtoColumn((int) mouseEvent.getX())+"\n");
//                bufferedWriter.flush();
                uart.addCharacter("pc:" + plantSelectedID+","+Plant.ytoRow((int) mouseEvent.getY()) + "," + Plant.xtoColumn((int) mouseEvent.getX())+"\n");
            //  uart.send("pc:" + plantSelectedID+","+Plant.ytoRow((int) mouseEvent.getY()) + "," + Plant.xtoColumn((int) mouseEvent.getX())+"\n");
                System.out.println("pc:" + plantSelectedID+","+Plant.ytoRow((int) mouseEvent.getY()) + "," + Plant.xtoColumn((int) mouseEvent.getX())+"\n");
             //   System.out.println("kiram dobare to masoud");
            }
        } catch (Exception e) {

        }

    }

//    private void boardGameClick(MouseEvent mouseEvent) {
//        switch (plantSelectedID) {
//            case 1:
//                if (LevelOnePlant.enable) {
//                    pane.getChildren().add(new LevelOnePlant(Plant.ytoRow((int) mouseEvent.getY()), Plant.xtoColumn((int) mouseEvent.getX())));
//                    LevelOnePlant.enable = false;
//                }
//                break;
//            case 2:
//                if (LevelTwoPlant.enable) {
//                    pane.getChildren().add(new LevelTwoPlant(Plant.ytoRow((int) mouseEvent.getY()), Plant.xtoColumn((int) mouseEvent.getX())));
//                    LevelTwoPlant.enable = false;
//                }
//                break;
//            case 3:
//                if (LevelThreePlant.enable) {
//                    pane.getChildren().add(new LevelThreePlant(Plant.ytoRow((int) mouseEvent.getY()), Plant.xtoColumn((int) mouseEvent.getX())));
//                    LevelThreePlant.enable = false;
//                }
//                break;
//        }
//        setPlantEnable(plantSelectedID,0);
//
//
//    }


    public void creatZombie(int kind, int row, int column) {
        Zombie zombie = null;
        switch (kind) {
            case 1:
                zombie = new LevelOneZombie(row, column);
                break;
            case 2:
                zombie = new LevelTwoZombie(row, column);
                break;
            case 3:
                zombie = new LevelThreeZombie(row, column);
                break;
            case 4:
                zombie = new LevelFourZombie(row, column);
                break;

        }
        if (zombie != null) {
            zombieMap.put(new Pair<>(row, column), zombie);

            Zombie finalZombie = zombie;
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    pane.getChildren().add(finalZombie);
                }
            });
        }


    }

    public void creatPlant(int kind, int row, int column) {
        Plant plant = null;
        switch (kind) {
            case 1:
                plant = new LevelOnePlant(row, column);
                break;
            case 2:
                plant = new LevelTwoPlant(row, column);
                break;
            case 3:
                plant = new LevelThreePlant(row, column);
                break;
        }
        if (plant != null) {
            final Plant tempPlamt = plant;
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    plantMap.put(new Pair<>(row, column), tempPlamt);
                    pane.getChildren().add(tempPlamt);
                }
            });

        }
    }

    public void moveZombie(int oldRow, int oldColumn, int newRow, int newColumn) {

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                Zombie zombie;

                zombie = zombieMap.remove(new Pair<>(oldRow, oldColumn));
                zombieMap.put(new Pair<>(newRow, newColumn), zombie);
                zombie.move(newRow, newColumn);
            }
        });

    }


    public void removeZombie(int row, int column) {

        Platform.runLater(new Runnable() {
            @Override
            public void run() {

                Zombie zombie;
                zombie = zombieMap.remove(new Pair<>(row, column));
                pane.getChildren().remove(zombie);

            }
        });
    }

    public void removePlant(int row, int column) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                Plant plant;

                plant = plantMap.remove(new Pair<>(row, column));

                pane.getChildren().remove(plant);
            }
        });

    }

    public void creatBonus(int kind, int row, int column) {
        Bonus bonus = null;
        switch (kind) {
            case 1:
                bonus = new LevelOneBonus(row, column);
                break;
            case 2:
                bonus = new LevelTwoBonus(row, column);
                break;
            case 3:
                bonus = new LevelThreeBonus(row, column);
                break;
        }
        if (bonus != null) {
            this.bonus = bonus;
            final Bonus tempBonus = bonus;
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    pane.getChildren().add(tempBonus);
                }
            });

        }
    }

    public void removeBonus(int row, int column) {


        Platform.runLater(new Runnable() {
            @Override
            public void run() {

                pane.getChildren().remove(bonus);
                bonus = null;

            }
        });

    }

    public void setTimeLabel(String string) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {

                timeLabel.setText("TIME: " + string);
            }
        });

    }

    public void setRoundLabel(String string) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {

                roundLabel.setText("ROUND: " + string);
            }
        });
    }

    public void setTemperatureLabel(String string) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {

                temperatureLabel.setText("LIGHT: " + string);
            }
        });


    }

    public void setLifeLabel(String string) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                lifeLabel.setText("REMAINING LIFE: " + string);

            }
        });
    }

    public void setScoreLabel(String string) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {

                scoreLabel.setText("SCORE: " + string);
            }
        });
    }

    public void setInformationText(String string) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                informationText.setText(string + "\n" + informationText.toString());
            }
        });
    }

    public void setPlantEnable(int kind, int enable) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {

                if (kind == 1) {
                    if (enable == 1) {
                        tempLevelOnePlant.setEffect(null);
                        LevelOnePlant.enable = true;
                    } else if (enable == 0) {
                        tempLevelOnePlant.setEffect(motionBlur);
                        LevelOnePlant.enable = false;
                    }
                } else if (kind == 2) {
                    if (enable == 1) {
                        tempLevelTwoPlant.setEffect(null);
                        LevelTwoPlant.enable = true;
                    } else if (enable == 0) {
                        tempLevelTwoPlant.setEffect(motionBlur);
                        LevelTwoPlant.enable = false;
                    }
                } else if (kind == 3) {
                    if (enable == 1) {
                        tempLevelThreePlant.setEffect(null);
                        LevelThreePlant.enable = true;
                    } else if (enable == 0) {
                        tempLevelThreePlant.setEffect(motionBlur);
                        LevelThreePlant.enable = false;
                    }
                }
            }
        });
    }


    private void saveGame(String string) {

    }

    public GameBoard(Uart uart) {
        this();
        this.uart = uart;
        // TODO: 7/13/2019 hazf she
       // bufferedWriter = uart.getWriter();
    }

}
