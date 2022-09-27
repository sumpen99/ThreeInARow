import helper.io.IOHandler;
import helper.matrix.GameBoard;
import helper.struct.SMDateTime;
import program.ThreeInARow;

public class StartUp {
    public static void main(String[] args){
        GameBoard board = new GameBoard(10);


        /*int[] test = new int[]{
        1,0,0,0,0,0,0,0,0,0,
        0,1,0,0,0,0,0,0,0,0,
        0,0,1,0,0,0,0,0,0,0,
        0,0,0,0,0,0,0,0,0,0,
        0,0,0,0,1,0,0,0,0,0,
        0,0,0,0,0,0,0,0,0,0,
        0,0,0,0,1,0,0,0,0,0,
        0,0,0,0,0,0,0,0,0,0,
        0,0,0,0,0,0,0,0,0,0,
        0,0,0,0,0,0,0,0,0,0
        };
        board.m = test;

        IOHandler.printBoolean(board.findPatter(1));*/

        ThreeInARow program;
        setGlobal();
        program = new ThreeInARow();
        program.runLoop();
    }

    static void setGlobal(){
        SMDateTime.initSMDateTime();
        IOHandler.initIOHandler();
        IOHandler.removeFilesFromFolder("./resources/files/log/gc");
        IOHandler.removeFile("./resources/files/log/error/error.log");
        IOHandler.testPathToLogFile();
    }

}
