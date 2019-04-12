package com.example.airlinesapp.viewmodel;


import android.arch.lifecycle.MutableLiveData;

import com.example.airlinesapp.datamodel.models.Airline;
import com.example.airlinesapp.datamodel.services.AirlinesService;
import com.example.airlinesapp.datamodel.services.ServiceBuilder;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AirlineRepository {

    public MutableLiveData<ArrayList<Airline>> getAirlinesList() {
        final MutableLiveData<ArrayList<Airline>> airlinesList = new MutableLiveData<>();
        AirlinesService airlinesService = ServiceBuilder.buildService(AirlinesService.class);
        Call<ArrayList<Airline>> call = airlinesService.getAirelinesList();
        call.enqueue(new Callback<ArrayList<Airline>>() {
            @Override
            public void onResponse(Call<ArrayList<Airline>> request, Response<ArrayList<Airline>> response) {
                airlinesList.setValue(response.body());
            }

            @Override
            public void onFailure(Call<ArrayList<Airline>> request, Throwable t) {
            }
        });

        return airlinesList;

    }
}
