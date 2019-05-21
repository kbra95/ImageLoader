package com.example.imageloader;

import android.provider.BaseColumns;

public class ImageLoaderContract {

    //this is our table
    public static final class  ImagesEntry implements BaseColumns{

        public static final String TABLE_NAME = "images";

        public static final String _ID = BaseColumns._ID;
        public static final String COLUMN_URL = "url";
        public static final String COLUMN_TIME_SEEN = "timeseen";
    }
}
