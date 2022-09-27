package helper.game;
import helper.interfaces.IGameMode;
import helper.io.IOHandler;
import helper.matrix.GameBoard;
import helper.matrix.Matrix;
import helper.player.GamePlayer;
import helper.struct.BoardPosition;

import static helper.methods.CommonMethods.stringIsInt;
import static helper.methods.CommonMethods.verifyNewPos;

public abstract class GameMode implements IGameMode {
    GamePlayer[] players;
    GamePlayer playerOne,playerTwo;
    Matrix gameBoard;
    BoardPosition newPos;
    int[] tryPos;
    int upNext;

    public GameMode(){
        newPos = new BoardPosition();
    }

    public void welcomePlayers(){
        IOHandler.welcomePlayers(playerOne.name,playerTwo.name);
    }

    public boolean validName(String name){
        int size = name.length();
        return size > 0 && size < 255;
    }

    public void setPlayerNames(String nameOne,String nameTwo){
        playerOne = new GamePlayer(nameOne,1);
        playerTwo = new GamePlayer(nameTwo,2);
    }

    public void setPlayerList(){
        players = new GamePlayer[]{playerOne,playerTwo};
    }

    public void putMarkerOnBoard(int row,int col,int value){
        gameBoard.setValue(newPos.row, newPos.col,playerOne.marker);
        updateNextIndex();
    }

    public void putMarkerOnBoard(int index,int value){
        gameBoard.setValue(index,playerTwo.marker);
        updateNextIndex();
    }

    public void setBoard(){
        int boardSize;
        while(((boardSize = stringIsInt(IOHandler.askForBoardSize())) == -1) || !validBoardSize(boardSize));
        gameBoard = new GameBoard(boardSize);
        //drawBoard();
    }

    public boolean validBoardSize(int size){
        return size >= 3 && size <= 64;
    }

    public void drawBoard(){
        gameBoard.drawToScreen();
    }

    public boolean validBoardPosition(String pos,BoardPosition newPos){
        tryPos = verifyNewPos(pos);
        int index;
        if(tryPos != null && gameBoard.validIndex((index=gameBoard.getIndex(tryPos[0],tryPos[1]))) && gameBoard.freeIndex(index)){
            newPos.row = tryPos[0];
            newPos.col = tryPos[1];
            return true;
        }
        return false;
    }

    public void updateNextIndex(){
        upNext++;
    }
}
