package helper.game;
import helper.ai.GameTheory;
import helper.ai.MiniMax;
import helper.ai.FollowTheory;
import helper.io.IOHandler;
import helper.player.GamePlayer;

/**
 * Extends GameMode but with an Ai-Computer as player2
 * */
public class SinglePlayerGame extends GameMode {
    GameTheory gameTheory;
    final String cmpName = "Hal";

    @Override
    public void addPlayers(){
        String name;
        if((validName(name = IOHandler.askForPlayerName(1)))){
            setPlayers(new GamePlayer(name,1),new GamePlayer(cmpName,2));
            return;
        }
        addPlayers();
    }

    @Override
    public void setMarkersToWin(){
        setMarkersToWinValue();
        setAiFunc();

    }

    /**
     * If the board is a standard three in a row we use MiniMax
     * Else we have a custom FollowTheory wich works on large boards and any kind of
     * markers but you could argue its not DeepBlue smart
     * */
    void setAiFunc(){
        if(gameBoard.columns == 3)gameTheory = new MiniMax(gameBoard,0,2,1,3,cmpName);
        else gameTheory = new FollowTheory(gameBoard,0,2,1,gameInfo.markersToWin,cmpName);
    }

    /**
     * Until we have a game to play it keeps going
     * User can at any point type quit to exit
     * If that doesnt happen we take turns to put a marker on the board player vs computer
     * */
    @Override
    public void runGame(){
        String pos;
        while(!gameInfo.quit && gameBoard.hasSpace()){
            drawBoard();
            if(gameInfo.getIndex()==0){
                pos = IOHandler.askForNewPosition(gameInfo.getCurrentPlayer());
                if(validBoardPosition(pos)){putMarkerOnBoard(newPos.row,newPos.col,gameInfo.getCurrentPlayer().marker);}
            }
            else{
                gameTheory.runAlgorithm();
                putMarkerOnBoard(gameTheory.getNewIndex(),gameInfo.getCurrentPlayer().marker);
            }
        }
    }

}
