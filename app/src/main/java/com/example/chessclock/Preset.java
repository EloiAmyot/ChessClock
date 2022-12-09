package com.example.chessclock;



public class Preset {
    int time1;
    int time2;
    int increment1;
    int increment2;
    boolean favorite;


    public int id;

    public Preset(){
        this.time1 = 15;
        this.time2 = 15;
        this.increment1 = 2;
        this.increment2 = 2;
        this.favorite = false;
    }

    public Preset(int time1, int time2, int increment1, int increment2, boolean favorite){
        this.time1 = time1;
        this.time2 = time2;
        this.increment1 = increment1;
        this.increment2 = increment2;
        this.favorite = favorite;
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

    public int getIncrement1() {
        return increment1;
    }

    public void setIncrement1(int increment) {
        this.increment1 = increment1;
    }
    public int getIncrement2() {return increment2;}

    public void setIncrement2 (int increment2)
    {
        this.increment2 = increment2;
    }

    public boolean getFavorite()
    {
        return favorite;
    }

    public void setFavorite(boolean favorite)
    {
        this.favorite = favorite;
    }
}
