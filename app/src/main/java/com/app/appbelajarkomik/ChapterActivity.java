package com.app.appbelajarkomik;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ChapterActivity extends AppCompatActivity implements ParsePageTask.Callback {
    private WebView webView;
    private ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chapter);
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

        ParsePageTask parsePageTask = new ParsePageTask();
        parsePageTask.execute(getIntent().getStringExtra("link"));
        parsePageTask.setCallback(this);
    }

    @Override
    public void onChange(String response) {
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