package helper.player;

public abstract class GamePlayer {
    public String name;
    public int winStreak,marker;
    public GamePlayer(String sName,int iMarker){
        name = sName;
        marker = iMarker;
    }
}
