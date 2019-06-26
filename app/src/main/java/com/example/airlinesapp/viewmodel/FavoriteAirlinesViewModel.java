package com.example.airlinesapp.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;
import android.util.Log;

import com.example.airlinesapp.datamodel.models.FavoriteAirlines;
import com.example.airlinesapp.datamodel.room.AirlineDao;
import com.example.airlinesapp.datamodel.room.AppDataBase;

import java.util.List;

public class FavoriteAirlinesViewModel extends AndroidViewModel {

    private String TAG = this.getClass().getSimpleName();
    private AirlineDao airlineDao;
    private AppDataBase appDB;
    private LiveData<List<FavoriteAirlines>> mAllFavoriteAirlines;


    public FavoriteAirlinesViewModel(Application application) {
        super(application);

        appDB = AppDataBase.getDatabase(application);
        airlineDao = appDB.getFavoriteAirlinesDao();
        mAllFavoriteAirlines = airlineDao.getAllFavoriteAirlines();
    }

    public LiveData<List<FavoriteAirlines>> getAllFavoriteAirlines() {
        return mAllFavoriteAirlines;
    }


    @Override
    protected void onCleared() {
        super.onCleared();
        Log.i(TAG, "ViewModel Destroyed");
    }


    public void insert(FavoriteAirlines favoriteAirlines) {
        new InsertAsyncTask(airlineDao).execute(favoriteAirlines);
    }

    public void delete(FavoriteAirlines favoriteAirlines) {
        new DeleteAsyncTask(airlineDao).execute(favoriteAirlines);
    }



    private class OperationsAsyncTask extends AsyncTask<FavoriteAirlines, Void, Void> {

        AirlineDao mAsyncTaskDao;

        OperationsAsyncTask(AirlineDao dao) {
            this.mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(FavoriteAirlines... favoriteAirlines) {
            return null;
        }
    }


    private class InsertAsyncTask extends OperationsAsyncTask {

        InsertAsyncTask(AirlineDao mAirlineDao) {
            super(mAirlineDao);
        }

        @Override
        protected Void doInBackground(FavoriteAirlines... favoriteAirlines) {
            mAsyncTaskDao.insertAll(favoriteAirlines[0]);
            return null;
        }
    }

    private class DeleteAsyncTask extends OperationsAsyncTask {

        DeleteAsyncTask(AirlineDao mAirlineDao) {
            super(mAirlineDao);
        }

        @Override
        protected Void doInBackground(FavoriteAirlines... favoriteAirlines) {
            mAsyncTaskDao.deleteAll(favoriteAirlines[0]);
            return null;
        }
    }


}
