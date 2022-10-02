package helper.ai;
import helper.matrix.GameBoard;

/**
 * https://www.geeksforgeeks.org/minimax-algorithm-in-game-theory-set-3-tic-tac-toe-ai-finding-optimal-move/?ref=rp
 *  Implementation of the minimax algorithm
 *  I take zero credit for this one but atleast the evaluate function is custom
 *  extends gametheory and
 * */
public class MiniMax extends GameTheory{
    public MiniMax(GameBoard gameBoard, int emptycell, int markervalueai, int markervalueopp, int markersinarow,String cmpName){
        super(gameBoard,emptycell,markervalueai,markervalueopp,markersinarow,cmpName);
    }

    /**
     * Searches the matrix for a winning pattern
     * returns 10 if computer wins else -10
     * */
    int evaluate() {
        if(board.findWinningPatter(markerValueAI,markersInARow))return 10;
        if(board.findWinningPatter(markerValueOpp,markersInARow))return -10;
        return 0;
    }

    /**
     * Plays the game recursive as Minimizer/Maximizer
     * until the game is won/grid is full
     * */
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

    /**
     * Calls minimax for every free spot in the matrix
     * and returns the index with best value
     * */
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
