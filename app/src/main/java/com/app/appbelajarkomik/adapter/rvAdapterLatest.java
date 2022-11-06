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

import com.app.appbelajarkomik.R;
import com.app.appbelajarkomik.activity.DetailActivity;
import com.app.appbelajarkomik.model.ListMoreLatest;
import com.app.appbelajarkomik.model.ListSearchModel;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

import per.wsj.library.AndRatingBar;

public class rvAdapterLatest extends RecyclerView.Adapter<rvAdapterLatest.ViewHolder> {
    private Context context;
    private ArrayList<ListMoreLatest> listMoreLatests;

    public rvAdapterLatest(Context context, ArrayList<ListMoreLatest> listMoreLatests) {
        this.context = context;
        this.listMoreLatests = listMoreLatests;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_latest, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ListMoreLatest list = listMoreLatests.get(position);
        holder.judul.setText(list.getJudul());
        holder.textChapter.setText(list.getChapter());
        Glide.with(context).load(list.getGambar())
                .into(holder.gambar);

        holder.textMenit.setText(list.getMenit());


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
        return listMoreLatests.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView gambar;
        private TextView judul,textChapter,textMenit;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textMenit = itemView.findViewById(R.id.textMenit);
            textChapter = itemView.findViewById(R.id.textChapter);
            gambar = itemView.findViewById(R.id.gambar);
            judul = itemView.findViewById(R.id.judul);
        }
    }
}
