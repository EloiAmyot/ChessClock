package com.example.chessclock;

import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

public class NouvellePartie extends AppCompatActivity {

    Preset preset;
    MyViewModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nouvelle_partie);
        preset = new Preset();
        model = new ViewModelProvider(this).get(MyViewModel.class);
        Intent intent  = new Intent(getIntent());
        Button boutton;
        boutton =findViewById(R.id.boutton);
        RadioButton radiobutton = findViewById(R.id.radioButton);
        if(intent.getStringExtra("origine").equals("nouveau_preset")){
            radiobutton.setEnabled(false);
            radiobutton.setChecked(true);
            boutton.setText("Confirmer");
            boutton.setOnClickListener(view -> {
                EditText time1 = findViewById(R.id.temps1);
                EditText increment1 = findViewById(R.id.newIncrement1);
                EditText time2 = findViewById(R.id.temps2);
                EditText increment2 = findViewById(R.id.newIncrement2);
                preset.setFavorite(true);
                if (!time1.getText().toString().equals("") && !increment1.getText().toString().equals("") && !time2.getText().toString().equals("") && !increment2.getText().toString().equals("")) {

                    preset.setTime1(Integer.parseInt(time1.getText().toString()));
                    preset.setIncrement1(Integer.parseInt(increment1.getText().toString()));
                    preset.setTime2(Integer.parseInt(time2.getText().toString()));
                    preset.setIncrement2(Integer.parseInt(increment2.getText().toString()));
                }
                model.addData(preset);
                Intent allo = new Intent();
                allo.putExtra("Preset", preset);
                this.setResult(this.RESULT_OK, allo);
                this.finish();
            });
        }
        else{
            boutton.setOnClickListener(view -> {
                EditText time1 = findViewById(R.id.temps1);
                EditText increment1 = findViewById(R.id.newIncrement1);
                EditText time2 = findViewById(R.id.temps2);
                EditText increment2 = findViewById(R.id.newIncrement2);
                if (!time1.getText().toString().equals("") && !increment1.getText().toString().equals("") && !time2.getText().toString().equals("") && !increment2.getText().toString().equals("")) {
                    preset.setTime1(Integer.parseInt(time1.getText().toString()));
                    preset.setIncrement1(Integer.parseInt(increment1.getText().toString()));
                    preset.setTime2(Integer.parseInt(time2.getText().toString()));
                    preset.setIncrement2(Integer.parseInt(increment2.getText().toString()));
                }
                if (radiobutton.isChecked()){
                    preset.setFavorite(true);
                    model.addData(preset);
                }
                Intent returnIntent = new Intent();
                returnIntent.putExtra("Preset", preset);
                setResult(RESULT_OK, returnIntent);
                finish();
            });
        }

    }
}