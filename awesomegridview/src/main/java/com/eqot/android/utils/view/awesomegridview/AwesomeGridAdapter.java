package com.eqot.android.utils.view.awesomegridview;

import android.content.ContentResolver;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
    private Resources mResources;
    private ContentResolver mContentResolver;

    private static Bitmap sPlaceHolderBitmap = null;

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
        mResources = view.getResources();
        mContentResolver = view.getContext().getContentResolver();

        if (sPlaceHolderBitmap == null) {
            sPlaceHolderBitmap = BitmapFactory.decodeResource(mResources, R.drawable.ic_launcher);
        }
    }

    @Override
    public AwesomeGridAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Uri uri = ((Media) mDataset.get(position)).mUri;

        if (AsyncDrawable.cancelPotentialWork(holder.mImageView, uri)) {
            final BitmapWorkerTask task = new BitmapWorkerTask(holder.mImageView, mContentResolver);
            final AsyncDrawable drawable = new AsyncDrawable(mResources, sPlaceHolderBitmap, task);
            holder.mImageView.setImageDrawable(drawable);
            task.execute(uri);
        }
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}


