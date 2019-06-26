package com.example.airlinesapp.views.adapters;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.airlinesapp.R;
import com.example.airlinesapp.datamodel.models.Airline;
import com.example.airlinesapp.datamodel.models.FavoriteAirlines;
import com.example.airlinesapp.datamodel.room.AppDataBase;
import com.example.airlinesapp.views.activities.FavoriteAirlinesActivity;

import java.util.ArrayList;
import java.util.List;

public class FavoriteAirlinesAdapter extends RecyclerView.Adapter<FavoriteAirlinesAdapter.FavoriteAirlinesViewHolder> {

    private List<FavoriteAirlines> airlinesList;
    private Context context;
    FavoriteAirlinesActivity mActivity;
    private AppDataBase db;

    public String[] mColors = {"#3F51B5", "#FF9800", "#009688", "#673AB7", "#ff0000", "#fffa00",
            "#6aff00", "#460684", "#a800ff"};

    public FavoriteAirlinesAdapter(FavoriteAirlinesActivity mActivity, List<FavoriteAirlines> airlinesList) {
        this.airlinesList = airlinesList;
        this.mActivity = mActivity;
        notifyDataSetChanged();


    }




    @Override
    public FavoriteAirlinesAdapter.FavoriteAirlinesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.favorite_airline_item_layout, parent, false);
        return new FavoriteAirlinesAdapter.FavoriteAirlinesViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(FavoriteAirlinesAdapter.FavoriteAirlinesViewHolder holder, int position) {


        FavoriteAirlines favairline = airlinesList.get(position);
        holder.airlineName.setText(favairline.getName());
        holder.airlineNumber.setText(favairline.getPhone());
        holder.viewcolor.setBackgroundColor(Color.parseColor(mColors[position % 9]));
        holder.unfavoriteIV.setOnClickListener(v -> {
/*
            mActivity.getDrawable(R.drawable.ic_favorite_border);
*/
            unFavorite(airlinesList.get(position));
        });
    }

    private void unFavorite(FavoriteAirlines favoriteAirlines)
    {
        mActivity.unFavorite(favoriteAirlines);
    }


    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }


    @Override
    public int getItemCount() {
        return airlinesList.size();
    }

    public static class FavoriteAirlinesViewHolder extends RecyclerView.ViewHolder {
        TextView airlineName, airlineNumber;
        ImageView airlineIV, unfavoriteIV;
        View viewcolor;

        FavoriteAirlinesViewHolder(View view) {
            super(view);
            viewcolor = view.findViewById(R.id.view);
            airlineName = view.findViewById(R.id.fav_aireline_TV);
            airlineIV = view.findViewById(R.id.aireline_IV);
            airlineNumber = view.findViewById(R.id.fav_airelineNumber_TV);
            unfavoriteIV = view.findViewById(R.id.fav_favorite_IV);

        }


    }

}
