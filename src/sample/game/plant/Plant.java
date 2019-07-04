package sample.game.plant;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import jdk.jshell.execution.Util;
import sample.utills.Utill;

import java.nio.file.Path;

public abstract class Plant  extends ImageView {
    private int kind,health;
    private int xPosition,yPosition;
    private int size=4* Utill.screenUnit;

    public Plant(int kind,int xPosition, int yPosition) {
        this.kind = kind;
        this.health = kind;

        this.xPosition = columnToX(xtoColumn(xPosition));
        this.yPosition = rowToY(ytoRow(yPosition));

        setLayoutX(getxPosition());
        setLayoutY(getyPosition());
        setFitWidth(Utill.plantFitWidth);
        setFitHeight(Utill.plantFitHeight);
        setImage(kind);
    }



    private void setImage(int kind){
       super.setImage(new Image("\\sample\\orange.png"));
    }
    public int getHealth() {
        return health;
    }

    public int getxPosition() {
        return xPosition;
    }

    public int getyPosition() {
        return yPosition;
    }

    public  int xtoColumn(int xPosition){
        return xPosition/ Utill.plantFitWidth;
    }
    public  int ytoRow(int yPosition){
        return yPosition/Utill.plantFitHeight;
    }
    public  int rowToY(int row){
        return row* Utill.plantFitHeight;
    }
    public  int columnToX(int column){
        return column* Utill.plantFitWidth;
    }
}
