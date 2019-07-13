package sample.game;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import sample.utills.Utill;


public class Result extends Stage {

    private Label result;
    private Label score;
    private Button newGame;
    private Button exit;

    private Result() {
        result = new Label("Result");
        score = new Label("Score:");
        newGame = new Button("NEW GAME");
        exit = new Button("EXIT GAME");


        newGame.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
               // Utill.controller.showGameBoard();
                Result.this.close();
            }
        });





        HBox hBox = new HBox();
        hBox.getChildren().addAll(newGame, exit);
        hBox.setSpacing(3* Utill.screenUnit);
        hBox.setAlignment(Pos.CENTER);

        VBox vBox = new VBox();
        vBox.getChildren().addAll(result, score,hBox);
        vBox.setSpacing(5 * Utill.screenUnit);
        vBox.setAlignment(Pos.CENTER);
        vBox.setLayoutX(100 * Utill.screenUnit / 3-5);
        vBox.setLayoutY(100 * Utill.screenUnit / 4);

        VBox.setMargin(hBox,new Insets(50,0,0,0));
//        hBox.setLayoutX(100 * Utill.screenUnit / 3-20);
//        hBox.setLayoutY(100 * Utill.screenUnit / 2);

        Pane pane = new Pane();
        pane.getChildren().addAll(vBox);
        pane.setPrefSize(100 * Utill.screenUnit, 100 * Utill.screenUnit);


        Scene scene = new Scene(pane, 100 * Utill.screenUnit, 100 * Utill.screenUnit);
        this.setTitle("Finish");
        this.setResizable(false);
        this.setScene(scene);

    }

    public Result(Boolean bool,int score){
        this();
        if (bool)
            result.setText("YOU WIN!!!!!!!");
        else
            result.setText("GAME OVER");

        this.score.setText("YOUR SCORE IS: "+score);

    }
}
