package helper.game;
import helper.io.IOHandler;
import helper.player.GamePlayer;


public class MultiPlayerGame extends GameMode{

    @Override
    public void addPlayers(){
        String nameOne,nameTwo;
        if((validName(nameOne = IOHandler.askForPlayerName(1)))){
            while(!(validName(nameTwo = IOHandler.askForPlayerName(2))));
            setPlayerNames(nameOne,nameTwo);
            return;
        }
        addPlayers();
    }

    @Override
    public void runGame(){
        String pos;
        while(!info.quit && gameBoard.hasSpace()){
            drawBoard();
            pos = IOHandler.askForNewPosition(info.players[upNext%2]);
            if(validBoardPosition(pos,newPos)){
                putMarkerOnBoard(newPos.row,newPos.col,info.players[upNext%2].marker);
            }
        }
    }
}
