package helper.ai;
import helper.io.IOHandler;
import helper.matrix.GameBoard;
import helper.struct.BoardPosition;

public class MiniMax extends GameTheory{
    int emptyCell,markerValueAI,markerValueOpp,markersInARow;
    public MiniMax(GameBoard gameBoard, int emptycell, int markervalueai, int markervalueopp, int markersinarow){
        super(gameBoard);
        emptyCell = emptycell;
        markerValueAI = markervalueai;
        markerValueOpp = markervalueopp;
        board = gameBoard;
        markersInARow = markersinarow;
    }

    int evaluate() {
        if(board.findWinningPatter(markerValueAI,markersInARow))return 10;
        if(board.findWinningPatter(markerValueOpp,markersInARow))return -10;
        return 0;
    }

    int miniMax(int depth,boolean isMax){
        int score = evaluate(),row,col;

        if (score == 10)return score-depth;
        if (score == -10)return score+depth;

        if(!board.hasFreeSpace())return 0;

        if(isMax){
            int best = -1000;
            for (row = 0;row< board.rows; row++){
                for (col = 0; col< board.columns;col++){
                    if (board.getValue(row,col)==emptyCell){
                        board.setValue(row,col,markerValueAI);
                        best = Math.max(best,miniMax(depth+1,!isMax));
                        board.setValue(row,col,emptyCell);
                    }
                }
            }
            return best;
        }

        else{
            int best = 1000;
            for (row=0;row< board.rows;row++) {
                for (col = 0;col< board.columns;col++) {
                    if (board.getValue(row,col) == emptyCell) {
                        board.setValue(row,col,markerValueOpp);
                        best = Math.min(best,miniMax(depth+1,!isMax));
                        board.setValue(row,col,emptyCell);
                    }
                }
            }
            return best;
        }
    }

    public int findBestMove(){
        int row = 0,col,bestValue = -1000,moveValue;
        bestPos.row = -1;
        bestPos.col = -1;
        for(;row<board.rows;row++){
            for(col = 0;col < board.columns;col++){
                if(board.getValue(row,col) == emptyCell){
                    board.setValue(row,col,markerValueAI);
                    moveValue = miniMax(0,false);
                    board.setValue(row,col,emptyCell);
                    if(moveValue > bestValue){
                        bestPos.setValue(row,col);
                        bestValue = moveValue;
                    }
                }
            }
        }
        return board.getIndex(bestPos.row, bestPos.col);
    }
}
