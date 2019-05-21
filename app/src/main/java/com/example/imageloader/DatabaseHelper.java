package com.example.imageloader;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;
import android.widget.Toast;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "imageloader.db";
    public static final int DATABASE_VERSION = 1;
    private static final String TABLE_IMAGES_CREATE =
            "CREATE TABLE " + ImageLoaderContract.ImagesEntry.TABLE_NAME + "("+
                    ImageLoaderContract.ImagesEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    ImageLoaderContract.ImagesEntry.COLUMN_URL + " TEXT ," +
                    ImageLoaderContract.ImagesEntry.COLUMN_TIME_SEEN +" INTEGER)";


    public DatabaseHelper(@Nullable Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL(TABLE_IMAGES_CREATE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + ImageLoaderContract.ImagesEntry.TABLE_NAME);
        onCreate(sqLiteDatabase);

    }
    private ModelLinkCount createRecord(String url){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues newRecord = new ContentValues();
        newRecord.put(ImageLoaderContract.ImagesEntry.COLUMN_URL ,url);
        newRecord.put(ImageLoaderContract.ImagesEntry.COLUMN_TIME_SEEN ,0);

        long row = db.insertOrThrow(ImageLoaderContract.ImagesEntry.TABLE_NAME ,null,newRecord);
        db.close();

        if(row != -1){
            ModelLinkCount item =  new ModelLinkCount(url, 0);
            item.setId(row);
            return item;
        }
        return null;

    }
    public ModelLinkCount getRecord(String url){

        SQLiteDatabase db = this.getReadableDatabase();

        String[] projection = {ImageLoaderContract.ImagesEntry._ID,ImageLoaderContract.ImagesEntry.COLUMN_URL ,
                ImageLoaderContract.ImagesEntry.COLUMN_TIME_SEEN};
        String selection = ImageLoaderContract.ImagesEntry.COLUMN_URL + "=?";
        String[] selectionArgs = {url};
        Cursor cursor = db.query(ImageLoaderContract.ImagesEntry.TABLE_NAME,projection,selection,selectionArgs,
                null,null,null);


        if (cursor != null) {
            if (cursor.moveToFirst()) {
                ModelLinkCount item = new ModelLinkCount(cursor.getString(1), cursor.getInt(2));
                item.setId(cursor.getLong(0));
                return item;
            }
            return null;
        }
        //Toast.makeText(this,"data : " +i,Toast.LENGTH_SHORT).show();

        return null;

    }
    public ModelLinkCount getOrCreate(String url) {

        SQLiteDatabase db = this.getWritableDatabase();

        ModelLinkCount imageLinkCount = getRecord(url);

        if (imageLinkCount == null) {
            return createRecord(url);
        } else {
        }
        db.close();
        return imageLinkCount;
    }
}
