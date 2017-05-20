package com.udacity.stockhawk.ui;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.LineScatterCandleRadarDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.udacity.stockhawk.R;
import com.udacity.stockhawk.utilities.ChartEntriesManager;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;

public class DetailFragment extends Fragment {
    private LineChart mLineChart;
    private ArrayList<Entry> mEntries;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mLineChart = (LineChart) getView().findViewById(R.id.chart);
        receiveAndSetData();
    }


    private void receiveAndSetData(){
        Bundle bundle = getArguments();
        String history = bundle.getString(DetailedViewActivity.CHART_HISTORY);
        ArrayList<Entry> chartEntries = ChartEntriesManager.generateChartEntries(history);
        LineDataSet set1 = new LineDataSet(chartEntries, "DataSet 1");
        ArrayList<ILineDataSet> dataSets = new ArrayList<ILineDataSet>();
        dataSets.add(set1);
        LineData data = new LineData(dataSets);
        mLineChart.setData(data);
    }
}
