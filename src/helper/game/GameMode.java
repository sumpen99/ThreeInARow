package helper.game;
import helper.interfaces.IGameMode;
import helper.io.IOHandler;
import helper.matrix.GameBoard;
import helper.matrix.Matrix;
import helper.player.GamePlayer;
import helper.struct.BoardPosition;
import helper.struct.GameInfo;
import helper.struct.SMDateTime;

import static helper.methods.CommonMethods.stringIsInt;
import static helper.methods.CommonMethods.verifyNewPos;

public abstract class GameMode implements IGameMode {
    GamePlayer playerOne,playerTwo;
    Matrix gameBoard;
    BoardPosition newPos;
    GameInfo info;
    int[] tryPos;
    int upNext;

    public GameMode(){
        newPos = new BoardPosition();
        info = new GameInfo();
    }

    public void run(){
        info.timer.startClock();
        runGame();
        drawBoard();
        info.runningTime = info.timer.getTimePassed();
        info.gamesPlayed++;
        IOHandler.printGameInfo(info);
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
        setPlayerList();
    }

    public void setPlayerList(){
        info.players = new GamePlayer[]{playerOne,playerTwo};
    }

    public void putMarkerOnBoard(int row,int col,int value){
        putMarkerOnBoard(gameBoard.getIndex(row,col),value);
    }

    public void putMarkerOnBoard(int index,int value){
        gameBoard.setValue(index,value);
        if(gameBoard.findPatter(info.players[upNext%2].marker)){
            info.lastWinner = info.players[upNext%2];
            info.winner = true;
            info.quit = true;
            info.lastWinner.winStreak++;
        }
        else{
            updateNextIndex();
        }
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
