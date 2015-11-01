package com.eqot.android.utils.image.smartimage;

import android.content.ContentResolver;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.lang.ref.WeakReference;

public class BitmapWorkerTask extends AsyncTask<Uri, Void, Bitmap> {
    private final WeakReference<ImageView> mImageViewReference;
    private final ContentResolver mContentResolver;

    public Uri uri = null;

    public BitmapWorkerTask(ImageView imageView, ContentResolver cr) {
        mImageViewReference = new WeakReference<ImageView>(imageView);
        mContentResolver = cr;
    }

    @Override
    protected Bitmap doInBackground(Uri... params) {
        uri = params[0];
        return SmartImageLoader.decodeUri(mContentResolver, uri, 128, 128);
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        if (mImageViewReference != null && bitmap != null) {
            final ImageView imageView = mImageViewReference.get();
            if (imageView != null) {
                imageView.setImageBitmap(bitmap);
            }
        }
    }
}
