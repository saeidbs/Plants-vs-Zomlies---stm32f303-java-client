package sample.game.plant;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import sample.utills.Utill;


import java.nio.file.Path;

public abstract class Plant extends ImageView {
    private int kind, health;
    private int xPosition, yPosition;


    public Plant(int kind, int row, int column) {
        this.kind = kind;

        if (kind == 3)
            this.health = kind + 1;
        else
            this.health = kind;

        this.xPosition = columnToX(column);
        this.yPosition = rowToY(row);

        setLayoutX(getxPosition());
        setLayoutY(getyPosition());
        setFitWidth(Utill.plantFitWidth);
        setFitHeight(Utill.plantFitHeight);
        setImage(kind);
    }

    public int getKind() {
        return kind;
    }
    //  public Plant(int kind,int xPosition, int yPosition) {
//        this.kind = kind;
//
//        if (kind==3)
//            this.health = kind+1;
//        else
//            this.health=kind;
//
//        this.xPosition = columnToX(xtoColumn(xPosition));
//        this.yPosition = rowToY(ytoRow(yPosition));
//
//        setLayoutX(getxPosition());
//        setLayoutY(getyPosition());
//        setFitWidth(Utill.plantFitWidth);
//        setFitHeight(Utill.plantFitHeight);
//        setImage(kind);
//    }


    private void setImage(int kind) {
        // super.setImage(new Image("\\sample\\download.png"));
        String path = "";
        switch (kind) {
            case 1:
                path = "\\sample\\game\\plant\\LevelOnePlant.png";
                break;
            case 2:
                path = "\\sample\\game\\plant\\LevelTwoPlant.png";
                break;
            case 3:
                path = "\\sample\\game\\plant\\LevelThreePlant.png";
                break;
        }
        super.setImage(new Image(path));
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

    public static int xtoColumn(int xPosition) {
        return xPosition / Utill.plantFitWidth;
    }

    public static int ytoRow(int yPosition) {
        return yPosition / Utill.plantFitHeight;
    }

    public int rowToY(int row) {
        return row * Utill.plantFitHeight;
    }

    public int columnToX(int column) {
        return column * Utill.plantFitWidth;
    }


}
