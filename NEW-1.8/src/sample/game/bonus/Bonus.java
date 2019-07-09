package sample.game.bonus;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import sample.utills.Utill;

import javax.swing.text.Element;


public class Bonus extends ImageView {
    private int row, column;
    private int score = 0;
    private int xPosition,yPosition;
    public Bonus(int row, int column) {

        this.xPosition = columnToX(column);
        this.yPosition = rowToY(row);

        setLayoutX(getxPosition());
        setLayoutY(getyPosition());
        setFitWidth(Utill.bonusFitWidth);
        setFitHeight(Utill.bonusFitHeight);
        setImage();
    }


        private void setImage () {
            super.setImage(new Image("\\sample\\download.png"));
        }
        public int getxPosition(){
            return columnToX(column);
        }
    public int getyPosition() {
        return rowToY(row);
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

