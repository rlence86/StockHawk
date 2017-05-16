package com.udacity.stockhawk.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import com.github.mikephil.charting.data.Entry;
import com.udacity.stockhawk.R;

import java.util.ArrayList;
import java.util.Collections;

public class DetailedViewActivity extends AppCompatActivity {

    private String mSymbol;
    private String mHistory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_view);

        Intent intent = getIntent();
        mSymbol = intent.getStringExtra(MainActivity.SELECTED_SYMBOL);
        mHistory = intent.getStringExtra(MainActivity.SELECTED_HISTORY);

        getSupportActionBar().setTitle(mSymbol);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ArrayList<Entry> chartEntries = this.generateChartEntries(mHistory);


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private ArrayList<Entry> generateChartEntries(String history){
        ArrayList<Entry> result = new ArrayList<>();
        String[] dataPairs = history.split("\n");

        for (String pair : dataPairs) {
            String[] entry = pair.split(", ");
            Entry entry1 = new Entry(Float.valueOf(entry[0]), Float.valueOf(entry[1]));
            result.add(entry1);
        }
        Collections.reverse(result);
        return result;
    }
}
