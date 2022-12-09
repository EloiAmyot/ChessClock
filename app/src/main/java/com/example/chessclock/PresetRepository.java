package com.example.chessclock;


import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.Executors;

/**
 * Le repository est le lien entre vos données et votre application. Souvent c'est utile si vous
 * avez une base de données et utilisez une base de données sur Internet.
 * Pour cet exemple, un repository n'est pas nécessaire, il serait suffisant de mettre directement
 * ces fonctions dans le ViewModel.
 * Le Repository sert à mettre une couche d'abstraction entre le ViewModel et vos différentes
 * sources de données.
 */
public class PresetRepository {

    private PresetDao presetDao;
    private LiveData<List<Preset>> data; // Le livedata permet d'observe quand la base de données change

    public PresetRepository(Application application) {
        PresetDatabase database = PresetDatabase.getDatabase(application); // Pour avoir accès  à la base de donnees
        presetDao = database.presetDao(); // On interragit avec la base de données avec le DAO (data access object)
        data = presetDao.getAll();
    }

    public LiveData<List<Preset>> getAll() {
        return data;
    }

    /**
     Pour modifier la base de données, il faut le faire dans un autre thread, on utilise donc la
     classe Executors
     */
    public void insert(Preset preset) {
        Executors.newSingleThreadExecutor().execute(() -> {
            presetDao.insert(preset);
        });
    }

    public void delete(Preset preset) {
        Executors.newSingleThreadExecutor().execute(() -> {
            presetDao.delete(preset);
        });
    }

}
