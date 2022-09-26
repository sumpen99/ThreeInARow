package helper.player;

import helper.enums.Token;

public class GamePlayer {
    public String name;
    public int winStreak,marker;
    public GamePlayer(String sName,int iMarker){
        name = sName;
        marker = iMarker;
    }
}
