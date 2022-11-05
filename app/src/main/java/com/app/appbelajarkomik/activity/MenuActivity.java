package com.app.appbelajarkomik.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.app.appbelajarkomik.R;
import com.app.appbelajarkomik.adapter.rvAdapterGenre;
import com.app.appbelajarkomik.model.ListGenreModel;
import com.app.appbelajarkomik.utils.ParsePageTask;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;

public class MenuActivity extends AppCompatActivity implements ParsePageTask.Callback {
    private String TAG ="MenuActivityTAG";
    private ProgressBar progressBar;
    private RecyclerView recyclerView;
    private ParsePageTask parsePageTask;
    private int page =1;
    private ArrayList<ListGenreModel> list;
    private rvAdapterGenre adapter;
    private int currentItems, totalItems, scrollOutItems;
    private boolean isScrolling = true;
    private LinearLayoutManager linearLayoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        list= new ArrayList<>();
        adapter = new rvAdapterGenre(this, list);
        progressBar = findViewById(R.id.progressBar);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setAdapter(adapter);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        Toast.makeText(this, "Mencari Komik dengan Genre "+ getIntent().getStringExtra("genre"), Toast.LENGTH_SHORT).show();

        String keyword = getIntent().getStringExtra("genre").replaceAll(" ","-");
        parsePageTask = new ParsePageTask();
        parsePageTask.execute("https://komikindo.id/genres/"+keyword +"/page/"+page++);
        parsePageTask.setCallback(this);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL){
                    isScrolling=true;
                }
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                currentItems= linearLayoutManager.getChildCount();
                totalItems = linearLayoutManager.getItemCount();
                scrollOutItems = linearLayoutManager.findFirstVisibleItemPosition();

                if (isScrolling && (currentItems+scrollOutItems == totalItems) ){
                    Toast.makeText(MenuActivity.this, "Menampilkan Komik Lainya...", Toast.LENGTH_SHORT).show();
                    isScrolling=false;
                    progressBar.setVisibility(View.VISIBLE);
                    parsePageTask = new ParsePageTask();
                    parsePageTask.execute("https://komikindo.id/genres/"+keyword +"/page/"+page++);
                    parsePageTask.setCallback(MenuActivity.this);
                }

            }
        });
    }

    @Override
    public void onChange(String response) {
        progressBar.setVisibility(View.GONE);
        parsePageTask= null;
        if (response==null) return;

        Document document = Jsoup.parse(response);
        Elements elements = document.getElementById("wrap")
                .select("div.postbody").select("div.film-list")
                .select("div.animepost");

        for (Element element : elements ) {
            String title = element.select("div.bigors").select("div.tt").select("h4").text();
            String gambar = element.select("a").select("div.limit").select("img").attr("src");
            String jenis  = element.select("a").select("div.limit").select("span").attr("class").replace("typeflag ","");
            String rating =element.select("div.bigors").select("div.adds").select("div.rating").select("i").text();
            String link =element.select("a").attr("href");
            String warna = element.select("a").select("div.limit").select("div.warnalabel").text();
            Log.d(TAG, "onChange: "+jenis);
            list.add(new ListGenreModel(title, gambar, jenis, rating,link,warna));
        }
        adapter.notifyDataSetChanged();

    }
}