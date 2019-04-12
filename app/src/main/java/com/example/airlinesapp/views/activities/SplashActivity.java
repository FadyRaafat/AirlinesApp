package com.example.airlinesapp.views.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.airlinesapp.R;
import com.example.airlinesapp.Utils.ThreadUtils;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_layout);
        ThreadUtils.run(() -> {
            Intent i = new Intent(SplashActivity.this, AirlinesListActivity.class);
            startActivity(i);
            finish();
        }).after(3000).exec();

    }

}
