package helper.game;
import helper.interfaces.IGameMode;
import helper.io.IOHandler;
import helper.matrix.GameBoard;
import helper.matrix.Matrix;
import helper.player.ComputerPlayer;
import helper.player.GamePlayer;
import helper.player.HumanPlayer;
import helper.struct.BoardPosition;
import helper.struct.GameInfo;
import static helper.methods.CommonMethods.stringIsInt;
import static helper.methods.CommonMethods.verifyNewPos;

public abstract class GameMode implements IGameMode {
    Matrix gameBoard;
    BoardPosition newPos;
    GameInfo gameInfo;

    public GameMode(){
        newPos = new BoardPosition();
        gameInfo = new GameInfo();
    }

    public void run(){
        gameInfo.timer.startClock();
        runGame();
        drawBoard();
        gameInfo.runningTime = gameInfo.timer.getTimePassed();
        gameInfo.gamesPlayed++;
        IOHandler.printGameInfo(gameInfo);
        if(evaluateNewGame(IOHandler.askForNewGame())){
            resetStateOfSession();
            run();
        }
    }

    public void setPlayers(GamePlayer p1,GamePlayer p2){
        gameInfo.players = new GamePlayer[]{p1,p2};
    }

    void resetStateOfSession(){
        gameBoard.resetMatrix();
        gameInfo.reset();
    }

    boolean evaluateNewGame(char c){
        return c == 'y';
    }

    public void welcomePlayers(){
        IOHandler.welcomePlayers(gameInfo);
    }

    public boolean validName(String name){
        int size = name.length();
        return size > 0 && size < 255;
    }

    public void putMarkerOnBoard(int row,int col,int value){
        putMarkerOnBoard(gameBoard.getIndex(row,col),value);
    }

    public void putMarkerOnBoard(int index,int value){
        gameBoard.setValue(index,value);
        if(gameBoard.findPatter(gameInfo.getCurrentPlayer().marker)){gameInfo.setWinner();}
        else{gameInfo.upNext++;}
    }

    public void setBoard(){
        int boardSize;
        while(((boardSize = stringIsInt(IOHandler.askForBoardSize())) == -1) || !validBoardSize(boardSize));
        gameBoard = new GameBoard(boardSize);
    }

    public boolean validBoardSize(int size){
        return size >= 3 && size <= 64;
    }

    public void drawBoard(){
        gameBoard.drawToScreen();
    }

    public boolean validBoardPosition(String pos){
        int[] tryPos = verifyNewPos(pos);
        int index;
        if(tryPos != null && gameBoard.validIndex((index=gameBoard.getIndex(tryPos[0],tryPos[1]))) && gameBoard.freeIndex(index)){
            newPos.row = tryPos[0];
            newPos.col = tryPos[1];
            return true;
        }
        return false;
    }

}
