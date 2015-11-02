package com.eqot.android.utils.image.smartimage;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.widget.ImageView;

import java.lang.ref.WeakReference;

public class AsyncDrawable extends BitmapDrawable {
    private final WeakReference<BitmapWorkerTask> mBitmapWorkerTaskRef;

    public AsyncDrawable(Resources res, Bitmap bitmap, BitmapWorkerTask bitmapWorkerTask) {
        super(res, bitmap);

        mBitmapWorkerTaskRef = new WeakReference<>(bitmapWorkerTask);
    }

    public BitmapWorkerTask getBitmapWorkerTask() {
        return mBitmapWorkerTaskRef.get();
    }

    public static boolean cancelPotentialWork(ImageView imageView, Uri uri) {
        final BitmapWorkerTask bitmapWorkerTask = getBitmapWorkerTask(imageView);

        if (bitmapWorkerTask != null) {
            final Uri bitmapUri = bitmapWorkerTask.uri;

            if (bitmapUri == null || bitmapUri != uri) {
                bitmapWorkerTask.cancel();
            } else {
                return false;
            }
        }

        return true;
    }

    private static BitmapWorkerTask getBitmapWorkerTask(ImageView imageView) {
        if (imageView != null) {
            final Drawable drawable = imageView.getDrawable();
            if (drawable instanceof AsyncDrawable) {
                return ((AsyncDrawable) drawable).getBitmapWorkerTask();
            }
        }

        return null;
    }
}
