package com.app.appbelajarkomik.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.app.appbelajarkomik.R;
import com.app.appbelajarkomik.adapter.rvAdapterSearch;
import com.app.appbelajarkomik.model.ListSearchModel;
import com.app.appbelajarkomik.utils.ParsePageTask;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity implements ParsePageTask.Callback {

    private ProgressBar progressBar;
    private RecyclerView recyclerView;
    private ArrayList<ListSearchModel> listSearchModels;
    private rvAdapterSearch   adapterSearch;
    private GridLayoutManager gridLayoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        Toast.makeText(this, "Mecari komik dengan judul "+getIntent().getStringExtra("keyword"), Toast.LENGTH_SHORT).show();
        listSearchModels = new ArrayList<>();
        adapterSearch = new rvAdapterSearch(this, listSearchModels);
        progressBar = findViewById(R.id.progressBar);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setAdapter(adapterSearch);
        gridLayoutManager = new GridLayoutManager(this, 3);
        recyclerView.setLayoutManager(gridLayoutManager);

        ParsePageTask parsePageTask = new ParsePageTask();
        parsePageTask.execute("https://komikindo.id/");
        parsePageTask.setCallback(this);
    }

    @Override
    public void onChange(String response) {

    }
}