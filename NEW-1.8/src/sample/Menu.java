package sample;



import com.sun.webkit.network.Util;
import javafx.animation.PathTransition;
import javafx.geometry.HPos;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Line;
import javafx.util.Duration;
import sample.game.GameBoard;
import sample.utills.Utill;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.util.Optional;


public class Menu extends Stage {
    private Button newGameButton;
    private Button loadGameButton;
    private Button aboutGameButton;

    private void aboutGameListener(){

        Alert alert=new Alert(Alert.AlertType.INFORMATION, Utill.aboutGameMessage);
        alert.setTitle(Utill.aboutGameTitle);
        alert.setHeaderText(Utill.aboutGameHeader);

        alert.show();
    }

    private void newGameButtonListener(){
        TextInputDialog textInputDialog=new TextInputDialog(Utill.defaultName);
        textInputDialog.setTitle(Utill.newGameTitle);
        textInputDialog.setHeaderText(Utill.headerDialogInputNewGame);
        textInputDialog.setContentText(Utill.contexDialogInputNewGame);

        Optional<String> result=textInputDialog.showAndWait();

        result.ifPresent(name->{
            System.out.println(name);

        });

    }
    private void loadGameButtonListener(){

       Utill.controller.showGameBoard();
        this.close();
    }

    public Menu() {

         GridPane gridPane=new GridPane();
        gridPane.setPadding(new Insets(10*Utill.screenUnit));
        gridPane.setAlignment(Pos.TOP_CENTER);
        gridPane.setVgap(10*Utill.screenUnit);
        gridPane.setHgap(1* Utill.screenUnit);

        Label welcomeLabel=new Label(Utill.menuWelcome);
        GridPane.setHalignment(welcomeLabel, HPos.CENTER);
        gridPane.add(welcomeLabel,1,0);

        aboutGameButton=new Button(Utill.aboutGameTitle);
        aboutGameButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                aboutGameListener();
            }
        });
        newGameButton =new Button(Utill.newGameTitle);
        newGameButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                newGameButtonListener();
            }
        });
        loadGameButton=new Button(Utill.loadGameTitle);
        loadGameButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                loadGameButtonListener();
            }
        });

        VBox vBox=new VBox();
        vBox.getChildren().addAll(newGameButton,loadGameButton,aboutGameButton);
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(2*Utill.screenUnit);

        gridPane.add(vBox,1,2);

        Scene scene=new Scene(gridPane,100*Utill.screenUnit,100*Utill.screenUnit);

        this.setTitle(Utill.menuTitle);
        this.setResizable(false);
        this.setScene(scene);


    }
}
