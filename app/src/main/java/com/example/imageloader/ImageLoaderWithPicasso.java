package com.example.imageloader;

import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class ImageLoaderWithPicasso {

    public static ImageLoaderWithPicasso imageLoaderLibrary;

    public static void load(String url, ImageView imageView) {
        if (imageLoaderLibrary == null) {
            imageLoaderLibrary = new ImageLoaderWithPicasso();
        }
        imageLoaderLibrary.loadPrivate(url, imageView);
        //When i add database ,I will put here db operation - adding
        DatabaseHelper helper = new DatabaseHelper(imageView.getContext());
        ModelLinkCount model = helper.getOrCreate(url);
        model.incrementTimesSeen();
        helper.updateItem(model);
    }

    protected void loadPrivate(String url, ImageView imageView) {
        Picasso.get()
                .load(url)
                .into(imageView);
    }
}
