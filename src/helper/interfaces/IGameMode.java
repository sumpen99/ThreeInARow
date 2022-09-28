package helper.interfaces;
import helper.player.GamePlayer;

public interface IGameMode {
    void addPlayers();
    void setBoard();
    void putMarkerOnBoard(int row,int col,int value);
    void putMarkerOnBoard(int index,int value);
    boolean validName(String name);
    boolean validBoardSize(int size);
    void welcomePlayers();
    boolean validBoardPosition(String pos);
    void setKeyValue();
    boolean validKeyValue(int keyValue);
    void setPlayers(GamePlayer p1,GamePlayer p2);
    void drawBoard();
    void run();
    void runGame();
}
