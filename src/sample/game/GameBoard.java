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
import sample.utills.Utill;



public class GameBoard extends Stage {
    private Label timeLabel;
    private Label roundLabel;
    private Label scoreLabel;
    private Label lifeLabel;
    private Label informationLabel;
    private Button saveButton;

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
        rightVBox.getChildren().addAll(scoreLabel,lifeLabel,saveButton);
        rightVBox.setAlignment(Pos.CENTER);
        rightVBox.setSpacing(2* Utill.screenUnit);

        GridPane gridPane=new GridPane();
        for (int i=0;i<4;i++){
            for (int j=0;j<20;j++){
                ImageView imageView=new ImageView("\\sample\\wormDamage.png");
                imageView.setFitHeight(Utill.screenHeight/4);
                imageView.setFitWidth(Utill.pageSize/1.25/20);
                //imageView.setAlignment(Pos.CENTER);
                imageView.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                       // System.out.println(label.getText());
                        System.out.println(imageView.getLayoutX()+","+imageView.getLayoutY());
                    }
                });
                gridPane.add(imageView,j,i);

            }
        }

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






}
