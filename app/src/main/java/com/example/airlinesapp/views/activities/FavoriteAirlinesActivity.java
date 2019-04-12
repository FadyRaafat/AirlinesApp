package com.example.airlinesapp.views.activities;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.example.airlinesapp.R;
import com.example.airlinesapp.datamodel.models.FavoriteAirlines;
import com.example.airlinesapp.datamodel.room.AppDataBase;
import com.example.airlinesapp.views.adapters.FavoriteAirlinesAdapter;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class FavoriteAirlinesActivity extends AppCompatActivity {

    private RecyclerView favoriteRV;
    FavoriteAirlinesAdapter mAdapter;
    private AppDataBase db;
    TextView emptyList;
    List<FavoriteAirlines> favoriteAirlinesList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.favorite_airlines_layout);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        emptyList = findViewById(R.id.emptyList_TV);
        db = AppDataBase.getDatabase(this);
        favoriteRV = findViewById(R.id.favorite_airlines_RV);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        favoriteRV.setLayoutManager(mLayoutManager);
        loadFavorites();

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }


    private void loadFavorites() {
        new AsyncTask<Void, Void, List<FavoriteAirlines>>() {
            @Override
            protected List doInBackground(Void... params) {
                return db.getFavoriteAirlinesDao().getAllFavoriteAirlines();
            }

            @Override
            protected void onPostExecute(List favorites) {
                if(favorites.size() != 0)
                {
                    favoriteRV.setVisibility(View.VISIBLE);
                    emptyList.setVisibility(View.GONE);
                    if (favoriteAirlinesList != null)
                        favoriteAirlinesList.clear();

                    favoriteAirlinesList = favorites;
                    mAdapter = new FavoriteAirlinesAdapter(FavoriteAirlinesActivity.this, favoriteAirlinesList);
                    favoriteRV.setAdapter(mAdapter);
                }
                else {
                    favoriteRV.setVisibility(View.GONE);
                    emptyList.setVisibility(View.VISIBLE);

                }

            }
        }.execute();
    }

    public void unFavorite(FavoriteAirlines favoriteAirlines) {
        db.getFavoriteAirlinesDao().deleteAll(favoriteAirlines);
        favoriteAirlinesList.remove(favoriteAirlines);
        mAdapter.notifyDataSetChanged();
    }



}