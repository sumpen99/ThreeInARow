package helper.threading;
import helper.interfaces.IThreading;

/**
 * Starts a new thread by taking a class object who implements IThreading
 * and executes function heavyduty
 * GameTheory implements IThreading
 * */
public class ThreadHandler{
    public static Thread t1;
    public static void executeNewThread(IThreading obj){
        TBF tbf = new TBF(obj);
        t1 = new Thread(tbf);
        t1.start();
    }
}

class TBF implements Runnable{
    IThreading object;
    public TBF(IThreading obj){
        object = obj;
    }

    public void run(){
        object.startLoop();
        object.heavyDuty();
        object.closeLoop();
    }
}
