package com.example.chessclock;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    Preset preset = new Preset();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.favorites){
            Intent intent = new Intent(this, Favorites.class);
            startActivity(intent);
        }
        else if(item.getItemId() == R.id.reset){
            int time1 = 430;
            Button button1 = findViewById(R.id.button1);
            button1.setText((time1 - (time1 % 60))/60 + " m " + String.valueOf(time1 % 60) + " s");
            int time2 = 42069;
            Button button2 = findViewById(R.id.button2);
            button2.setText((time2 - (time2 % 60))/60 + " m " + String.valueOf(time2 % 60) + " s");
        }
        return super.onOptionsItemSelected(item);
    }

    boolean j1Win = false;
    boolean j2Win = false;
    Timer myTimer1 = new Timer();
    Timer myTimer2 = new Timer();
    Button button1 = findViewById(R.id.button1);
    Button button2 = findViewById(R.id.button2);
    public void ClickJ1(View view)
    {
        myTimer1.cancel();

        if(j1Win == false && j2Win == false) {

            TimerTask task = new TimerTask() {
                @Override
                public void run() {
                    preset.setTime2(preset.getTime2() - 1);
                    button2.setText(String.valueOf(((preset.getTime2()) - (preset.getTime2() % 60)) / 60) + " m " + String.valueOf(preset.getTime2() % 60) + " s");
                    if (preset.getTime2() == 0) {
                        j1Win = true;
                    }
                }
            };
            myTimer2.scheduleAtFixedRate(task, 1000, 1000);
        }
        button2.setEnabled(false);
        button1.setEnabled(true);
    }

    public void ClickJ2(View view)
    {
        myTimer2.cancel();

        if(j1Win == false && j2Win == false)
        {
            TimerTask task  = new TimerTask() {
                @Override
                public void run() {
                    preset.setTime1(preset.getTime1() - 1);
                    button1.setText(String.valueOf(((preset.getTime1()) - (preset.getTime1() % 60)) / 60) + " m " + String.valueOf(preset.getTime1() % 60) + " s");
                    if(preset.getTime2() == 0)
                    {
                        j1Win = true;
                    }
                }
            };
            myTimer1.scheduleAtFixedRate(task, 1000, 1000);
        }
        button1.setEnabled(false);
        button2.setEnabled(true);
    }
}