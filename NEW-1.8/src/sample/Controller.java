package sample;

import sample.game.GameBoard;

public class Controller {




private Menu menu=new Menu();
private GameBoard gameBoard=new GameBoard();
private Uart uart=new Uart("COM4");


public void showMenu(){
    menu.show();

}
public void showGameBoard(){
    gameBoard.show();
}

    public Uart getUart() {
        return uart;
    }

    public GameBoard getGameBoard() {
        return gameBoard;
    }
}
