import program.ThreeInARow;
/**
 * Program of Three-In-A-Row
 * */
public class StartUp {
    public static void main(String[] args){
        ThreeInARow program;
        program = new ThreeInARow();
        program.setGlobal();
        program.runLoop();
    }

}
