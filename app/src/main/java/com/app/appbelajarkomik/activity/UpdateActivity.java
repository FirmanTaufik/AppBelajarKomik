package com.app.appbelajarkomik.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.app.appbelajarkomik.R;
import com.app.appbelajarkomik.adapter.rvAdapterLatest;
import com.app.appbelajarkomik.model.ListGenreModel;
import com.app.appbelajarkomik.model.ListMoreLatest;
import com.app.appbelajarkomik.utils.ParsePageTask;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;

public class UpdateActivity extends AppCompatActivity implements ParsePageTask.Callback {

    private ProgressBar progressBar;
    private RecyclerView recyclerView;
    private ArrayList<ListMoreLatest> listMoreLatests;
    private GridLayoutManager gridLayoutManager;
    private int page =1;
    private ParsePageTask parsePageTask;
    private rvAdapterLatest rvAdapterLatest;
    private int currentItems, totalItems, scrollOutItems;
    private boolean isScrolling = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        progressBar = findViewById(R.id.progressBar);
        recyclerView = findViewById(R.id.recyclerView);
        listMoreLatests = new ArrayList<>();
        gridLayoutManager = new GridLayoutManager(this, 3);
        recyclerView.setLayoutManager(gridLayoutManager);
        rvAdapterLatest = new rvAdapterLatest(this, listMoreLatests);
        recyclerView.setAdapter(rvAdapterLatest);

        parsePageTask = new ParsePageTask();
        parsePageTask.execute("https://komikindo.id/komik-terbaru/page/"+page++);
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
                currentItems= gridLayoutManager.getChildCount();
                totalItems = gridLayoutManager.getItemCount();
                scrollOutItems = gridLayoutManager.findFirstVisibleItemPosition();

                if (isScrolling && (currentItems+scrollOutItems == totalItems) ){
                    Toast.makeText(UpdateActivity.this, "Menampilkan Komik Lainya...", Toast.LENGTH_SHORT).show();
                    isScrolling=false;
                    progressBar.setVisibility(View.VISIBLE);

                    parsePageTask = new ParsePageTask();
                    parsePageTask.execute("https://komikindo.id/komik-terbaru/page/"+page++);
                    parsePageTask.setCallback(UpdateActivity.this);
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
            String title = element.select("div.bigor").select("div.tt").select("h4").text();
            String gambar = element.select("a").select("div.limit").select("img").attr("src");
            String link =element.select("a").attr("href");
            String chapter= element.select("div.bigor").select("div.adds").select("div.lsch").select("a").text();
            String menit = element.select("div.bigor").select("div.adds").select("div.lsch").select("span").text();
            Log.i( "onChangeUpdate: ",chapter + " "+menit);
            listMoreLatests.add(new ListMoreLatest(title,gambar,chapter,menit,link));
        }
        rvAdapterLatest.notifyDataSetChanged();

    }
}