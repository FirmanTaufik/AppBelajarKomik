package com.app.appbelajarkomik.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.app.appbelajarkomik.model.ListChapterModel;
import com.app.appbelajarkomik.utils.ParsePageTask;
import com.app.appbelajarkomik.R;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;

public class ChapterActivity extends AppCompatActivity implements ParsePageTask.Callback {
    private WebView webView;
    private ProgressBar progressBar;
    private ArrayList<ListChapterModel> listChapterModels;
    private int position =0;
    private ImageButton btnPrevious,btnNext;
    private TextView textChapter;
    private   ParsePageTask parsePageTask;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chapter);
        listChapterModels = getIntent().getParcelableArrayListExtra("list");
        position = getIntent().getIntExtra("pos", 0);

        textChapter = findViewById(R.id.textChapter);
        btnPrevious = findViewById(R.id.btnPrevious);
        btnNext = findViewById(R.id.btnNext);
        webView = findViewById(R.id.webView);
        progressBar = findViewById(R.id.progressBar);

        webView.setWebViewClient(new WebViewClient());
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setBuiltInZoomControls(false);
        webView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        webView.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if (newProgress==100){
                    progressBar.setVisibility(View.GONE);
                }
                super.onProgressChanged(view, newProgress);
            }
        } );

        parsePageTask = new ParsePageTask();
        parsePageTask.execute( listChapterModels.get(position).getLink());
        parsePageTask.setCallback(this);

        textChapter.setText(listChapterModels.get(position).getChapter());
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (position==0){
                    Toast.makeText(ChapterActivity.this, "tidak ada chapter selanjutnya lagi", Toast.LENGTH_SHORT).show();

                }else{
                    position = position-1;
                    parsePageTask = new ParsePageTask();
                    parsePageTask.execute( listChapterModels.get(position).getLink());
                    parsePageTask.setCallback(ChapterActivity.this);
                    progressBar.setVisibility(View.VISIBLE);

                }
            }
        });

        btnPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (position==listChapterModels.size()-1) {
                    Toast.makeText(ChapterActivity.this, "tidak ada chapter sebelumnya lagi", Toast.LENGTH_SHORT).show();

                }else {
                    position = position+1;
                    parsePageTask = new ParsePageTask();
                    parsePageTask.execute( listChapterModels.get(position).getLink());
                    parsePageTask.setCallback(ChapterActivity.this);
                    progressBar.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    @Override
    public void onChange(String response) {
        textChapter.setText(listChapterModels.get(position).getChapter());
        parsePageTask =null;
        Document document = Jsoup.parse(response);
        Elements article = document.getElementById("wrap")
                .select("article");

        Elements  list = article.select("div.imgch")
                .select("img");

        StringBuilder sb = new StringBuilder();
        sb.append("<html> <head> <style type='text/css'> body{margin:auto auto;text-align:center;} img{max-width:100%} " +
                "width: auto; height:auto; margin-bottom:10px;} </style></body>");
        for (Element l :list){
            sb.append("<img src=\" "+ l.attr("src")+"\" >");
            Log.i( "onChangeChapter: ", l.attr("src"));
        }
        sb.append("</body> </html>");
        webView.loadData(sb.toString(), "text/html", null);

    }
}