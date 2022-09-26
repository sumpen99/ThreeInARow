import helper.io.IOHandler;
import program.ThreeInARow;

public class StartUp {
    //https://www.geeksforgeeks.org/search-a-word-in-a-2d-grid-of-characters/
    //https://www.geeksforgeeks.org/rabin-karp-algorithm-for-pattern-searching-in-matrix/
    public static void main(String[] args){
        IOHandler.removeFilesFromFolder("./resources/files/log/gc");
        IOHandler.removeFile("./resources/files/log/error/error.log");
        ThreeInARow program = new ThreeInARow();
        program.runLoop();
    }

}
