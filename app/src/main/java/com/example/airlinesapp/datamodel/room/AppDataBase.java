package com.example.airlinesapp.datamodel.room;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import com.example.airlinesapp.datamodel.models.FavoriteAirlines;

@Database(entities = {FavoriteAirlines.class}, version = 1)
public abstract class AppDataBase extends RoomDatabase {

    public static final String DB_NAME = "favoriteairlines";

    public abstract AirlineDao getFavoriteAirlinesDao();

    private static volatile AppDataBase INSTANCE;

    public static AppDataBase getDatabase(Context context) {
        if (INSTANCE == null) {
            synchronized (AppDataBase.class) {

                INSTANCE =
                        Room.databaseBuilder(context.getApplicationContext(),
                                AppDataBase.class, DB_NAME)
                                .allowMainThreadQueries().build();
            }
        }
        return INSTANCE;
    }



}
