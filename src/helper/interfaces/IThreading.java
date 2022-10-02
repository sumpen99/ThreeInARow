package helper.interfaces;

/**
 * Interface for classes who want run functions in a separate thread
 * */
public interface IThreading {
    void heavyDuty();
    void startLoop();
    void closeLoop();
}
