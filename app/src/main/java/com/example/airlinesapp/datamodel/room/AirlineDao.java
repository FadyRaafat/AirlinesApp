package com.example.airlinesapp.datamodel.room;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;
import com.example.airlinesapp.datamodel.models.Airline;
import com.example.airlinesapp.datamodel.models.FavoriteAirlines;

import java.util.List;

@Dao
public interface AirlineDao {

    @Insert
    void insertAll(FavoriteAirlines... favoriteAirlines);

    @Update
    void updateAll(FavoriteAirlines... favoriteAirlines);

    @Query("SELECT * FROM favoriteAirlines")
    LiveData<List<FavoriteAirlines>> getAllFavoriteAirlines();

    @Query("SELECT COUNT(1) FROM favoriteAirlines WHERE defaultName= :defaultName")
    boolean ifExists(String defaultName);


    @Delete
    void deleteAll(FavoriteAirlines... favoriteAirlines);
}
