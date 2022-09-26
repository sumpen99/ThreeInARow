package helper.game;

import helper.io.IOHandler;
import helper.player.GamePlayer;

public class SinglePlayerGame extends GameMode {

    @Override
    public void addPlayers(){
        String name;
        if((validName(name = IOHandler.askForPlayerName(1)))){
            setPlayerNames(name,"Hal The Computer");
            return;
        }
        addPlayers();
    }

    @Override
    public boolean runGame(){
        boolean quit = false;
        /*while(!quit){
            IOHandler.welcomePlayers(playerOne.name,playerTwo.name);
            drawBoard();
        }*/
        return true;
    }
}
