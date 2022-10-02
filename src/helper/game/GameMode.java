package helper.game;
import helper.interfaces.IGameMode;
import helper.io.IOHandler;
import helper.matrix.GameBoard;
import helper.player.GamePlayer;
import helper.struct.BoardPosition;
import helper.struct.GameInfo;
import helper.struct.PassedCheck;

import static helper.methods.CommonMethods.stringIsInt;
import static helper.methods.CommonMethods.verifyNewPos;

/**
 * Abstract class with childs SinglePlayer and MultiPlayer
 * */
public abstract class GameMode implements IGameMode {
    GameBoard gameBoard;
    BoardPosition newPos;
    GameInfo gameInfo;
    PassedCheck validInput;

    public GameMode(){
        newPos = new BoardPosition();
        gameInfo = new GameInfo();
        validInput = new PassedCheck();
    }

    /**
     * The run loop for both Gamemodes who then
     * overrides run()
     * */
    public void run(){
        gameInfo.shuffleDrawOrder();
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

    /**
     * Puts things in order for a new game
     * */
    public void resetStateOfSession(){
        gameBoard.resetMatrix();
        gameInfo.reset();
    }

    /**
     * Adds both players to a list thats keeps track of whos next
     * in line to put a marker on the gameboard
     * */
    public void setPlayers(GamePlayer p1,GamePlayer p2){
        gameInfo.players = new GamePlayer[]{p1,p2};
    }

    /**
     * If the user wants to play again. Everything except a y return false
     * */
    boolean evaluateNewGame(char c){
        return c == 'y';
    }

    /**
     * Makles a call to Iohandler and welcomes user to the game
     * */
    public void welcomePlayers(){
        IOHandler.welcomePlayers(gameInfo);
    }

    /**
     * Some kind of check for name makes sense
     * */
    public boolean validName(String name){
        int size = name.length();
        return size > 0 && size < 255;
    }

    public void putMarkerOnBoard(int row,int col,int value){
        putMarkerOnBoard(gameBoard.getIndex(row,col),value);
    }

    /**
     * Sets the value to the correct index inside matrix and then make a check
     * if we have a winner
     * */
    public void putMarkerOnBoard(int index,int value){
        gameInfo.getCurrentPlayer().lastMarkerIndex = index;
        gameBoard.setValue(index,value);
        if(gameBoard.findWinningPatter(gameInfo.getCurrentPlayer().marker,gameInfo.markersToWin)){gameInfo.setWinner();}
        else{gameInfo.updateNext();}
    }

    public void setBoard(){
        int boardSize;
        while(((boardSize = stringIsInt(IOHandler.askForBoardSize())) == -1) || !validBoardSize(boardSize));
        gameBoard = new GameBoard(boardSize);
    }

    /**
     * Asks user for the amount of markers in a row to win
     * */
    public void setMarkersToWinValue(){
        int keyValue;
        if(gameBoard.columns == 3){gameInfo.markersToWin = 3;}
        else{
            while(((keyValue = stringIsInt(IOHandler.askForKeyValue(gameBoard.columns))) == -1) || !validKeyValue(keyValue));
            gameInfo.markersToWin = keyValue;
        }
    }

    /**
     * Validates Boardsize. As long its bigger then 3 it could be anything
     * but 11 is a nice number
     * */
    public boolean validBoardSize(int size){
        return size >= 3 && size <= 11;
    }

    /**
     * The amount of markers in a row to win the game
     * */
    public boolean validKeyValue(int size){
        return size >= 3 && size <= gameBoard.columns;
    }

    public void drawBoard(){
        gameBoard.drawToScreen();
    }

    /**
     * Validates the input of user
     * the correct value should be row col
     * if user inputs quit we end current game
     * */
    public boolean validBoardPosition(String pos){
        validInput.clear();
        int[] tryPos = verifyNewPos(pos,validInput);
        int index;
        if(tryPos != null && gameBoard.validIndex((index=gameBoard.getIndex(tryPos[0],tryPos[1]))) && gameBoard.freeIndex(index)){
            newPos.row = tryPos[0];
            newPos.col = tryPos[1];
            return true;
        }
        else{
            if(validInput.message.equals("quit")){
                gameInfo.quit = true;
            }
        }
        return false;
    }

}
