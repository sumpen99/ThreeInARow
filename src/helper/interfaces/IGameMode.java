package helper.interfaces;

public interface IGameMode {
    void addPlayers();
    void setBoard();
    boolean validName(String name);
    boolean validBoardSize(int size);
    void setPlayerNames(String nameOne,String nameTwo);
    void drawBoard();
    boolean runGame();
}
