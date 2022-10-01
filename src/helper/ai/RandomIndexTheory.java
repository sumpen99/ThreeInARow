package helper.ai;
import helper.matrix.GameBoard;

/**
 *  HAHAHA
 *
 * */
public class RandomIndexTheory extends GameTheory {
    public RandomIndexTheory(GameBoard gameBoard, int emptycell, int markervalueai, int markervalueopp, int markersinarow){
        super(gameBoard,emptycell,markervalueai,markervalueopp,markersinarow);
    }

    @Override
    public int findBestMove(){
        return board.lookForNewPosition(markerValueAI,markerValueOpp,markersInARow);
    }
}
