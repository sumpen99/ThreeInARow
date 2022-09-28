import helper.io.IOHandler;
import helper.struct.SMDateTime;
import program.ThreeInARow;

public class StartUp {
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
        IOHandler.removeFile("./resources/files/log/console/console.log");
        IOHandler.testPathToLogFile();
    }

}
