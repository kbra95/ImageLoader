package com.example.imageloader;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private ImageView ivBasicImage;
    private String url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ivBasicImage = (ImageView) findViewById(R.id.image);
        url = "http://i67.tinypic.com/2n668nc.jpg";
        // Download image from URL and display within ImageView

    }


    public void submit(View view) {
        EditText etURL = (EditText) findViewById(R.id.etURL);
        String url = etURL.getText().toString();
        if(!TextUtils.isEmpty(url)) {
            ImageLoaderWithPicasso.load(url ,ivBasicImage);
            //ImageLinkCount row = imageLinkCountDatabase.getRecord(url);
            //Toast.makeText(this, String.valueOf(row.getTimesSeen()), Toast.LENGTH_SHORT).show();
        }
    }
}

