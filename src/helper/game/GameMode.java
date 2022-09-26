package helper.game;
import helper.interfaces.IGameMode;
import helper.io.IOHandler;
import helper.matrix.GameBoard;
import helper.matrix.Matrix;
import helper.player.GamePlayer;
import static helper.methods.CommonMethods.stringIsInt;

public abstract class GameMode implements IGameMode {
    GamePlayer playerOne,playerTwo;
    GameBoard gameBoard;

    public boolean validName(String name){
        int size = name.length();
        return size > 0 && size < 255;
    }

    public void setPlayerNames(String nameOne,String nameTwo){
        playerOne = new GamePlayer(nameOne,1);
        playerTwo = new GamePlayer(nameTwo,2);
    }

    public void setBoard(){
        int boardSize;
        while(((boardSize = stringIsInt(IOHandler.askForBoardSize())) == -1) || !validBoardSize(boardSize));
        gameBoard = new GameBoard(boardSize);
        drawBoard();
    }

    public boolean validBoardSize(int size){
        return size >= 3 && size <= 64;
    }

    public void drawBoard(){
        gameBoard.drawToScreen();
    }
}
