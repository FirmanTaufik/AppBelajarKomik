package com.app.appbelajarkomik.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.app.appbelajarkomik.R;
import com.app.appbelajarkomik.adapter.rvAdapterSearch;
import com.app.appbelajarkomik.model.ListSearchModel;
import com.app.appbelajarkomik.utils.ParsePageTask;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

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

        String keyword = getIntent().getStringExtra("keyword").replaceAll(" ","+");
        ParsePageTask parsePageTask = new ParsePageTask();
        parsePageTask.execute("https://komikindo.id/?s="+keyword );
        parsePageTask.setCallback(this);
    }

    @Override
    public void onChange(String response) {
        progressBar.setVisibility(View.GONE);
        try {
            Document document = Jsoup.parse(response);
            Elements elements = document.getElementsByClass("postBody");
            Elements list = elements.select("div.film-list").select("div.animepost");

            for (Element l : list){
                String gambar = l.select("a").select("div.limit").select("img").attr("src");
                String judul = l.select("div.bigors").select("a").select("div.tt").text();
                String rating = l.select("div.bigors").select("div.adds").select("div.rating").select("i").text();
                String link = l.select("a").attr("href");

                listSearchModels.add(new ListSearchModel(judul, gambar, rating,link));
            }

            adapterSearch.notifyDataSetChanged();

        }catch (NullPointerException n) {
            Toast.makeText(this, "tidak ada komik yang di cari", Toast.LENGTH_SHORT).show();
        }

    }
}