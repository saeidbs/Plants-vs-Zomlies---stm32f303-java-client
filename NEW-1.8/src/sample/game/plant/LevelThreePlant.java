package sample.game.plant;

public class LevelThreePlant extends Plant {
    public static boolean enable=true;
    public static long plantCanBeUsed=0;
    public LevelThreePlant(int xPosition, int yPosition) {
        super(3, xPosition, yPosition);
    }
    public static void setplantCanBeUsed(long lo) {
        plantCanBeUsed=lo;
    }
}
