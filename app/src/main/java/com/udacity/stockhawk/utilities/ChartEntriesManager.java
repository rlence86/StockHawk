package com.udacity.stockhawk.utilities;

import com.github.mikephil.charting.data.Entry;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by darke on 20/05/2017.
 */

public class ChartEntriesManager {

    public static ArrayList<Entry> generateChartEntries(String history){
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
