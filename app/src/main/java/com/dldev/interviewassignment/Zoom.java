package com.dldev.interviewassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

public class Zoom extends AppCompatActivity {

    private ScaleGestureDetector scaleGestureDetector;
    private float FACTOR = 1.0f;
    ImageView imageZoom;
    ImageView share;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_zoom);

        imageZoom = findViewById(R.id.imageZoom);
        share = findViewById(R.id.shareBtn);



        Intent intent = getIntent();
        String imageUrl = intent.getStringExtra("imageUrl");

        Glide.with(this).load(imageUrl).into(imageZoom);


        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent shareIntent = new Intent();
                shareIntent.setType("image/*");
                shareIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                shareIntent.setAction(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                shareIntent.putExtra(Intent.EXTRA_TEXT, imageUrl);

                try {
                    startActivity(shareIntent);
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(Zoom.this, "I can't send", Toast.LENGTH_SHORT).show();
                }
            }
        });





        scaleGestureDetector = new ScaleGestureDetector(this, new ScaleListener());


    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        scaleGestureDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener
    {
        @Override
        public boolean onScale(ScaleGestureDetector detector) {
            FACTOR *= detector.getScaleFactor();
            FACTOR = Math.max(0.1f, Math.min(FACTOR, 10.f));
            imageZoom.setScaleX(FACTOR);
            imageZoom.setScaleY(FACTOR);
            return true;
        }
    }
}