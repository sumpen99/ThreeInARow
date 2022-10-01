package helper.game;
import helper.ai.MiniMax;
import helper.ai.RandomIndexTheory;
import helper.interfaces.IGameTheory;
import helper.io.IOHandler;
import helper.player.GamePlayer;

public class SinglePlayerGame extends GameMode {
    IGameTheory gameTheory;

    @Override
    public void addPlayers(){
        String name;
        if((validName(name = IOHandler.askForPlayerName(1)))){
            setPlayers(new GamePlayer(name,1),new GamePlayer("Hal",2));
            return;
        }
        addPlayers();
    }

    @Override
    public void setMarkersToWin(){
        setMarkersToWinValue();
        setAiFunc();

    }

    void setAiFunc(){
        if(gameBoard.columns == 3)gameTheory = new MiniMax(gameBoard,0,2,1,3);
        else gameTheory = new RandomIndexTheory(gameBoard,0,2,1,gameInfo.markersToWin);
    }

    @Override
    public void runGame(){
        String pos;
        int index;
        while(!gameInfo.quit && gameBoard.hasSpace()){
            drawBoard();
            if(gameInfo.getIndex()==0){
                pos = IOHandler.askForNewPosition(gameInfo.getCurrentPlayer());
                if(validBoardPosition(pos)){putMarkerOnBoard(newPos.row,newPos.col,gameInfo.getCurrentPlayer().marker);}
            }
            else{
                index = gameTheory.findBestMove();
                IOHandler.askComputerForValue(gameInfo.getCurrentPlayer().name);
                putMarkerOnBoard(index,gameInfo.getCurrentPlayer().marker);
            }
        }
    }

}
