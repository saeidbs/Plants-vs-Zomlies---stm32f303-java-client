package sample.game.plant;

public class LevelOnePlant extends Plant {
    public static boolean enable=true;
    public static long plantCanBeUsed=0;

    public LevelOnePlant( int xPosition, int yPosition) {
        super(1, xPosition, yPosition);
    }


    public static void setplantCanBeUsed(long lo) {
        plantCanBeUsed=lo;
    }
}
