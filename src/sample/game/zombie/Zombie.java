package sample.game.zombie;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import sample.utills.Utill;



public abstract class Zombie extends ImageView {
    private int kind,health;
    private int xPosition,yPosition;
    private int size=5* Utill.screenUnit;
    
    public Zombie(int kind, int xPosition, int yPosition) {
        this.kind=kind;
        this.health=kind;
        this.xPosition=xPosition;
        this.yPosition=yPosition;
        setImage(kind);
    }
    public  void move(){

    }
    private   void setImage(int kind){
       // super.setImage(new Image(path));
    }

    public int getxPosition() {
        return xPosition;
    }

    public int getKind() {
        return kind;
    }

    public int getHealth() {
        return health;
    }

    public int getyPosition() {
        return yPosition;
    }

    public int getSize() {
        return size;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void setxPosition(int xPosition) {
        this.xPosition = xPosition;
    }

    public void setyPosition(int yPosition) {
        this.yPosition = yPosition;
    }
}
