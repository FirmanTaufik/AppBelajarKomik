package com.app.appbelajarkomik;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements ParsePageTask.Callback {
    private RecyclerView rvTrend;
    private ArrayList<ListTrendingModel> listTrendingModelArrayList;
    private rvAdapterTrend rvAdapterTrend;
    private ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listTrendingModelArrayList = new ArrayList<>();
        progressBar = findViewById(R.id.progressBar);

        rvTrend = findViewById(R.id.rvTrend);
        rvTrend.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));

        rvAdapterTrend = new rvAdapterTrend(this,listTrendingModelArrayList);
        rvTrend.setAdapter(rvAdapterTrend);


        ///ghdusgiudududgigud
        ParsePageTask parsePageTask = new ParsePageTask();
        parsePageTask.execute("https://komikindo.id/");
        parsePageTask.setCallback(this);
    }



    @Override
    public void onChange(String response) {
        progressBar.setVisibility(View.GONE);
        Document document = Jsoup.parse(response);
        Elements postbody = document.getElementById("main").getElementsByClass("postbody");
        //KOMIK POPULAR
        Elements  mangapopuler = postbody.select("div.mangapopuler").select("div.odadingslider")
                        .select("div.animepost");

        for (int i = 0; i < mangapopuler.size() ; i++) {
            String judul =  mangapopuler.get(i).select("div.bigor").select("a").select("div.tt").text();
            String link =   mangapopuler.get(i).select("div.bigor").select("a").attr("href");
            String gambar = mangapopuler.get(i).select("div.limit")
                    .select("img").attr("src").replaceAll("width=146&height=208","");
            String chapter = mangapopuler.get(i).select("div.bigor").select("div.adds")
                            .select("a").text();

            listTrendingModelArrayList.add(new ListTrendingModel(judul,
                    gambar,
                    chapter,
                    link));

            Log.i( "onChange: ", chapter);
        }
        rvAdapterTrend.notifyDataSetChanged();

    }
}