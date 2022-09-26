package helper.game;

import helper.io.IOHandler;
import helper.player.GamePlayer;

public class MultiPlayerGame extends GameMode{

    @Override
    public void addPlayers(){
        String nameOne,nameTwo;
        if((validName(nameOne = IOHandler.askForPlayerName(1)))){
            while(!(validName(nameTwo = IOHandler.askForPlayerName(2)))){}
            setPlayerNames(nameOne,nameTwo);
            return;
        }
        addPlayers();
    }

    @Override
    public boolean runGame(){
        boolean quit = false;
        IOHandler.welcomePlayers(playerOne.name,playerTwo.name);
        String pos;
        GamePlayer[] players = new GamePlayer[]{playerOne,playerTwo};
        int upNext = 0;
        while(!quit && gameBoard.hasSpace()){
            drawBoard();
            pos = IOHandler.askForNewPosition(players[upNext%2]);
            if(validBoardPosition(pos,newPos)){
                gameBoard.setValue(newPos.row, newPos.col,players[upNext%2].marker);
                upNext++;
            }
        }
        drawBoard();
        return true;
    }
}
