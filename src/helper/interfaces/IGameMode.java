package helper.interfaces;

/**
 * Interface implemented by SinglePlayer/MultiPlayer
 * */
public interface IGameMode {
    void addPlayers();
    void setMarkersToWin();
    void runGame();
}
