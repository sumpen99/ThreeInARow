package helper.struct;

import helper.player.GamePlayer;

public class GameInfo {
    public SMTimer timer;
    public boolean quit,winner;
    public float runningTime;
    public GamePlayer lastWinner;
    public GamePlayer[] players;
    public int gamesPlayed,upNext;
    public GameInfo(){
        timer = new SMTimer();
    }

    public void reset(){
        upNext = 0;
        quit = false;
        winner = false;
        lastWinner = null;
    }

    public void setWinner(){
        lastWinner = getCurrentPlayer();
        winner = true;
        quit = true;
        lastWinner.winStreak++;
    }

    public GamePlayer getCurrentPlayer(){
        return players[getIndex()];
    }

    public int getIndex(){
        return upNext%2;
    }
}
