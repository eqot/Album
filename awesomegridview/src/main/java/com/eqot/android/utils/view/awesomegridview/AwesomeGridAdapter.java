package com.eqot.android.utils.view.awesomegridview;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.eqot.android.utils.image.medialist.Media;
import com.eqot.android.utils.image.smartimage.AsyncDrawable;
import com.eqot.android.utils.image.smartimage.BitmapWorkerTask;

import java.util.ArrayList;

public class AwesomeGridAdapter extends RecyclerView.Adapter<AwesomeGridAdapter.ViewHolder> {
    private static final String TAG = "AwesomeGridAdapter";

    private ArrayList<Object> mDataset;
    private View mView;

    private Bitmap mPlaceHolderBitmap;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView mImageView;
        public TextView mTextView;

        public ViewHolder(View itemView) {
            super(itemView);

            mImageView = (ImageView) itemView.findViewById(R.id.list_item_image);
            mTextView = (TextView) itemView.findViewById(R.id.list_item_text);
        }
    }

    public AwesomeGridAdapter(ArrayList<Object> dataset, View view) {
        mDataset = dataset;
        mView = view;

        mPlaceHolderBitmap = BitmapFactory.decodeResource(
                mView.getResources(), R.drawable.ic_launcher);
    }

    @Override
    public AwesomeGridAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                        int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (mDataset.get(0) instanceof Integer) {
            Bitmap bitmap = BitmapFactory.decodeResource(
                    mView.getResources(), (Integer) mDataset.get(position));
            holder.mImageView.setImageBitmap(bitmap);
        } else if (mDataset.get(0) instanceof String) {
            holder.mTextView.setText(mDataset.get(position).toString());
        } else {
            Uri uri = ((Media) mDataset.get(position)).mUri;

            if (cancelPotentialWork(uri, holder.mImageView)) {
                final BitmapWorkerTask task = new BitmapWorkerTask(
                        holder.mImageView, mView.getContext().getContentResolver());
                final AsyncDrawable drawable =
                        new AsyncDrawable(mView.getResources(), mPlaceHolderBitmap, task);
                holder.mImageView.setImageDrawable(drawable);
                task.execute(uri);
            }
        }
    }

    private static boolean cancelPotentialWork(Uri uri, ImageView imageView) {
        final BitmapWorkerTask bitmapWorkerTask = getBitmapWorkerTask(imageView);

        if (bitmapWorkerTask != null) {
            final Uri bitmapUri = bitmapWorkerTask.uri;

            if (bitmapUri == null || bitmapUri != uri) {
                bitmapWorkerTask.cancel(true);
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
                final AsyncDrawable asyncDrawable = (AsyncDrawable) drawable;
                return asyncDrawable.getBitmapWorkerTask();
            }
        }

        return null;
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}


