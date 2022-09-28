package helper.game;
import helper.io.IOHandler;
import helper.player.GamePlayer;

public class SinglePlayerGame extends GameMode {

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
    public void runGame(){
        String pos;
        int index;
        while(!gameInfo.quit && gameBoard.hasSpace()){
            drawBoard();
            if(gameInfo.getIndex()==0){
                pos = IOHandler.askForNewPosition(gameInfo.getCurrentPlayer());
                if(validBoardPosition(pos)){
                    putMarkerOnBoard(newPos.row,newPos.col,gameInfo.getCurrentPlayer().marker);
                }
            }
            else{
                int lastIndex = gameInfo.getCurrentPlayer().lastMarkerIndex;
                if(lastIndex == -1){index=gameBoard.getFreeIndex();}
                else index = gameBoard.lookForNewPosition(lastIndex);
                IOHandler.askComputerForValue(gameInfo.getCurrentPlayer().name);
                putMarkerOnBoard(index,gameInfo.getCurrentPlayer().marker);
            }
        }
    }

}
