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
import com.app.appbelajarkomik.model.ListGenreModel;
import com.app.appbelajarkomik.model.ListSearchModel;
import com.bumptech.glide.Glide;
import com.joooonho.SelectableRoundedImageView;

import java.util.ArrayList;

import per.wsj.library.AndRatingBar;

public class rvAdapterGenre extends RecyclerView.Adapter<rvAdapterGenre.ViewHolder> {
    private Context context;
    private ArrayList<ListGenreModel> listGenreModel;

    public rvAdapterGenre(Context context, ArrayList<ListGenreModel> listGenreModel) {
        this.context = context;
        this.listGenreModel = listGenreModel;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ListGenreModel list = listGenreModel.get(position);
        holder.textTitle.setText(list.getTitle());
        holder.textRating.setText(list.getRating());

        Glide.with(context).load(list.getGambar())
                .into(holder.image);


        holder.txtJenis.setText(list.getJenis());
        holder.textWarna.setText(list.getWarna());
        if (list.getWarna()==null || list.getWarna().equals("")){
            holder.textWarna.setText("Hitam Putih");
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra("judul",list.getTitle() );
                intent.putExtra("gambar",list.getGambar() );
                intent.putExtra("link",list.getLink() );

                context. startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return listGenreModel.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private SelectableRoundedImageView image;
        private TextView textTitle,txtJenis,textRating,textWarna;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image);
            textTitle = itemView.findViewById(R.id.textTitle);
            txtJenis = itemView.findViewById(R.id.txtJenis);
            textRating = itemView.findViewById(R.id.textRating);
            textWarna = itemView.findViewById(R.id.textViews);
        }
    }
}
