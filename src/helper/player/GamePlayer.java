package helper.player;

/**
 * Player class
 * */
public class GamePlayer{
    public String name;
    public int winStreak,marker,lastMarkerIndex;
    public GamePlayer(String sName,int iMarker){
        name = sName;
        marker = iMarker;
        lastMarkerIndex = -1;
    }

    public void resetPlayer(){
        lastMarkerIndex = -1;
    }

}
