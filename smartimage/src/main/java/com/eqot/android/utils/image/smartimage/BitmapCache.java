package com.eqot.android.utils.image.smartimage;

import android.graphics.Bitmap;
import android.net.Uri;
import android.util.LruCache;

public class BitmapCache {
    private static MemoryCache mMemoryCache;

    public BitmapCache() {
        mMemoryCache = new MemoryCache();
    }

    public void addBitmap(Uri uri, Bitmap bitmap) {
        mMemoryCache.addBitmap(uri, bitmap);
    }

    public Bitmap getBitmap(Uri uri) {
        Bitmap bitmap = mMemoryCache.getBitmap(uri);
        if (bitmap != null) {
            return bitmap;
        }

        return null;
    }

    public static class MemoryCache {
        private static LruCache<Uri, Bitmap> sMemoryCache;

        private final int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);
        private final int cacheSize = maxMemory / 8;

        public MemoryCache() {
            sMemoryCache = new LruCache<Uri, Bitmap>(cacheSize) {
                @Override
                protected int sizeOf(Uri uri, Bitmap bitmap) {
                    return bitmap.getByteCount() / 1024;
                }
            };
        }

        public void addBitmap(Uri uri, Bitmap bitmap) {
            if (getBitmap(uri) == null) {
                sMemoryCache.put(uri, bitmap);
            }
        }

        public Bitmap getBitmap(Uri uri) {
            return sMemoryCache.get(uri);
        }
    }
}
