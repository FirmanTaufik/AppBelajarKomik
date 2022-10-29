package com.app.appbelajarkomik.adapter;

import android.annotation.SuppressLint;
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
import com.app.appbelajarkomik.model.ListChapterBaru;
import com.app.appbelajarkomik.model.ListTrendingModel;
import com.bumptech.glide.Glide;
import com.joooonho.SelectableRoundedImageView;

import java.util.ArrayList;

public class rvAdapterBaru extends RecyclerView.Adapter<rvAdapterBaru.ViewHolder> {
    private Context context;
    private ArrayList<ListChapterBaru> listChapterBarus;

    public rvAdapterBaru(Context context, ArrayList<ListChapterBaru> listChapterBarus) {
        this.context = context;
        this.listChapterBarus = listChapterBarus;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view);
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    @Override
    public void onBindViewHolder(@NonNull rvAdapterBaru.ViewHolder holder, int position) {
        ListChapterBaru list = listChapterBarus.get(position);
        Glide.with(context).load(list.getGambar()).into(holder.image);
        holder.textTitle.setText(list.getJudul());
        holder.txtJenis.setText(list.getStatus());
        holder.textChapter.setText(list.getChapter());
        holder.textRating.setText(list.getRating());
        holder.textViews.setText(list.getView());

        holder.textViews.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_baseline_remove_red_eye_24,
                0, 0, 0);


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
        return listChapterBarus.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private SelectableRoundedImageView image;
        private TextView textTitle, txtJenis,textChapter,
                textRating,textViews;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image);
            textTitle = itemView.findViewById(R.id.textTitle);
            txtJenis = itemView.findViewById(R.id.txtJenis);
            textChapter = itemView.findViewById(R.id.textChapter);
            textRating = itemView.findViewById(R.id.textRating);
            textViews = itemView.findViewById(R.id.textViews);

        }
    }
}
