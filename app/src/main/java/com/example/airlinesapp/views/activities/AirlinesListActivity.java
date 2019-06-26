package com.example.airlinesapp.views.activities;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.example.airlinesapp.R;
import com.example.airlinesapp.datamodel.models.Airline;
import com.example.airlinesapp.viewmodel.AirlinesViewModel;
import com.example.airlinesapp.views.adapters.AirlinesAdapter;

import java.util.ArrayList;

public class AirlinesListActivity extends AppCompatActivity implements AirlinesAdapter.AirlinesViewHolder.ItemClickListener {

    private AirlinesViewModel mViewModel;
    private ArrayList<Airline> airlineLIst;
    private RecyclerView recyclerView;
    private AirlinesAdapter mAdapter;
    private FloatingActionButton favoriteFAB;
    ProgressBar PB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.airlines_RV);
        favoriteFAB = findViewById(R.id.favorite_FAB);
        PB = findViewById(R.id.progressBar);
        mViewModel = ViewModelProviders.of(this).get(AirlinesViewModel.class);
        mViewModel.init();
        mViewModel.getAirLinesList().observe(this, airlinesList ->{
            if(airlinesList != null){
                PB.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);
                mAdapter = new AirlinesAdapter(airlinesList, AirlinesListActivity.this, recyclerView);
                mAdapter.setItemClickListener(this);
                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
                recyclerView.setLayoutManager(mLayoutManager);
                recyclerView.setAdapter(mAdapter);

            }
        });

        favoriteFAB.setOnClickListener(v -> {
            Intent i = new Intent(this, FavoriteAirlinesActivity.class);
            startActivity(i);
        });

    }


    @Override
    public void onItemClick(Airline airline) {
        Intent i = new Intent(this, AirlineDetailsActivity.class);
        i.putExtra("st", airline);
        startActivity(i);

    }
}
