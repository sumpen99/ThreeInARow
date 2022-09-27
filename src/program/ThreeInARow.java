package program;
import helper.game.GameMode;
import helper.game.MultiPlayerGame;
import helper.game.SinglePlayerGame;
import helper.io.IOHandler;
import helper.struct.PassedCheck;
import helper.struct.SMDateTime;

import java.util.Scanner;

public class ThreeInARow {
    GameMode gameMode;

    public void runLoop(){
        char input;
        do {
            input = startGame();
            if(evaluateGameMode(input)){
                gameMode.addPlayers();
                gameMode.setBoard();
                gameMode.welcomePlayers();
                gameMode.run();
            }
        }while(!exit(input));
    }

    char startGame(){
        return evaluateInput(IOHandler.printGameMenu());
    }

    char evaluateInput(String str){
        if((str = str.trim()).length() == 0)return 0;
        return str.charAt(0);
    }

    boolean evaluateGameMode(char c){
        boolean result = false;
        if(c == '1'){
            gameMode = new SinglePlayerGame();
            result = true;
        }
        else if(c == '2'){
            gameMode = new MultiPlayerGame();
            result = true;
        }
        return result;
     }

    boolean exit(char c){
        return c == 'q';
    }
}
