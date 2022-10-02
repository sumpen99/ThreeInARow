package helper.game;
import helper.io.IOHandler;
import helper.player.GamePlayer;

/**
 * Multiplayer GameMode
 * Overrides three methods
 * */
public class MultiPlayerGame extends GameMode{

    @Override
    public void addPlayers(){
        String nameOne,nameTwo;
        if((validName(nameOne = IOHandler.askForPlayerName(1)))){
            while(!(validName(nameTwo = IOHandler.askForPlayerName(2))));
            setPlayers(new GamePlayer(nameOne,1),new GamePlayer(nameTwo,2));
            return;
        }
        addPlayers();
    }

    @Override
    public void setMarkersToWin(){
        setMarkersToWinValue();
    }

    /**
     * Until we have a game to play it keeps going
     * User can at any point type quit to exit
     * If that doesnt happen we take turns to put a marker on the board player vs player
     * */
    @Override
    public void runGame(){
        String pos;
        while(!gameInfo.quit && gameBoard.hasSpace()){
            drawBoard();
            pos = IOHandler.askForNewPosition(gameInfo.getCurrentPlayer());
            if(validBoardPosition(pos)){
                putMarkerOnBoard(newPos.row,newPos.col,gameInfo.getCurrentPlayer().marker);
            }
        }
    }
}
