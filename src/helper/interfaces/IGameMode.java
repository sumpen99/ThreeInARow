package helper.interfaces;

import helper.struct.BoardPosition;

public interface IGameMode {
    void addPlayers();
    void setBoard();
    boolean validName(String name);
    boolean validBoardSize(int size);
    boolean validBoardPosition(String pos, BoardPosition lastPos);
    void setPlayerNames(String nameOne,String nameTwo);
    void drawBoard();
    boolean runGame();
}
