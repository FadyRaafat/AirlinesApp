package com.example.airlinesapp.viewmodel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.example.airlinesapp.datamodel.models.Airline;

import java.util.ArrayList;

public class AirlinesListViewModel extends ViewModel {

    private MutableLiveData<ArrayList<Airline>> airlines;
    private AirlineRepository airlineRepository;

    public AirlinesListViewModel() {
        airlineRepository = new AirlineRepository();
    }

    public void init() {
        if (this.airlines != null) {
            // ViewModel is created per Fragment so
            // we know the userId won't change
            return;
        }
        airlines = airlineRepository.getAirlinesList();
    }

    public MutableLiveData<ArrayList<Airline>> getAirLinesList() {
        return this.airlines;
    }
}
