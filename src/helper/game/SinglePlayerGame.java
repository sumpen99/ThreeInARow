package helper.game;
import helper.io.IOHandler;


public class SinglePlayerGame extends GameMode {

    @Override
    public void addPlayers(){
        String name;
        if((validName(name = IOHandler.askForPlayerName(1)))){
            setPlayerNames(name,"Hal");
            return;
        }
        addPlayers();
    }

    @Override
    public boolean runGame(){
        boolean quit = false;
        String pos;
        int index;
        while(!quit && gameBoard.hasSpace()){
            drawBoard();
            if(upNext%2==0){
                pos = IOHandler.askForNewPosition(playerOne);
                if(validBoardPosition(pos,newPos)){
                    putMarkerOnBoard(newPos.row, newPos.col,playerOne.marker);
                }
            }
            else{
                if((index=gameBoard.getFreeIndex())!= -1){
                    IOHandler.askComputerForValue(playerTwo.name);
                    putMarkerOnBoard(index,playerTwo.marker);
                }
            }
        }
        return true;
    }

}
