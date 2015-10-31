package com.eqot.android.utils.image.medialist;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

import java.util.ArrayList;

public class MediaList {

    private static final String TAG = "MediaList";

    private final Context mContext;

    public MediaList(Context context) {
        mContext = context;
    }

    public ArrayList<Object> load() {
        ArrayList<Object> list = new ArrayList<>();

        Uri contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;

        ContentResolver contentResolver = mContext.getContentResolver();
        Cursor cursor = contentResolver.query(contentUri, null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                Media media = new Media(cursor, contentUri);
                list.add(media);
            } while (cursor.moveToNext());
        }
        cursor.close();

        return list;
    }

}
