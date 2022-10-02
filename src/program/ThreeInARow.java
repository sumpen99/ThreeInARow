package program;
import helper.game.GameMode;
import helper.game.MultiPlayerGame;
import helper.game.SinglePlayerGame;
import helper.io.IOHandler;
import helper.struct.SMDateTime;
import static helper.methods.CommonMethods.evaluateInput;

/**
 * The main loop of the program
 * The user can choose from different gamemodes,gridsize and markers in a row
 * Supported grids are 3*3 -> 10*10
 * Supported Markers-In-A-Row follows grid size with a minimum of 3
 * */
public class ThreeInARow {
    GameMode gameMode;

    /**
     * Sets some Global instances
     * DateTime is used for logging error and
     * Iohandler is used for every input/output call
     * */
    public void setGlobal(){
        SMDateTime.initSMDateTime();
        IOHandler.initIOHandler();
    }

    /**
     * As long as the user want to play we keep going
     * When a game finish/user wants to exit we end up here.
     * */
    public void runLoop(){
        char input;
        do {
            input = startGame();
            if(evaluateGameMode(input)){
                gameMode.addPlayers();
                gameMode.setBoard();
                gameMode.setMarkersToWin();
                gameMode.welcomePlayers();
                gameMode.run();
            }
        }while(!exit(input));
    }

    char startGame(){
        return evaluateInput(IOHandler.printGameMenu());
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
