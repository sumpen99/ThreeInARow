package helper.game;

import helper.io.IOHandler;

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
        return true;
    }
}
