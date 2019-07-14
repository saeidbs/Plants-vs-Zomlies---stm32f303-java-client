package sample.game;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import sample.utills.Utill;
import static sample.utills.Utill.controller;


public class Result extends Stage {


    private Label resultLabel;
    private Label scoreLabel;
    private Button newGameButton;
    private Button exitButton;

    private Result() {
        resultLabel = new Label("Result");
        scoreLabel = new Label("Score:");
        newGameButton = new Button("NEW GAME");
        exitButton = new Button("EXIT GAME");

        DropShadow dropShadow = new DropShadow();
        newGameButton.addEventFilter(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                newGameButton.setEffect(dropShadow);
            }
        });

        newGameButton.addEventFilter(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                newGameButton.setEffect(null);
            }
        });

        newGameButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
               // Utill.controller.showGameBoard();
                Result.this.close();
                controller.getGameBoard().close();
                controller.setGameBoard();
                controller.getGameBoard().show();
            }
        });

        exitButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Result.this.close();
                controller.getGameBoard().close();
                System.exit(0);
            }
        });

        exitButton.addEventFilter(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                exitButton.setEffect(dropShadow);
            }
        });

        exitButton.addEventFilter(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                exitButton.setEffect(null);
            }
        });





        HBox hBox = new HBox();
        hBox.getChildren().addAll(newGameButton, exitButton);
        hBox.setSpacing(3* Utill.screenUnit);
        hBox.setAlignment(Pos.CENTER);

        VBox vBox = new VBox();
        vBox.getChildren().addAll(resultLabel, scoreLabel,hBox);
        vBox.setSpacing(5 * Utill.screenUnit);
        vBox.setAlignment(Pos.CENTER);
        vBox.setLayoutX(100 * Utill.screenUnit / 3-5);
        vBox.setLayoutY(100 * Utill.screenUnit / 4);

        VBox.setMargin(hBox,new Insets(50,0,0,0));
//        hBox.setLayoutX(100 * Utill.screenUnit / 3-20);
//        hBox.setLayoutY(100 * Utill.screenUnit / 2);

        Pane pane = new Pane();
        pane.getChildren().addAll(vBox);
      //  pane.setPrefSize(100 * Utill.screenUnit, 100 * Utill.screenUnit);
        pane.setBackground(new Background(new BackgroundImage(
                new Image("\\sample\\menu.png"),
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(100*Utill.screenUnit, 100*Utill.screenUnit, true, true, true, true))));



        Scene scene = new Scene(pane, 100 * Utill.screenUnit, 100 * Utill.screenUnit);
        this.setTitle("Finish");
        this.setResizable(false);
        this.setScene(scene);
        

    }

    public Result(Boolean bool,int score){
        this();
        if (bool)
            resultLabel.setText("YOU WIN!!!!!!!");
        else
            resultLabel.setText("GAME OVER");

        this.scoreLabel.setText("YOUR SCORE IS: "+score);

    }
}
