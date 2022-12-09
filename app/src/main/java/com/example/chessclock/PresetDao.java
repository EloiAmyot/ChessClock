package com.example.chessclock;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

/**
 * Le DAO (database access object) permet d'accéder à la base de données (nice).
 * Vous devez définir des fonctions avec des décorateurs (les @).
 */
@Dao
public interface PresetDao {

    /**
     * Accéder à toutes les données.
     * Vous pouvez faire des query SQL différentes pour accéder à d'autres données
     * @return Le LiveData d'une liste qui contient tous les objets
     */
    @Query("SELECT * from preset")
    LiveData<List<Preset>> getAll();

    /**
     * Ajoute un text (ou une liste) dans la base de données
     * @param texts text à insérer dans la base de données
     */
    @Insert
    void insert(Preset... presets);

    /**
     * Supprime un élément de la base de données
     * @param text le Text à enlever
     */
    @Delete
    void delete(Preset preset);
}
