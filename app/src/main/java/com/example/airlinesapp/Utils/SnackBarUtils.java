package com.example.airlinesapp.Utils;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;

import com.example.airlinesapp.R;

public class SnackBarUtils {

    public static void SnackBar(RecyclerView rv , String message ){
        Snackbar snack = Snackbar.make(rv,message, 800);
        snack.show();

    }


}
