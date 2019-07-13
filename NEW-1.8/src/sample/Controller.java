package sample;

import sample.game.GameBoard;

public class Controller {




private Menu menu=new Menu();
private Uart uart;
private GameBoard gameBoard=new GameBoard(uart);


public void showMenu(){
    menu.show();

}
public void showGameBoard(){
    gameBoard.show();
}

    public Uart getUart() {
        return uart;
    }

    public void setUart(String string){

    uart=new Uart(string);

    }

    public void setGameBoard(){

    gameBoard=new GameBoard(uart);

    }

    public GameBoard getGameBoard() {
        return gameBoard;
    }
}
