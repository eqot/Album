package com.eqot.android.utils.view.awesomegridview;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import java.util.ArrayList;

public class AwesomeGridView extends LinearLayout {

    private static final String TAG = "AwesomeGridView";

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    public AwesomeGridView(Context context) {
        super(context);

        init();
    }

    public AwesomeGridView(Context context, AttributeSet attrs) {
        super(context, attrs);

        init();
    }

    private void init() {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        inflater.inflate(R.layout.view_main, this);

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);

        mRecyclerView.addOnItemTouchListener(new AwesomeTouchListener(getContext()));
    }

    public void setDataset(ArrayList<Object> dataset) {
        mAdapter = new AwesomeGridAdapter(dataset, this);
        mRecyclerView.setAdapter(mAdapter);
    }

    public void addSpanCount(int delta) {
        if (mLayoutManager instanceof GridLayoutManager) {
            int span = ((GridLayoutManager) mLayoutManager).getSpanCount();
            ((GridLayoutManager) mLayoutManager).setSpanCount(span + delta);

            mAdapter.notifyItemChanged(0);
        }
    }
}
