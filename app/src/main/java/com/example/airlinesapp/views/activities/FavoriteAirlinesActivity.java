package com.example.airlinesapp.views.activities;

import android.arch.lifecycle.ViewModelProviders;
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
import com.example.airlinesapp.viewmodel.FavoriteAirlinesViewModel;
import com.example.airlinesapp.views.adapters.FavoriteAirlinesAdapter;

import java.util.List;

public class FavoriteAirlinesActivity extends AppCompatActivity {

    private RecyclerView favoriteRV;
    FavoriteAirlinesAdapter mAdapter;
    private AppDataBase db;
    TextView emptyList;
    List<FavoriteAirlines> favoriteAirlinesList;
    private FavoriteAirlinesViewModel favoriteAirlinesViewModel;


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

        favoriteAirlinesViewModel = ViewModelProviders.of(this).get(FavoriteAirlinesViewModel.class);
        favoriteAirlinesViewModel.getAllFavoriteAirlines().observe(this, favoriteAirlines ->
        {
            if (favoriteAirlines.size() != 0) {
                favoriteRV.setVisibility(View.VISIBLE);
                emptyList.setVisibility(View.GONE);
                mAdapter = new FavoriteAirlinesAdapter(this, favoriteAirlines);
                favoriteRV.setAdapter(mAdapter);
            }
            else{
                favoriteRV.setVisibility(View.GONE);
                emptyList.setVisibility(View.VISIBLE);
            }
        });

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }


    public void unFavorite(FavoriteAirlines favoriteAirlines) {
        favoriteAirlinesViewModel.delete(favoriteAirlines);
        mAdapter.notifyDataSetChanged();
    }


}