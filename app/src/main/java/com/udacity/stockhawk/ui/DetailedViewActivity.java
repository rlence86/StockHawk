package com.udacity.stockhawk.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.udacity.stockhawk.R;

public class DetailedViewActivity extends AppCompatActivity {

    private String mSymbol;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_view);

        Intent intent = getIntent();
        mSymbol = intent.getStringExtra(MainActivity.SELECTED_SYMBOL);

        getSupportActionBar().setTitle(mSymbol);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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
