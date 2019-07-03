package sample.game;

import com.sun.javafx.util.Utils;
import com.sun.prism.paint.Color;
import com.sun.webkit.network.Util;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.image.Image;
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
        informationLabel.setMaxWidth(Utill.pageSize/6);
        informationLabel.setMaxHeight(Utill.pageSize/6);
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
        Pane pane =new Pane();
        pane.setBackground(new Background(new BackgroundImage(
                new Image("\\sample\\saeid.jpg"),
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(Utill.pageSize, Utill.pageSize, true, true, true, true))));
        BorderPane root = new BorderPane();

        root.setLeft(leftVBox);
        root.setCenter(pane);
        root.setRight(rightVBox);

//        SplitPane root=new SplitPane();
//        root.setDividerPositions(0.2f,0.8f);
//        root.getItems().addAll(leftVBox,pane,rightVBox);

        Scene scene=new Scene(root,Utill.pageSize,Utill.pageSize);
        this.setScene(scene);
        this.setResizable(false);
    }






}
