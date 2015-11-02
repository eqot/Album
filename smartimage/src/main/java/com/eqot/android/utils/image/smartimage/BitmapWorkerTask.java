package com.eqot.android.utils.image.smartimage;

import android.content.ContentResolver;
import android.graphics.Bitmap;
import android.net.Uri;
import android.util.Log;
import android.widget.ImageView;

import java.lang.ref.WeakReference;

import rx.Observable;
import rx.Subscription;
import rx.schedulers.Schedulers;
import rx.android.schedulers.AndroidSchedulers;

public class BitmapWorkerTask {
    private static final String TAG = "BitmapWorkerTask (Rx)";

    private final WeakReference<ImageView> mImageViewReference;
    private final ContentResolver mContentResolver;

    public Uri uri = null;

    private Subscription mSubscription;

    public BitmapWorkerTask(ImageView imageView, ContentResolver cr) {
        mImageViewReference = new WeakReference<>(imageView);
        mContentResolver = cr;
    }

    public void execute(Uri imageUri) {
        mSubscription = Observable.just(imageUri)
                .subscribeOn(Schedulers.computation())
                .map(this::getBitmap)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::setBitmap, this::onError);
    }

    public void cancel() {
        mSubscription.unsubscribe();
    }

    public Bitmap getBitmap(Uri uri) {
        Log.i(TAG, Thread.currentThread().getName());

        this.uri = uri;

        return SmartImageLoader.decodeUri(mContentResolver, uri, 128, 128);
    }

    public void setBitmap(Bitmap bitmap) {
        if (mImageViewReference != null && bitmap != null) {
            final ImageView imageView = mImageViewReference.get();
            if (imageView != null) {
                imageView.setImageBitmap(bitmap);
            }
        }
    }

    public void onError(Throwable e) {
        Log.i(TAG, "" + e);
    }
}
