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
        IOHandler.welcomePlayers(playerOne.name,playerTwo.name);
        String pos;
        int index;
        while(!quit && gameBoard.hasSpace()){
            drawBoard();
            pos = IOHandler.askForNewPosition(playerOne);
            if(validBoardPosition(pos,newPos)){
                gameBoard.setValue(newPos.row, newPos.col,playerOne.marker);
                drawBoard();
                if((index=gameBoard.getFreeIndex())!= -1){
                    IOHandler.askComputerForValue(playerTwo.name);
                    gameBoard.setValue(index,playerTwo.marker);
                }
            }
        }
        return true;
    }
}
