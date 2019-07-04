package sample.utills;

public abstract class Utill {
    public static final int screenUnit=4;
    public static final int pageSize=250 * screenUnit*15/10;
    public static final int screenHeight=200*screenUnit;
    public static final int xScreen=pageSize/5;
    public static final int yScreen=0;
    public static final int boardGameDistanceY=screenHeight/4;

    public static int zombieFitWidth=(int)((Utill.pageSize/1.25-30)/20);
    public static int zombieFitHeight=(int)(Utill.screenHeight/4);
    public static int plantFitWidth=(int)((Utill.pageSize/1.25-30)/20);
    public static int plantFitHeight=(int)(Utill.screenHeight/4);

    public static double animationStep=3;



    public static final String aboutGameMessage="bazi kon pare shi bekhandim";
    public static final String aboutGameTitle="About Game";
    public static final String menuWelcome="Welcome To Game";
    public static final String newGameTitle="New Game";
    public static final String loadGameTitle="Load Game";

    public static final String menuTitle="MENU";
    public static final String aboutGameHeader="About The Game";


    public static final String defaultName="You'r Name";
    public static final String headerDialogInputNewGame="Enter your name:";
    public static final String contexDialogInputNewGame="Name:";

}
