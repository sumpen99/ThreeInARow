import helper.io.IOHandler;
import helper.struct.SMDateTime;
import program.ThreeInARow;

public class StartUp {
    //https://www.geeksforgeeks.org/search-a-word-in-a-2d-grid-of-characters/
    //https://www.geeksforgeeks.org/rabin-karp-algorithm-for-pattern-searching-in-matrix/
    public static void main(String[] args){
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
