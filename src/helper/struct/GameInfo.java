package helper.struct;

import helper.player.GamePlayer;

public class GameInfo {
    public SMTimer timer;
    public boolean quit,winner;
    public float runningTime;
    public GamePlayer lastWinner;
    public GamePlayer[] players;
    public int gamesPlayed;
    public GameInfo(){
        timer = new SMTimer();
    }
}
