package helper.struct;

/**
 * Struct used by FollowTheory to find a new position for
 * the computer to place a marker
 * */
public class MinMaxPos {
    public int index,valueHigh;

    public void reset(){
        index = 0;
        valueHigh = -1;
    }

}
