package com.eqot.android.app.album;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.eqot.android.utils.view.awesomegridview.AwesomeGridView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Object> mDatasetImage;
    private ArrayList<Object> mDatasetText;

    private AwesomeGridView mGridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
                mGridView.addSpanCount(1);
            }
        });

        execute();
    }

    private void execute() {
        mGridView = (AwesomeGridView) findViewById(R.id.grid_view);

        mDatasetImage = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            mDatasetImage.add(R.drawable.ic_launcher);
        }
        mGridView.setDataset(mDatasetImage);

//        mDatasetText = new ArrayList<>();
//        for (int i = 0; i < 100; i++) {
//            mDatasetText.add("" + i);
//        }
//        gridView.setDataset(mDatasetText);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
