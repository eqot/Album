package com.eqot.android.utils.image.medialist;

import android.content.ContentUris;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

public class Media {

    public Uri mUri;
    public String mData;
    public String mTitle;

    public Media(Cursor cursor, Uri contentUri) {
        Long id = cursor.getLong(cursor.getColumnIndex(MediaStore.Images.Media._ID));
        mUri = ContentUris.withAppendedId(contentUri, id);

        mData = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));

        mTitle = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.TITLE));
    }

}
