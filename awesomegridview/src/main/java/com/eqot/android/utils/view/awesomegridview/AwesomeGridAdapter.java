package com.eqot.android.utils.view.awesomegridview;

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
import com.eqot.android.utils.image.smartimage.SmartImageLoader;

import java.util.ArrayList;

public class AwesomeGridAdapter extends RecyclerView.Adapter<AwesomeGridAdapter.ViewHolder> {
    private static final String TAG = "AwesomeGridAdapter";
    private ArrayList<Object> mDataset;
    private Resources mResource;
    private View mView;

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

        mResource = mView.getResources();
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
            Bitmap bitmap = BitmapFactory.decodeResource(mResource, (Integer) mDataset.get(position));
            holder.mImageView.setImageBitmap(bitmap);
        } else if (mDataset.get(0) instanceof String) {
            holder.mTextView.setText(mDataset.get(position).toString());
        } else {
            String data = ((Media) mDataset.get(position)).mData;
//            Log.d(TAG, data);
//            holder.mTextView.setText(((Media) mDataset.get(position)).mData);

            Uri uri = ((Media) mDataset.get(position)).mUri;
//            Log.d(TAG, uri.toString());

//                InputStream inputStream = mView.getContext().getContentResolver()
//                        .openInputStream(uri);
//                final BitmapFactory.Options options = new BitmapFactory.Options();
//                options.inSampleSize = 4;
//                Bitmap bitmap = BitmapFactory.decodeStream(inputStream, null, options);

            Bitmap bitmap = SmartImageLoader.decodeUri(
                    mView.getContext().getContentResolver(), uri, 128, 128);
            holder.mImageView.setImageBitmap(bitmap);
        }
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}


