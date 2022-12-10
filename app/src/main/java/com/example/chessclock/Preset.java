package com.example.chessclock;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class Preset  implements Serializable {
    int time1;
    int time2;
    int increment1;
    int increment2;
    boolean favorite;

    public Preset(){
        this.time1 = 300;
        this.time2 = 300;
        this.increment1 = 2;
        this.increment2 = 2;
        this.favorite = false;
    }

    public Preset(Preset preset){
        this.time1 = preset.getTime1();
        this.time2 = preset.getTime2();
        this.increment1 = preset.getIncrement1();
        this.increment2 = preset.getIncrement2();
        this.favorite = preset.getFavorite();
    }

    public Preset(int time1, int time2, int increment1, int increment2, boolean favorite){
        this.time1 = time1;
        this.time2 = time2;
        this.increment1 = increment1;
        this.increment2 = increment2;
        this.favorite = favorite;
    }

    @PrimaryKey(autoGenerate = true) // Primary key = la variable qui rend votre item unique
    public int id;

    public boolean equals(Preset preset){
        return time1 == preset.time1  && time2 == preset.time2  && increment1 == preset.increment1  && increment2 == preset.increment2  && favorite ==preset.favorite;
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
