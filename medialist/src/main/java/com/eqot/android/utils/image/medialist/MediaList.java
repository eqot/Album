package com.eqot.android.utils.image.medialist;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.provider.MediaStore;
import android.util.Log;

import java.util.ArrayList;

public class MediaList {

    private static final String TAG = "MediaList";

    private final Context mContext;

    public MediaList(Context context) {
        mContext = context;
    }

    public ArrayList<Object> load() {
        ArrayList<Object> list = new ArrayList<>();

        ContentResolver contentResolver = mContext.getContentResolver();
        Cursor cursor = contentResolver.query(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI, null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                String uri = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
                Log.d(TAG, uri);

                list.add(uri);
            } while (cursor.moveToNext());
        }
        cursor.close();

        return list;
    }

}
