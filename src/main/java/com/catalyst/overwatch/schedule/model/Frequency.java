package com.catalyst.overwatch.schedule.model;

/**
 * 
 * @author sAnnamalai
 *
 */

public enum Frequency {

    ONE_TIME (0),
    ONE_WEEK (7),
    TWO_WEEKS (14),
    THREE_WEEKS (21),
    FOUR_WEEKS (28);

    final int frequencyValue;

    Frequency(int frequencyValue){
        this.frequencyValue = frequencyValue;
    }

    public int getValue(){
        return frequencyValue;
    }
}
