package com.example.chessclock;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

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
    ActivityResultLauncher<Intent> newGame;
    ActivityResultLauncher<Intent> favorites;
    Preset preset;
    Button button1;
    Button button2;
    boolean first = true;
    MutableLiveData<Boolean> j1enable = new MutableLiveData<>();
    MutableLiveData<Boolean> j2enable = new MutableLiveData<>();
    MutableLiveData<String> j1text = new MutableLiveData<>();
    MutableLiveData<String> j2text = new MutableLiveData<>();
    Timer myTimer1 = new Timer();
    Timer myTimer2 = new Timer();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        j1enable.observe(this, button1::setEnabled);
        j2enable.observe(this, button2::setEnabled);
        j1text.observe(this, button1::setText);
        j2text.observe(this, button2::setText);
        preset = new Preset();
        button1.setText(String.valueOf(((preset.getTime1()) - (preset.getTime1() % 60)) / 60) + " m " + String.valueOf(preset.getTime1() % 60) + " s");
        button2.setText(String.valueOf(((preset.getTime2()) - (preset.getTime2() % 60)) / 60) + " m " + String.valueOf(preset.getTime2() % 60) + " s");

        newGame = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    Intent intent = result.getData();
                    if (intent == null) return;
                    preset = (Preset)intent.getSerializableExtra("Preset");
                    button1.setText(String.valueOf(((preset.getTime1()) - (preset.getTime1() % 60)) / 60) + " m " + String.valueOf(preset.getTime1() % 60) + " s");
                    button2.setText(String.valueOf(((preset.getTime2()) - (preset.getTime2() % 60)) / 60) + " m " + String.valueOf(preset.getTime2() % 60) + " s");
                    myTimer1.cancel();
                    myTimer2.cancel();
                    button1.setEnabled(true);
                    button2.setEnabled(true);
                    first = true;
                }
        );
        favorites = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    Intent intent = result.getData();
                    if (intent == null) return;
                    preset = (Preset)intent.getSerializableExtra("Preset");
                    button1.setText(String.valueOf(((preset.getTime1()) - (preset.getTime1() % 60)) / 60) + " m " + String.valueOf(preset.getTime1() % 60) + " s");
                    button2.setText(String.valueOf(((preset.getTime2()) - (preset.getTime2() % 60)) / 60) + " m " + String.valueOf(preset.getTime2() % 60) + " s");
                    myTimer1.cancel();
                    myTimer2.cancel();
                    button1.setEnabled(true);
                    button2.setEnabled(true);
                    first = true;
                }
        );
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
            newGame.launch(intent);
        }
        else if (item.getItemId() == R.id.newGame){

            Intent intent = new Intent(this, NouvellePartie.class);
            intent.putExtra("origine", "main");
            newGame.launch(intent);
        }
        else if(item.getItemId() == R.id.reset){
            preset.setTime1(300);
            Button button1 = findViewById(R.id.button1);
            button1.setText(String.valueOf(((preset.getTime1()) - (preset.getTime1() % 60)) / 60) + " m " + String.valueOf(preset.getTime1() % 60) + " s");
            preset.setTime2(300);
            Button button2 = findViewById(R.id.button2);
            button2.setText(String.valueOf(((preset.getTime2()) - (preset.getTime2() % 60)) / 60) + " m " + String.valueOf(preset.getTime2() % 60) + " s");
            myTimer1.cancel();
            myTimer2.cancel();
            button1.setEnabled(true);
            button2.setEnabled(true);
            first = true;
        }
        return super.onOptionsItemSelected(item);
    }


    boolean j1Win = false;
    boolean j2Win = false;

    public void ClickJ1(View view)
    {
        myTimer1.cancel();
        myTimer2 = new Timer();
        if(j1Win == false && j2Win == false)
        {
            if (first == true)
            {
                preset.setTime1(preset.getTime1());
                first = false;
            }
            else
            {
                preset.setTime1(preset.getTime1() + preset.getIncrement1());
            }
            button1.setText(String.valueOf(((preset.getTime1()) - (preset.getTime1() % 60)) / 60) + " m " + String.valueOf(preset.getTime1() % 60) + " s");
            TimerTask task = new TimerTask() {
                @Override
                public void run() {
                    preset.setTime2(preset.getTime2() - 1);
                    button2.setText(String.valueOf(((preset.getTime2()) - (preset.getTime2() % 60)) / 60) + " m " + String.valueOf(preset.getTime2() % 60) + " s");
                    if (preset.getTime2() == 0)
                    {
                        j1Win = true;
                        ClickJ2(button2);
                    }
                }
            };
            myTimer2.scheduleAtFixedRate(task, 1000, 1000);
        }
        else
        {
            j1enable.postValue(false);
            j2enable.postValue(false);
            myTimer2.cancel();
            myTimer1.cancel();
            if(j1Win == true)
            {
                j1text.postValue("YOU WIN!");
                j2text.postValue("LOSER HHAHAHAHAA");
            }
            if(j2Win == true)
            {
                j2text.postValue("YOU WIN!");
                j1text.postValue("LOSER HHAHAHAHAA");
            }
        }
        j2enable.postValue(true);
        j1enable.postValue(false);
    }

    public void ClickJ2(View view)
    {

        myTimer2.cancel();
        myTimer1 = new Timer();
        if(j1Win == false && j2Win == false)
        {
            if (first == true)
            {
                preset.setTime2(preset.getTime2());
                first = false;
            }
            else
            {
                preset.setTime2(preset.getTime2() + preset.getIncrement2());
            }
            button2.setText(String.valueOf(((preset.getTime2()) - (preset.getTime2() % 60)) / 60) + " m " + String.valueOf(preset.getTime2() % 60) + " s");
            TimerTask task  = new TimerTask() {
                @Override
                public void run() {
                    preset.setTime1(preset.getTime1() - 1);
                    button1.setText(String.valueOf(((preset.getTime1()) - (preset.getTime1() % 60)) / 60) + " m " + String.valueOf(preset.getTime1() % 60) + " s");
                    if(preset.getTime1() == 0)
                    {
                        j1Win = true;
                        ClickJ1(button1);
                    }
                }
            };
            myTimer1.scheduleAtFixedRate(task, 1000, 1000);
        }
        else
        {
            j1enable.postValue(false);
            j2enable.postValue(false);
            myTimer2.cancel();
            myTimer1.cancel();
            if(j1Win == true)
            {
                j1text.postValue("YOU WIN!");
                j2text.postValue("LOSER HAHAHAHAA");
            }
            if(j2Win == true)
            {
                j2text.postValue("YOU WIN!");
                j1text.postValue("LOSER HAHAHAHAA");
            }
        }
        j1enable.postValue(true);
        j2enable.postValue(false);
    }
}