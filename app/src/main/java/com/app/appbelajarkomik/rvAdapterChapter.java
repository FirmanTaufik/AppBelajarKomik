package com.app.appbelajarkomik;


import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class rvAdapterChapter extends RecyclerView.Adapter<rvAdapterChapter.ViewHolder> {
    private Context context;
    private ArrayList<ListChapterModel> listChapterModels;

    public rvAdapterChapter(Context context, ArrayList<ListChapterModel> listChapterModels) {
        this.context = context;
        this.listChapterModels = listChapterModels;
    }

    @NonNull
    @Override
    public rvAdapterChapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_chapter, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull rvAdapterChapter.ViewHolder holder, int position) {
        holder.textChapter.setText(listChapterModels.get(position).getChapter());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ChapterActivity.class);
                intent.putExtra("chapter",listChapterModels.get(position).getChapter());
                intent.putExtra("link",listChapterModels.get(position).getLink() );
                context. startActivity(intent);

                Log.i(  "onClick: ",listChapterModels.get(position).getLink() );
            }
        });
    }

    @Override
    public int getItemCount() {
        return listChapterModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textChapter;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textChapter = itemView.findViewById(R.id.textChapter);
        }
    }
}
