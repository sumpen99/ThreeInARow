package helper.game;
import helper.io.IOHandler;
import helper.player.HumanPlayer;

public class MultiPlayerGame extends GameMode{

    @Override
    public void addPlayers(){
        String nameOne,nameTwo;
        if((validName(nameOne = IOHandler.askForPlayerName(1)))){
            while(!(validName(nameTwo = IOHandler.askForPlayerName(2))));
            setPlayers(new HumanPlayer(nameOne,1),new HumanPlayer(nameTwo,2));
            return;
        }
        addPlayers();
    }

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
