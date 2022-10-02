package helper.interfaces;

/**
 * Interface implemented by SinglePlayer/MultiPlayer
 * They have different stuff to do before and during runtime of the game
 * */
public interface IGameMode {
    void addPlayers();
    void setMarkersToWin();
    void runGame();
}
