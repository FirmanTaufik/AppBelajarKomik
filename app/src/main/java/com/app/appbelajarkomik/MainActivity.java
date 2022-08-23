package com.app.appbelajarkomik;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView rvTrend;
    private ArrayList<ListTrendingModel> listTrendingModelArrayList;
    private rvAdapterTrend rvAdapterTrend;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listTrendingModelArrayList = new ArrayList<>();
        rvTrend = findViewById(R.id.rvTrend);
        rvTrend.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));

        rvAdapterTrend = new rvAdapterTrend(this,listTrendingModelArrayList);
        rvTrend.setAdapter(rvAdapterTrend);
        for (int i = 0; i <8 ; i++) {

            listTrendingModelArrayList.add(new ListTrendingModel("Komik Standard Of Reincarnation",
                    "https://cdn.kena-blok.xyz/uploads/2022/08/Komik-Standard-Of-Reincarnation.jpg?width=214&height=315",
                    "chapter "+String.valueOf(i),
                    "https://komikindo.id/komik/standard-of-reincarnation/"));
        }

        Log.i("posisi", listTrendingModelArrayList.get(0).getChapter());
        rvAdapterTrend.notifyDataSetChanged();
        ///ghdusgiudududgigud
    }

    public void detail(View view) {
        startActivity(new Intent(MainActivity.this, DetailActivity.class));
    }
}