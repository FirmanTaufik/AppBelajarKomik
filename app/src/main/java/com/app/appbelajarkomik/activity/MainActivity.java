package com.app.appbelajarkomik.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.app.appbelajarkomik.model.ListTrendingModel;
import com.app.appbelajarkomik.utils.ParsePageTask;
import com.app.appbelajarkomik.R;
import com.app.appbelajarkomik.adapter.rvAdapterTrend;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements ParsePageTask.Callback {
    private RecyclerView rvTrend;
    private ArrayList<ListTrendingModel> listTrendingModelArrayList;
    private com.app.appbelajarkomik.adapter.rvAdapterTrend rvAdapterTrend;
    private ProgressBar progressBar;
    private EditText edtSearch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listTrendingModelArrayList = new ArrayList<>();
        progressBar = findViewById(R.id.progressBar);
        edtSearch = findViewById(R.id.edtSearch);
        rvTrend = findViewById(R.id.rvTrend);
        rvTrend.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));

        rvAdapterTrend = new rvAdapterTrend(this,listTrendingModelArrayList);
        rvTrend.setAdapter(rvAdapterTrend);

        edtSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i == EditorInfo.IME_ACTION_SEARCH) {
                    String keyword = edtSearch.getText().toString().trim();
                    Intent intent = new Intent(MainActivity.this, SearchActivity.class);
                    intent.putExtra("keyword", keyword);
                    startActivity(intent);
                    return true;
                }
                return false;
            }
        });

        ///ghdusgiudududgigud
        ParsePageTask parsePageTask = new ParsePageTask();
        parsePageTask.execute("https://komikindo.id/");
        parsePageTask.setCallback(this);
    }



    @Override
    public void onChange(String response) {
        try {
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
        }catch (NullPointerException n){
            n.getMessage();
        }
        progressBar.setVisibility(View.GONE);


    }
}