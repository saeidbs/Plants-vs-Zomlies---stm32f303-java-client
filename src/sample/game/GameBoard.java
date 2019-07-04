package sample.game;

import com.sun.javafx.util.Utils;
import com.sun.prism.paint.Color;
import com.sun.webkit.network.Util;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import sample.game.zombie.LevelOneZombie;
import sample.game.zombie.TempZombie;
import sample.game.zombie.Zombie;
import sample.utills.Utill;



public class GameBoard extends Stage {
    private Label timeLabel;
    private Label roundLabel;
    private Label scoreLabel;
    private Label lifeLabel;
    private Label informationLabel;
    private Button saveButton;
    private static GridPane gridPane;
    private static int zombieFitWidth=(int)(Utill.pageSize/1.25/20);
    private static int zombieFitHeight=(int)(Utill.screenHeight/4);
    public GameBoard(){
        VBox leftVBox=new VBox();

        timeLabel=new Label("TIME LABEL");
        roundLabel=new Label("ROUND LABEL");
        informationLabel=new Label("informationLabelinformationLabelinformationLabelinformationLabelinformationLabelinformationLabel");
        informationLabel.setWrapText(true);
//        informationLabel.setMaxWidth(Utill.pageSize/6);
//        informationLabel.setMaxHeight(Utill.pageSize/6);
        leftVBox.getChildren().addAll(timeLabel,roundLabel,informationLabel);
        leftVBox.setAlignment(Pos.CENTER);
        leftVBox.setSpacing(2* Utill.screenUnit);

        VBox rightVBox=new VBox();
        scoreLabel=new Label("SCORE LABEL");
        lifeLabel=new Label("LIFE LABEL");
        // TODO: 7/4/2019 add plant
        saveButton=new Button("SAVE BUTTON");
        saveButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                LevelOneZombie levelOneZombie=new LevelOneZombie(zombieFitWidth/2,zombieFitHeight/2);
                replaceZombie((Zombie)gridPane.getChildren().get(0),levelOneZombie);
                levelOneZombie.move();
//                TempZombie tempZombie= (TempZombie) gridPane.getChildren().get(20);
//                tempZombie.move();

//                Zombie test= (Zombie) gridPane.getChildren().get(41);
//              LevelOneZombie saeid=new LevelOneZombie(test.getxPosition(),test.getyPosition());
//              replaceZombie(test,saeid);
            }
        });
        rightVBox.getChildren().addAll(scoreLabel,lifeLabel,saveButton);
        rightVBox.setAlignment(Pos.CENTER);
        rightVBox.setSpacing(2* Utill.screenUnit);

         gridPane=new GridPane();
        initial();

      Pane root=new Pane();
      leftVBox.setLayoutX(0);
      leftVBox.setLayoutY(0);
      leftVBox.setPrefSize(Utill.pageSize/6.5-30,Utill.screenHeight);
      leftVBox.setAlignment(Pos.TOP_CENTER);
      gridPane.setLayoutX(Utill.pageSize/6.5-30);
      gridPane.setLayoutY(0);
      gridPane.setPrefSize(Utill.pageSize/1.25-30, Utill.screenHeight);

      rightVBox.setLayoutX(1370);
      rightVBox.setLayoutY(0);
      rightVBox.setPrefSize(130,Utill.screenHeight);
      rightVBox.setAlignment(Pos.CENTER_RIGHT);
      root.getChildren().addAll(leftVBox,gridPane,rightVBox);


      gridPane.setBackground(new Background(new BackgroundImage(
              new Image("\\sample\\Wiki-background.jpg"),
              BackgroundRepeat.NO_REPEAT,
              BackgroundRepeat.NO_REPEAT,
              BackgroundPosition.CENTER,
              new BackgroundSize(Utill.pageSize/1.25-30, Utill.screenHeight, true, true, true, true))));
//
//        BorderPane root = new BorderPane();
//
//        root.setLeft(leftVBox);
//        root.setCenter(gridPane);
//        root.setRight(rightVBox);

//        SplitPane root=new SplitPane();
//        root.setDividerPositions(0.2f,0.8f);
//        root.getItems().addAll(leftVBox,pane,rightVBox);

        Scene scene=new Scene(root,Utill.pageSize,Utill.screenHeight);
        this.setScene(scene);
        this.setResizable(false);
    }


    public static GridPane getGridPane() {
        return gridPane;
    }
    public static void replaceZombie(Zombie mainZombie,Zombie newZombie){
        mainZombie.setFitHeight(zombieFitHeight);
        mainZombie.setFitWidth(zombieFitWidth);
        newZombie.setFitHeight(zombieFitHeight);
        newZombie.setFitWidth(zombieFitWidth);
        int index=gridPane.getChildren().indexOf(mainZombie);
        System.out.println("dasdasd  "+index);
        gridPane.getChildren().remove(index);

        int column,row;
        column=index % 20;
        row=index/20;
//            for (int i=0;i<4;i++){
//                if(i*19-index>=0){
//                    row=i-1;
//                    column=index-row*19;
//                    break;
//                }
//            }


       gridPane.add(newZombie,column,row);
    }
    private void initial(){
        for (int i=0;i<4;i++){
            for (int j=0;j<20;j++){
               // TempZombie tempZombie=new TempZombie((j*zombieFitWidth/2)+(zombieFitWidth/2),i*zombieFitHeight/4+zombieFitHeight/2);
                TempZombie tempZombie=new TempZombie(j*zombieFitWidth/2,i*zombieFitHeight/4);
                System.out.println(tempZombie.getxPosition()+","+tempZombie.getyPosition());
                tempZombie.setFitHeight(zombieFitHeight);
                tempZombie.setFitWidth(zombieFitWidth);
                //imageView.setAlignment(Pos.CENTER);

                tempZombie.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        // System.out.println(label.getText());
                        System.out.println(tempZombie.getLayoutX()+","+tempZombie.getLayoutY());
                    }
                });
                gridPane.add(tempZombie,j,i);

            }
        }
    }
}
