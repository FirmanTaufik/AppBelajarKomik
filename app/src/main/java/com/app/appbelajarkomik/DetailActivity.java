package com.app.appbelajarkomik;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.palette.graphics.Palette;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.FrameLayout;

public class DetailActivity extends AppCompatActivity {
    private FrameLayout frameLeft;
    private FrameLayout frameRight;
    private CustomBackgroundCurve customBackgroundCurve;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_detail);
        customBackgroundCurve = findViewById(R.id.custom);
        frameLeft = findViewById(R.id.frameLeft);
        frameRight = findViewById(R.id.frameRight);

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.cover);
        Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
            @Override
            public void onGenerated(@Nullable Palette palette) {
                int defaultValue = 0x000000;
                int color = palette.getDarkVibrantColor(defaultValue);
                customBackgroundCurve.setBg(color);
                frameLeft.setBackgroundColor(color);
                frameRight.setBackgroundColor(color);
              //  customBackgroundCurve.setBg(getResources().getColor(R.color.testColor));

            }
        });
    }
}