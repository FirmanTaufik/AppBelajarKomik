package com.app.appbelajarkomik.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.transition.Slide;
import androidx.transition.Transition;
import androidx.transition.TransitionManager;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.app.appbelajarkomik.adapter.rvAdapterBaru;
import com.app.appbelajarkomik.model.ListChapterBaru;
import com.app.appbelajarkomik.model.ListTrendingModel;
import com.app.appbelajarkomik.model.UserModel;
import com.app.appbelajarkomik.utils.Constant;
import com.app.appbelajarkomik.utils.ParsePageTask;
import com.app.appbelajarkomik.R;
import com.app.appbelajarkomik.adapter.rvAdapterTrend;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements ParsePageTask.Callback {
    private RecyclerView rvTrend;
    private ArrayList<ListTrendingModel> listTrendingModelArrayList;
    private com.app.appbelajarkomik.adapter.rvAdapterTrend rvAdapterTrend;
    private ProgressBar progressBar;
    private EditText edtSearch;
    private TextView txtName;
    private DatabaseReference mDatabase;
    private FloatingActionButton fabMenu;
    private ImageButton btnClose;
    private LinearLayout linearGenre;
    private RecyclerView rvBaru;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        listTrendingModelArrayList = new ArrayList<>();
        rvBaru = findViewById(R.id.rvBaru);
        linearGenre = findViewById(R.id.linearGenre);
        btnClose = findViewById(R.id.btnClose);
        fabMenu = findViewById(R.id.fabMenu);
        txtName = findViewById(R.id.txtName);
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
        getName();

        fabMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toogle(true);
            }
        });

        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toogle(false);
            }
        });
        setUpGenre();
    }

    private void setUpGenre() {
        Document document = Jsoup.parse(Constant.htmlGenre());
        Elements elements = document.select("ul").select("li");
        for (Element e: elements ) {
            View view = LayoutInflater.from(this).inflate(R.layout.list_genre, null);
            TextView textGenre = view.findViewById(R.id.textGenre);
            textGenre.setText(e.select("a") .text());

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(MainActivity.this,MenuActivity.class);
                    intent.putExtra("genre", e.text());
                    startActivity(intent);

                }
            });
            linearGenre.addView(view);
            Log.d(TAG, "setUpGenre: "+e.text());
        }

    }

    private void toogle(boolean show){
        View linearChapter = findViewById(R.id.linearChapter);
        ViewGroup parent = findViewById(R.id.parent);

        Transition transition = new Slide(Gravity.RIGHT);
        transition.setDuration(600);
        transition.addTarget(R.id.linearChapter);

        TransitionManager.beginDelayedTransition(parent, transition);
        linearChapter.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    String TAG ="MainActivityTAG";
    private void getName(){
        if (Constant.getId(this)==null) {
            return;
        }
        mDatabase.child("users").child(Constant.getId(this))
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                         UserModel userModel = snapshot.getValue(UserModel.class);
                        Log.d(TAG, "onDataChange: "+userModel.getNama());
                        txtName.setText(userModel.getNama());
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
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

                listTrendingModelArrayList.add(new ListTrendingModel(judul,  gambar,   chapter, link));
              //  Log.i( "onChange: ", chapter);
            }
            rvAdapterTrend.notifyDataSetChanged();

            //KOMIK TERBARU

            Elements mangabaru = postbody.select("div.chapterbaru")
                    .select("div.listupd").select("div.animepost");

            Log.d(TAG, "onChange: "+postbody.select("div.chapterbaru"));

            ArrayList <ListChapterBaru> listChapterBarus = new ArrayList<>();

            for (Element list: mangabaru ) {
                String judul = list.select("div.animepostxx-top")
                        .select("div.animepostxx-top-bottom").select("a").select("div.tt")
                        .select("h4").text();
                String gambar = list.select("div.animepostxx-top")
                        .select("a").select("div.limietles").select("img").attr("src")
                        .replaceAll("width=60&height=60","");
                String chapter = list.select("div.animepostxx-bottom")
                        .select("div.info-skroep").select("div.list-ch-skroep")
                        .select("div.lsch").first().select("a").text();
                String status = list.select("div.animepostxx-bottom")
                        .select("div.info-skroep").select("div.status-skroep").text();

                String rating = list.select("div.animepostxx-top")
                        .select("div.animepostxx-top-bottom").select("a").select("div.info-skroep")
                        .select("div.flex-skroep").first().text();

                String view = list.select("div.animepostxx-top")
                        .select("div.animepostxx-top-bottom").select("a").select("div.info-skroep")
                        .select("div.flex-skroep").get(2).text();

                String link = list.select("div.animepostxx-top")
                        .select("div.animepostxx-top-bottom").select("a").attr("href");

                listChapterBarus.add(new ListChapterBaru(judul,gambar,chapter,status,rating
                        ,view,link));
                Log.d(TAG, "onChangeBaru: "+ view);
            }
            rvBaru.setLayoutManager(new LinearLayoutManager(this));
            rvBaru.setAdapter(new rvAdapterBaru(this, listChapterBarus));


        }catch (NullPointerException n){
            n.getMessage();
        }
        progressBar.setVisibility(View.GONE);
    }

    public void bookmark(View view) {
        if (Constant.getId(MainActivity.this)==null) {

            AlertDialog.Builder builder1 = new AlertDialog.Builder(MainActivity.this);
            builder1.setMessage("Login terlebih dahulu untuk bookmark");
            builder1.setCancelable(true);

            builder1.setPositiveButton(
                    "Login",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                            intent.putExtra("isDetail", true);
                            startActivity(intent);
                            dialog.cancel();
                        }
                    });

            builder1.setNegativeButton(
                    "Cancel",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });

            AlertDialog alert11 = builder1.create();
            alert11.show();

            return;
        }
        startActivity(new Intent(this, BookmarkActivity.class));
    }

    public void update(View view) {
        startActivity(new Intent(this, UpdateActivity.class));
    }

    boolean doubleBackToExitPressedOnce = false;

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            Intent homeIntent = new Intent(Intent.ACTION_MAIN);
            homeIntent.addCategory( Intent.CATEGORY_HOME );
            homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(homeIntent);
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Tap Sekali Lagi Untuk Close Aplikasi", Toast.LENGTH_SHORT).show();

        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }

}