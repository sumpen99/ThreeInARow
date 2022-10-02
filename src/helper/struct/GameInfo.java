package helper.struct;
import helper.player.GamePlayer;
import static helper.methods.CommonMethods.getRandomInt;

/**
 * Small class with everything the Gamemode needs to function
 * */
public class GameInfo {
    public SMTimer timer;
    public boolean quit,winner;
    public float runningTime;
    public GamePlayer lastWinner;
    public GamePlayer[] players;
    public int markersToWin,gamesPlayed,upNext;
    public GameInfo(){
        timer = new SMTimer();
    }

    /**
     * resets the values needed to play a new game
     * **/
    public void reset(){
        upNext = 0;
        quit = false;
        winner = false;
        lastWinner = null;
        for(int i = 0;i<players.length;i++){players[i].resetPlayer();}
    }

    /**
     * Sets the winner and closes the running loop
     * by setting quit to true
     * */
    public void setWinner(){
        lastWinner = getCurrentPlayer();
        winner = true;
        quit = true;
        lastWinner.winStreak++;
    }

    /**
     * shuffles the order of who starts the game
     * */
    public void shuffleDrawOrder(){
        upNext = getRandomInt(10);
    }

    /**
     * returns the current player in charge
     * */
    public GamePlayer getCurrentPlayer(){
        return players[getIndex()];
    }

    /**
     * updates the next player index
     * */
    public void updateNext(){
        upNext++;
    }

    /**
     * keeps the value between zero and 1
     * and returns the value
     * */
    public int getIndex(){
        return upNext%2;
    }
}
