package sample;



import com.sun.webkit.network.Util;
import javafx.animation.PathTransition;
import javafx.geometry.HPos;
import javafx.scene.control.TextInputDialog;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
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
import javafx.stage.Stage;

import java.util.Optional;


public class Menu extends Stage {
    private Button newGameButton;
    private Button loadGameButton;
    private Button aboutGameButton;
    private DropShadow dropShadow = new DropShadow();
    private void aboutGameListener(){

        Alert alert=new Alert(Alert.AlertType.INFORMATION, Utill.aboutGameMessage);
        alert.setTitle(Utill.aboutGameTitle);
        alert.setHeaderText(Utill.aboutGameHeader);

        alert.show();
    }

    private void newGameButtonListener(){
        Utill.controller.showGameBoard();
        this.close();

//        TextInputDialog textInputDialog=new TextInputDialog(Utill.defaultName);
//        textInputDialog.setTitle(Utill.newGameTitle);
//        textInputDialog.setHeaderText(Utill.headerDialogInputNewGame);
//        textInputDialog.setContentText(Utill.contexDialogInputNewGame);
//
//        Optional<String> result=textInputDialog.showAndWait();
//
//        result.ifPresent(name->{
//            System.out.println(name);
//
//        });

    }
    private void loadGameButtonListener(){

//       Utill.controller.showGameBoard();
//        this.close();
    }

    public Menu() {

         GridPane gridPane=new GridPane();
        gridPane.setPadding(new Insets(10*Utill.screenUnit));
        gridPane.setAlignment(Pos.TOP_CENTER);
        gridPane.setVgap(10*Utill.screenUnit);
        gridPane.setHgap(1* Utill.screenUnit);

        gridPane.setBackground(new Background(new BackgroundImage(
                new Image("\\sample\\menu.png"),
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(100*Utill.screenUnit, 100*Utill.screenUnit, true, true, true, true))));


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
        aboutGameButton.addEventFilter(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                aboutGameButton.setEffect(dropShadow);
            }
        });
        aboutGameButton.addEventFilter(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                aboutGameButton.setEffect(null);
            }
        });




        newGameButton =new Button(Utill.newGameTitle);

        newGameButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                newGameButtonListener();
            }
        });
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






        loadGameButton=new Button(Utill.loadGameTitle);
        loadGameButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                loadGameButtonListener();
            }
        });
        loadGameButton.addEventFilter(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                loadGameButton.setEffect(dropShadow);
            }
        });
        loadGameButton.addEventFilter(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                loadGameButton.setEffect(null);
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
