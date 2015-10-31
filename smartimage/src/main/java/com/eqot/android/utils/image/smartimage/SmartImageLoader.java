package com.eqot.android.utils.image.smartimage;

import android.content.ContentResolver;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class SmartImageLoader {

    private static final String TAG = "SmartImageLoader";

    public static Bitmap decodeUri(ContentResolver cr, Uri uri, int reqWidth, int reqHeight) {
        Bitmap bitmap = null;

        try {
            InputStream inputStream = cr.openInputStream(uri);

            final BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeStream(inputStream, null, options);

            inputStream.close();

//            Log.i(TAG, "org size: " + options.outWidth + ", " + options.outHeight);

            options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);
            options.inPreferredConfig = Bitmap.Config.RGB_565;

//            Log.i(TAG, "sample size: " + options.inSampleSize);

            options.inJustDecodeBounds = false;

            inputStream = cr.openInputStream(uri);
            bitmap = BitmapFactory.decodeStream(inputStream, null, options);

//            Log.i(TAG, "mod size: " + options.outWidth + ", " + options.outHeight);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return bitmap;
    }

    private static int calculateInSampleSize(
            BitmapFactory.Options options, int reqWidth, int reqHeight) {
        final int width = options.outWidth;
        final int height = options.outHeight;

        int inSampleSize = 1;

        if (width > reqWidth || height > reqHeight) {
            final int halfWidth = width / 2;
            final int halfHeight = height / 2;

            while ((halfWidth / inSampleSize) > reqWidth
                    && (halfHeight / inSampleSize) > reqHeight) {
                inSampleSize *= 2;
            }
        }

        return inSampleSize;
    }

}
