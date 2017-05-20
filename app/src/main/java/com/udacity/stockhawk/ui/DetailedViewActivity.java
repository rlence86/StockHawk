package com.udacity.stockhawk.ui;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
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
    public static final String CHART_HISTORY = "chart_history";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_view);

        Intent intent = getIntent();
        mSymbol = intent.getStringExtra(MainActivity.SELECTED_SYMBOL);
        mHistory = intent.getStringExtra(MainActivity.SELECTED_HISTORY);

        getSupportActionBar().setTitle(mSymbol);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Bundle fragmentBundle = new Bundle();
        fragmentBundle.putString(CHART_HISTORY, mHistory);

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        DetailFragment fragment = new DetailFragment();
        fragment.setArguments(fragmentBundle);
        fragmentTransaction.add(R.id.activity_detailed_view, fragment);
        fragmentTransaction.commit();
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
}
