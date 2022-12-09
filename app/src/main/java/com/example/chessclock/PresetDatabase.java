package com.example.chessclock;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

/**
 * La base de données
 * Mettre les différentes classes qui peuvent être dans votre base de données dans une liste dans
 * entities.
 */
@Database(entities = {Preset.class}, version=1)
public abstract class PresetDatabase extends RoomDatabase {
    public abstract PresetDao presetDao();
    private static PresetDatabase instance;

    /**
     * Fonction static pour avoir une seule base de données. Vous pouvez pas mal copier cette partie,
     * mais n'oubliez pas de changer le nom de la base de données.
     * @param context le contexte de l'application
     * @return la base de données
     */
    public static PresetDatabase getDatabase(final Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                            PresetDatabase.class, "text-database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}
