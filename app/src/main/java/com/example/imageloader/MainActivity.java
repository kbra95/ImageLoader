package com.example.imageloader;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
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
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private ImageView ivBasicImage;
    private String url;
    private EditText etURL;
    private DatabaseHelper helper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ivBasicImage = (ImageView) findViewById(R.id.image);
        etURL = (EditText) findViewById(R.id.etURL);
        url = "http://i67.tinypic.com/2n668nc.jpg";
        helper = new DatabaseHelper(this);
        // Download image from URL and display within ImageView


    }


    public void submit(View view) {

        String url = etURL.getText().toString();
        if(!TextUtils.isEmpty(url)) {
            ImageLoaderWithPicasso.load(url,ivBasicImage);
            ModelLinkCount row = helper.getRecord(url);
            Toast.makeText(this, String.valueOf(row.getTimesSeen()), Toast.LENGTH_SHORT).show();
        }
    }

}

