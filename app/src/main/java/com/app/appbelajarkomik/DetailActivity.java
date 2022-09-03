package com.app.appbelajarkomik;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.palette.graphics.Palette;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

public class DetailActivity extends AppCompatActivity implements ParsePageTask.Callback {
    private  FrameLayout frameLeft;
    private FrameLayout frameRight;
    private CustomBackgroundCurve customBackgroundCurve;
    private CardView cardChapter;
    private ImageView imageView;
    private TextView textViewJudul;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_detail);
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

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.cover);
        Palette.from(bitmap).generate(palette -> {
            int defaultValue = 0x000000;
            int color = palette.getDarkVibrantColor(defaultValue);
            customBackgroundCurve.setBg(color);
            frameLeft.setBackgroundColor(color);
            frameRight.setBackgroundColor(color);
          //  customBackgroundCurve.setBg(getResources().getColor(R.color.testColor));

        });


        cardChapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(DetailActivity.this, "Show Chapter", Toast.LENGTH_SHORT).show();
            }
        });

        ParsePageTask parsePageTask = new ParsePageTask();
        parsePageTask.execute(getIntent().getStringExtra("link"));
        parsePageTask.setCallback(this);

    }


    @Override
    public void onChange(String response) {
        Log.i( "onChange: ",response);
    }
}