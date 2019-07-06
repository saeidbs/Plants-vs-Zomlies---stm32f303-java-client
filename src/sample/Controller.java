package sample;

import sample.game.GameBoard;

public class Controller {
private Menu menu=new Menu();
private GameBoard gameBoard=new GameBoard();



public void showMenu(){
    menu.show();

}
public void showGameBoard(){
    gameBoard.show();
}

}
