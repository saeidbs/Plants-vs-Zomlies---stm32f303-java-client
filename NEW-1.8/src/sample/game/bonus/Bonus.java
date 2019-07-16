package sample.game.bonus;

import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import sample.utills.Utill;

import javax.swing.text.Element;


public abstract class Bonus extends ImageView {
    public static boolean clicked=false;
    private int score = 0;
    private int xPosition,yPosition;
    int kind;
    public Bonus(int kind,int row, int column) {
        this.kind=kind;

        this.xPosition = columnToX(column);
        this.yPosition = rowToY(row);

        setLayoutX(getxPosition());
        setLayoutY(getyPosition());
        setFitWidth(Utill.bonusFitWidth);
        setFitHeight(Utill.bonusFitHeight);
        setImage(kind);


        setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                clicked=true;

            }
        });
    }


        private void setImage (int kind) {
        String path="";
        switch (kind){
            case 1:
                path="\\sample\\game\\bonus\\LevelOnebonus.png";
                break;
            case 2:
                path="\\sample\\game\\bonus\\LevelTwobonus.png";
                break;
            case 3:
                path="\\sample\\game\\bonus\\LevelThreebonus.png";
                break;
        }
                super.setImage(new Image(path));

        }
    public int getxPosition() {
        return xPosition;
    }

    public int getyPosition() {
        return yPosition;
    }

        public static int rowToY ( int row){
            return row * Utill.bonusFitHeight;
        }
        public static int columnToX ( int column){
            return column * Utill.bonusFitWidth;
        }
      public  static int xtoColumn(int xPosition){
        return xPosition/ Utill.bonusFitWidth;
    }
     public static int ytoRow(int yPosition){
        return yPosition/Utill.bonusFitHeight;
    }
    }

