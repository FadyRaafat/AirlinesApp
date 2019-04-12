package com.example.airlinesapp.views.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.airlinesapp.R;
import com.example.airlinesapp.datamodel.models.Airline;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

public class AirlineDetailsActivity extends AppCompatActivity {

    TextView airlineName, airlineWebsite, airlinePhone;
    ImageView visitWebsiteIV, callIV;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.airline_details_layout);
        Airline airline = (Airline) getIntent().getSerializableExtra("st");
        airlineName =findViewById(R.id.airlineName_TV);
        airlineWebsite = findViewById(R.id.airlineWebsite_TV);
        airlinePhone = findViewById(R.id.airlinePhone_TV);
        visitWebsiteIV = findViewById(R.id.visitwebsite_IV);
        callIV = findViewById(R.id.call_IV);
        airlineName.setText(airline.getName());
        airlinePhone.setText(airline.getPhone());
        airlineWebsite.setText(airline.getSite());

        visitWebsiteIV.setOnClickListener(v -> {
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse("http://"+airline.getSite()));
            startActivity(i);

        });

        callIV.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse("tel:"+airline.getPhone()));
            startActivity(intent);

        });

    }
}