package com.example.airlinesapp.views.adapters;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.graphics.Color;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.airlinesapp.R;
import com.example.airlinesapp.Utils.SnackBarUtils;
import com.example.airlinesapp.datamodel.models.Airline;
import com.example.airlinesapp.datamodel.models.FavoriteAirlines;
import com.example.airlinesapp.datamodel.room.AppDataBase;
import com.example.airlinesapp.viewmodel.FavoriteAirlinesViewModel;

import java.util.ArrayList;

public class AirlinesAdapter extends RecyclerView.Adapter<AirlinesAdapter.AirlinesViewHolder> {

    private ArrayList<Airline> airlinesList;
    private Context context;
    RecyclerView rv;
    private AirlinesViewHolder.ItemClickListener onItemClickListener;
    private AppDataBase db;
    FavoriteAirlinesViewModel favoriteAirlinesViewModel;

    public String[] mColors = {"#3F51B5", "#FF9800", "#009688", "#673AB7", "#ff0000", "#fffa00",
            "#6aff00", "#460684", "#a800ff"};

    public AirlinesAdapter(ArrayList<Airline> airlinesList, Context context, RecyclerView rv) {
        this.airlinesList = airlinesList;
        this.context = context;
        this.rv = rv;

    }

    public void setItemClickListener(AirlinesViewHolder.ItemClickListener clickListener) {
        onItemClickListener = clickListener;
    }


    @Override
    public AirlinesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.airline_item_layout, parent, false);
        return new AirlinesViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(AirlinesViewHolder holder, int position) {


        Airline airline = airlinesList.get(position);
        holder.airlineName.setText(airline.getName());
        holder.airlineNumber.setText(airline.getPhone());
        holder.viewcolor.setBackgroundColor(Color.parseColor(mColors[position % 9]));
        holder.itemView.setOnClickListener(v -> onItemClickListener.onItemClick(airline));

        holder.favoriteIV.setTag("notFavourite");
        holder.favoriteIV.setOnClickListener(v -> {
            if (holder.favoriteIV.getTag().equals("notFavourite")) {
                favorite(airline);
                holder.favoriteIV.setTag("favourite");
                SnackBarUtils.SnackBar(rv, "Saved Successfully..");
            } else {
                SnackBarUtils.SnackBar(rv, "Already Saved");

            }
        });
    }

    private void favorite(Airline airline) {


        favoriteAirlinesViewModel = ViewModelProviders.of((FragmentActivity) context).get(FavoriteAirlinesViewModel.class);

        FavoriteAirlines favoriteAirline = new FavoriteAirlines(
                airline.getCode(),
                airline.getDefaultName(),
                airline.getLogoURL(),
                airline.getName(),
                airline.getPhone(),
                airline.getSite(),
                airline.getUsName());
        favoriteAirlinesViewModel.insert(favoriteAirline);

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

    public static class AirlinesViewHolder extends RecyclerView.ViewHolder {
        TextView airlineName, airlineNumber;
        ImageView airlineIV, favoriteIV;
        View viewcolor;

        AirlinesViewHolder(View view) {
            super(view);
            viewcolor = view.findViewById(R.id.view);
            airlineName = view.findViewById(R.id.aireline_TV);
            airlineIV = view.findViewById(R.id.aireline_IV);
            airlineNumber = view.findViewById(R.id.airelineNumber_TV);
            favoriteIV = view.findViewById(R.id.favorite_IV);
        }

        public interface ItemClickListener {
            void onItemClick(Airline airline);
        }

    }


}