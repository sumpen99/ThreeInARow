package helper.ai;
import helper.interfaces.IGameTheory;
import helper.matrix.GameBoard;
import helper.struct.BoardPosition;

public abstract class GameTheory implements IGameTheory {
    GameBoard board;
    BoardPosition bestPos;

    public GameTheory(GameBoard gameBoard){
        board = gameBoard;
        bestPos = new BoardPosition();
    }

    public void reset(){}

}
