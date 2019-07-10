package com.example.airlinesapp.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;
import android.util.Log;

import com.example.airlinesapp.datamodel.models.FavoriteAirlines;
import com.example.airlinesapp.datamodel.repository.FavoriteAirlinesRepository;
import com.example.airlinesapp.datamodel.room.AirlineDao;
import com.example.airlinesapp.datamodel.room.AppDataBase;

import java.util.List;

public class FavoriteAirlinesViewModel extends AndroidViewModel {

    private FavoriteAirlinesRepository mRepository;
    // Using LiveData and caching what getAlphabetizedWords returns has several benefits:
    // - We can put an observer on the data (instead of polling for changes) and only update the
    //   the UI when the data actually changes.
    // - Repository is completely separated from the UI through the ViewModel.
    private LiveData<List<FavoriteAirlines>> mAllFavoriteAirlines;

    public FavoriteAirlinesViewModel(Application application) {
        super(application);
        mRepository = new FavoriteAirlinesRepository(application);
        mAllFavoriteAirlines = mRepository.getAllFavoriteAirlines();
    }

    public LiveData<List<FavoriteAirlines>> getAllFavoriteAirlines() {
        return mAllFavoriteAirlines;
    }

    public void insert(FavoriteAirlines favoriteAirlines) {
        mRepository.insert(favoriteAirlines);
    }

    public boolean ifExists(String defaultName){
        return mRepository.ifExists(defaultName);
    }
    public void delete(FavoriteAirlines favoriteAirlines) {
        mRepository.delete(favoriteAirlines);
    }




}
