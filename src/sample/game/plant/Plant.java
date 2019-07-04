package sample.game.plant;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import sample.utills.Utill;

import java.nio.file.Path;

public abstract class Plant  extends ImageView {
    private int kind,health;
    private int xPosition,yPosition;
    private int size=4* Utill.screenUnit;

    public Plant(int kind,int xPosition, int yPosition) {
        this.kind = kind;
        this.health = kind;
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        setImage(kind);
    }



    private void setImage(int kind){
      //  super.setImage(new Image(path));
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
}
