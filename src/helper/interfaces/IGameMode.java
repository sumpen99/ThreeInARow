package helper.interfaces;

import helper.struct.BoardPosition;

public interface IGameMode {
    void addPlayers();
    void setBoard();
    void putMarkerOnBoard(int row,int col,int value);
    void putMarkerOnBoard(int index,int value);
    void updateNextIndex();
    boolean validName(String name);
    boolean validBoardSize(int size);
    void welcomePlayers();
    boolean validBoardPosition(String pos, BoardPosition lastPos);
    void setPlayerNames(String nameOne,String nameTwo);
    void setPlayerList();
    void drawBoard();
    boolean runGame();
}
