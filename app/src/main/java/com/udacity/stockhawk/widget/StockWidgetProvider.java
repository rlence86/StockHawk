package com.udacity.stockhawk.widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.TaskStackBuilder;
import android.widget.RemoteViews;

import com.udacity.stockhawk.R;
import com.udacity.stockhawk.ui.DetailedViewActivity;
import com.udacity.stockhawk.ui.MainActivity;

/**
 * Implementation of App Widget functionality.
 */
public class StockWidgetProvider extends AppWidgetProvider {

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        for (int widgetId : appWidgetIds){
            RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.widget_stock_list);
            setRemoteAdapter(context, remoteViews);
            Intent intent = new Intent(context, MainActivity.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
            remoteViews.setOnClickPendingIntent(R.id.widget, pendingIntent);
            appWidgetManager.notifyAppWidgetViewDataChanged(widgetId, R.id.widget_list);
            appWidgetManager.updateAppWidget(widgetId, remoteViews);
        }
    }

    private void setRemoteAdapter(Context context, @NonNull final RemoteViews views){
        views.setRemoteAdapter(R.id.widget_list, new Intent(context, StockWidgetService.class));
    }
}

