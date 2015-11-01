package com.eqot.android.utils.image.smartimage;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;

import java.lang.ref.WeakReference;

public class AsyncDrawable extends BitmapDrawable {
    private final WeakReference<BitmapWorkerTask> mBitmapWorkerTaskWeakReference;

    public AsyncDrawable(Resources res, Bitmap bitmap,
                         BitmapWorkerTask bitmapWorkerTaskWeakReference) {
        super(res, bitmap);
        mBitmapWorkerTaskWeakReference =
                new WeakReference<BitmapWorkerTask>(bitmapWorkerTaskWeakReference);
    }

    public BitmapWorkerTask getBitmapWorkerTask() {
        return mBitmapWorkerTaskWeakReference.get();
    }
}
