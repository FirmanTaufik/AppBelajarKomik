package com.app.appbelajarkomik;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.palette.graphics.Palette;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.transition.Slide;
import androidx.transition.Transition;
import androidx.transition.TransitionManager;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.joooonho.SelectableRoundedImageView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;

public class DetailActivity extends AppCompatActivity implements ParsePageTask.Callback {
    private  FrameLayout frameLeft;
    private FrameLayout frameRight;
    private CustomBackgroundCurve customBackgroundCurve;
    private CardView cardChapter;
    private ImageView imageView;
    private TextView textViewJudul;
    private ProgressBar progressBar;
    private LinearLayout linearContent;
    private TextView textSinopsis,textGenre,
            textRating,textStatus,textViews;
    private SelectableRoundedImageView imageFlag;
    private ImageButton btnClose;
    private RecyclerView recyclerView;
    private  rvAdapterChapter rvAdapterChapter;
    private ArrayList<ListChapterModel> listChapterModels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_detail);
        recyclerView = findViewById(R.id.recyclerView);
        btnClose = findViewById(R.id.btnClose);
        imageFlag = findViewById(R.id.imageFlag);
        textViews = findViewById(R.id.textViews);
        textStatus = findViewById(R.id.textStatus);
        textGenre = findViewById(R.id.textGenre);
        textRating = findViewById(R.id.textRating);
        textSinopsis = findViewById(R.id.textSinopsis);
        imageView = findViewById(R.id.imageView);
        textViewJudul = findViewById(R.id.textViewJudul);
        textViewJudul.setText(getIntent().getStringExtra("judul"));

        Glide.with(this)
                .load(getIntent().getStringExtra("gambar"))
                .centerCrop()
                .into(imageView);


        customBackgroundCurve = findViewById(R.id.custom);
        frameLeft = findViewById(R.id.frameLeft);
        frameRight = findViewById(R.id.frameRight);
        cardChapter = findViewById(R.id.cardChapter);
        progressBar = findViewById(R.id.progressBar);
        linearContent = findViewById(R.id.linearContent);

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.cover);
        Palette.from(bitmap).generate(palette -> {
            int defaultValue = 0x000000;
            int color = palette.getDarkVibrantColor(defaultValue);
            customBackgroundCurve.setBg(color);
            frameLeft.setBackgroundColor(color);
            frameRight.setBackgroundColor(color);
          //  customBackgroundCurve.setBg(getResources().getColor(R.color.testColor));

        });

        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toogle(false);
            }
        });

        cardChapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toogle(true);
                Toast.makeText(DetailActivity.this, "Show Chapter", Toast.LENGTH_SHORT).show();
            }
        });
        listChapterModels = new ArrayList<>();
        rvAdapterChapter = new rvAdapterChapter(this, listChapterModels);
        recyclerView.setAdapter(rvAdapterChapter);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));


        ParsePageTask parsePageTask = new ParsePageTask();
        parsePageTask.execute(getIntent().getStringExtra("link"));
        parsePageTask.setCallback(this);

    }


    @Override
    public void onChange(String response) {
        progressBar.setVisibility(View.GONE);
        linearContent.setVisibility(View.VISIBLE);
        cardChapter.setVisibility(View.VISIBLE);

        Document document = Jsoup.parse(response);
        Elements article = document.getElementById("wrap")
                .select("article");

        String  synopsis = article.select("div#sinopsis")
                        .select("div.entry-content").text();
        textSinopsis.setText(synopsis);

        //AMBIL GENRE
        Elements genres = article.select("div.infoanime")
                        .select("div.infox").select("div.genre-info").select("a");

        textGenre.setText("Genre : ");
        for (int i = 0; i <genres.size() ; i++) {
            Element g = genres.get(i);
            Log.i("perulangan ke ", String.valueOf(i) + " "+g.text());
            if (i== genres.size()-1) {
                textGenre.append(g.text());

            }else {
                textGenre.append(g.text()+", ");
            }

        }

        //RATING
        String rating = article.select("div.infoanime").select("div.thumb")
                .select("div.ratingmanga").select("div.rtg")
                .select("i").first().text();
        textRating.setText(rating);

        //STATUS DAN VIEW

        Elements list = article.select("div.infoanime")
                .select("div.infox").select("div.spe")
                .select("span");

        for (Element e : list){


            if (e.text().startsWith("Status:")) {
                String status = e.text().replace("Status: ","");
                textStatus.setText(status);
            }

            if (e.text().startsWith("Jumlah Pembaca:")) {
                String jmlPembaca = e.text().replace("Jumlah Pembaca: ","")
                        .replace("orang","");
                textViews.setText(jmlPembaca);

            }

            if (e.text().startsWith("Jenis Komik")) {
                String jenisKomik = e.text().replace("Jenis Komik: ","") ;

                switch (jenisKomik) {
                    case "Manhwa":
                        imageFlag.setImageResource(R.drawable.ic_south_korea);
                        Log.i("onChangeDetail:  ","Ini Korea");
                        break;
                    case "Manhua":
                        imageFlag.setImageResource(R.drawable.ic_china);
                        Log.i("onChangeDetail:  ","Ini China");
                        break;
                    case "Manga":
                        imageFlag.setImageResource(R.drawable.ic_japan);
                        Log.i("onChangeDetail:  ","Ini Jepang");
                        break;
                }
            }
        }

        //LIST EPISODE
        Log.i( "onChangeEpisode: ",article.select("div.listeps").html());
        Elements listEpisode = article.select("div.listeps")
                .select("div.bxcl").select("ul").select("li");


        for (Element eps :listEpisode) {
            Log.i( "onChangeEpisodeList: ", eps.select("span").first().select("a").text() +
                    " "+eps.select("span").select("a").attr("href")  );

            listChapterModels.add(new ListChapterModel(eps.select("span").first().select("a").text(),
                    eps.select("span").select("a").attr("href") ));

        }

        rvAdapterChapter.notifyDataSetChanged();

    }

    private void toogle(boolean show){
        View linearChapter = findViewById(R.id.linearChapter);
        ViewGroup parent = findViewById(R.id.parent);

        Transition transition = new Slide(Gravity.BOTTOM);
        transition.setDuration(600);
        transition.addTarget(R.id.linearChapter);

        TransitionManager.beginDelayedTransition(parent, transition);
        linearChapter.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    public void share(View view) {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, "Aku Baca Komik " +textViewJudul.getText().toString() + " di Aplikasi "
                +getString(R.string.app_name) + " Download di https://play.google.com/store/apps/details?id="+getPackageName() );
        sendIntent.setType("text/plain");

        Intent shareIntent = Intent.createChooser(sendIntent, null);
        startActivity(shareIntent);
    }
}