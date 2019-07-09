package sample.game.bonus;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import sample.utills.Utill;

import javax.swing.text.Element;


public abstract class Bonus extends ImageView {
    private int row, column;
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
    }


        private void setImage (int kind) {
            super.setImage(new Image("\\sample\\download.png"));
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

