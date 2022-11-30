package com.example.chessclock;

public class Preset {
    int time1;
    int time2;
    int increment;

    public Preset(){
        this.time1 = 200;
        this.time2 = 200;
        this.increment = 0;
    }

    public Preset(int time1, int time2, int increment){
        this.time1 = time1;
        this.time2 = time2;
        this.increment = increment;
    }

    public int getTime1() {
        return time1;
    }

    public void setTime1(int time1) {
        this.time1 = time1;
    }

    public int getTime2() {
        return time2;
    }

    public void setTime2(int time2) {
        this.time2 = time2;
    }

    public int getIncrement() {
        return increment;
    }

    public void setIncrement(int increment) {
        this.increment = increment;
    }
}
