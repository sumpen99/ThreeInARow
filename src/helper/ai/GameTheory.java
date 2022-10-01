package helper.ai;
import helper.interfaces.IGameTheory;
import helper.matrix.GameBoard;
import helper.struct.BoardPosition;

public abstract class GameTheory implements IGameTheory {
    GameBoard board;
    BoardPosition bestPos;
    int emptyCell,markerValueAI,markerValueOpp,markersInARow;
    public GameTheory(GameBoard gameBoard,int emptycell, int markervalueai, int markervalueopp, int markersinarow){
        board = gameBoard;
        bestPos = new BoardPosition();
        emptyCell = emptycell;
        markerValueAI = markervalueai;
        markerValueOpp = markervalueopp;
        markersInARow = markersinarow;
    }

}
