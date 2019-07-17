package sample.utills;

import sample.Controller;

public abstract class Utill {

    public static  Controller controller;





    public static final int stateMenu=0;
    public static final int stateAbout=1;
    public static final int stateNewGame=2;

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
    public static int bonusFitWidth=(int)((Utill.pageSize/1.25-30)/20);
    public static int bonusFitHeight=(int)(Utill.screenHeight/4);

    public static double animationStep=3;
    public final static int TIME_TO_SEC=1000;



    public static final String aboutGameMessage="This is Plants VS Zombie With Micro\nProduse by: Saeid Bahmani AND Masoud Kermanipoor";
    public static final String aboutGameTitle="About Game";
    public static final String menuWelcome="Welcome To Game";
    public static final String newGameTitle="New Game";
    public static final String loadGameTitle="Load Game";

    public static final String menuTitle="MENU";
    public static final String aboutGameHeader="About The Game";


    public static final String defaultName="You'r Name";
    public static final String headerDialogInputNewGame="Enter your name:";
    public static final String contexDialogInputNewGame="Name:";
    public static final String saveGameTitle="Save Game";
}
