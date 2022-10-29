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
import com.app.appbelajarkomik.model.BookmarkModel;
import com.app.appbelajarkomik.model.BookmarkModel;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class rvAdapterBookmark extends RecyclerView.Adapter<rvAdapterBookmark.ViewHolder> {
    private Context context;
    private ArrayList<BookmarkModel> BookmarkModelArrayList;

    public rvAdapterBookmark(Context context, ArrayList<BookmarkModel> BookmarkModelArrayList) {
        this.context = context;
        this.BookmarkModelArrayList = BookmarkModelArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_bookmark, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull rvAdapterBookmark.ViewHolder holder, int position) {
        BookmarkModel list =BookmarkModelArrayList.get(position);
        holder.judul.setText(list.getJudul());
        Glide
                .with(context)
                .load(list.getImage())
                .centerCrop()
                .into(holder.gambar);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra("judul",list.getJudul() );
                intent.putExtra("gambar",list.getImage() );
                intent.putExtra("link",list.getLink() );

               context. startActivity(intent);

            }
        });

    }


    @Override
    public int getItemCount() {
        return BookmarkModelArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView gambar;
        private TextView judul;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            gambar = itemView.findViewById(R.id.gambar);
            judul = itemView.findViewById(R.id.judul);

        }
    }
}
