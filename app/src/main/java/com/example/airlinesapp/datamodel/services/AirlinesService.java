package com.example.airlinesapp.datamodel.services;

import com.example.airlinesapp.datamodel.models.Airline;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface AirlinesService {

    @GET("airlines")
    Call<ArrayList<Airline>> getAirelinesList();

}
