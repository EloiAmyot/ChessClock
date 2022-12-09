package com.example.chessclock;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Le ViewModel qu'on va utiliser pour avoir accès aux données
 * Puisqu'un activity perd toutes ses variables lorsqu'elle n'est plus affichée (ou même lorsqu'on
 * change l'affichage de sens), ça nous prend une place pour stocker ce qu'on a besoin. C'est à ça
 * que sert le ViewModel. Cependant, même le ViewModel est vidé lorsque vous fermez l'application.
 * Pour ça il faut utiliser une base de données.
 * Ici on l'utilise juste pour accéder à la base de données.
 * Vous pouvez extend AndroidViewModel ou juste ViewModel si vous n'avez pas besoin de "contexte"
 */
public class MyViewModel extends AndroidViewModel {

    PresetRepository repository; // Accès à la base de données

    public MyViewModel(@NonNull Application application) {
        super(application);
        repository = new PresetRepository(application);
    }

    /**
     * @return Live data for all texts
     */
    public LiveData<List<Preset>> getData() {
        return repository.getAll();
    }

    /**
     * @param string Name of the text to add to the database
     */
    public void addData(Preset preset){
        repository.insert(preset);
    }

    /**
     * @param text Text to remove from the database
     */
    public void removeData(Preset preset) {
        repository.delete(preset);
    }

}
