package com.app.appbelajarkomik.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.appbelajarkomik.R;
import com.app.appbelajarkomik.model.ListSearchModel;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

import per.wsj.library.AndRatingBar;

public class rvAdapterSearch extends RecyclerView.Adapter<rvAdapterSearch.ViewHolder> {
    private Context context;
    private ArrayList<ListSearchModel> listSearchModels;

    public rvAdapterSearch(Context context, ArrayList<ListSearchModel> listSearchModels) {
        this.context = context;
        this.listSearchModels = listSearchModels;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_search, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ListSearchModel list = listSearchModels.get(position);
        holder.judul.setText(list.getJudul());
        holder.textRating.setText(list.getRating());
        Glide.with(context).load(list.getGambar())
                .into(holder.gambar);

        float rating = Float.valueOf(list.getRating()) *5/10;

        holder.ratingBar.setRating(rating);

    }

    @Override
    public int getItemCount() {
        return listSearchModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView gambar;
        private TextView judul,textRating;
        private AndRatingBar ratingBar;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            gambar = itemView.findViewById(R.id.gambar);
            judul = itemView.findViewById(R.id.judul);
            textRating = itemView.findViewById(R.id.textRating);
            ratingBar = itemView.findViewById(R.id.ratingBar);
        }
    }
}
