package sample.game.plant;

public class LevelTwoPlant extends Plant {
    public static boolean enable=true;
    public static long plantCanBeUsed=0;
    public LevelTwoPlant( int xPosition, int yPosition) {
        super(2, xPosition, yPosition);
    }

    public static void setplantCanBeUsed(long lo) {
        plantCanBeUsed=lo;
    }
}
