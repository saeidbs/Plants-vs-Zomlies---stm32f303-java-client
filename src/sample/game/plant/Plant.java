package sample.game.plant;

import sample.utills.Utill;

public abstract class Plant {
    private int kind,health;
    private int xPosition,yPosition;
    private int size=4* Utill.screenUnit;

    public Plant(int kind,int xPosition, int yPosition) {
        this.kind = kind;
        this.health = kind;
        this.xPosition = xPosition;
        this.yPosition = yPosition;
    }



    private void setImage(String path){
        super
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
