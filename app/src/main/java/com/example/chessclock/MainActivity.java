package com.example.chessclock;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    int time1 = 200;
    int time2 = 200;
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
        if(item.getItemId() == R.id.settings){
            Intent intent = new Intent(this, Settings.class);
            startActivity(intent);
        }
        if(item.getItemId() == R.id.reset){
            time1 = 120;
            Button button1 = findViewById(R.id.button1);
            button1.setText(String.valueOf(time1) + " s");
            time2 = 120;
            Button button2 = findViewById(R.id.button2);
            button2.setText(String.valueOf(time2) + " s");
        }
        return super.onOptionsItemSelected(item);
    }
}