package com.app.appbelajarkomik.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.appbelajarkomik.model.ListTrendingModel;
import com.app.appbelajarkomik.R;
import com.app.appbelajarkomik.activity.DetailActivity;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class rvAdapterTrend extends RecyclerView.Adapter<rvAdapterTrend.ViewHolder> {
    private Context context;
    private ArrayList<ListTrendingModel> listTrendingModelArrayList;

    public rvAdapterTrend(Context context, ArrayList<ListTrendingModel> listTrendingModelArrayList) {
        this.context = context;
        this.listTrendingModelArrayList = listTrendingModelArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_trend, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull rvAdapterTrend.ViewHolder holder, int position) {
        ListTrendingModel list =listTrendingModelArrayList.get(position);
        holder.judul.setText(list.getJudul());
        holder.chapter.setText(list.getChapter());
        Glide
                .with(context)
                .load(list.getGambar())
                .centerCrop()
                .into(holder.gambar);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra("judul",list.getJudul() );
                intent.putExtra("gambar",list.getGambar() );
                intent.putExtra("link",list.getLink() );

               context. startActivity(intent);

            }
        });

    }


    @Override
    public int getItemCount() {
        return listTrendingModelArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView gambar;
        private TextView judul, chapter;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            gambar = itemView.findViewById(R.id.gambar);
            judul = itemView.findViewById(R.id.judul);
            chapter = itemView.findViewById(R.id.chapter);

        }
    }
}
