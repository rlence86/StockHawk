package com.udacity.stockhawk.widget;

import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Binder;
import android.widget.AdapterView;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.udacity.stockhawk.R;
import com.udacity.stockhawk.data.Contract;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

public class StockWidgetService extends RemoteViewsService {
    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new StockRemoteViewFactory();
    }


    public class StockRemoteViewFactory implements RemoteViewsService.RemoteViewsFactory {

        private Cursor mCursor = null;

        @Override
        public void onDestroy() {
            if (mCursor != null) {
                mCursor.close();
                mCursor = null;
            }
        }

        @Override
        public void onCreate() {

        }

        public int getCount() {
            if(mCursor == null){
                return 0;
            }
            return mCursor.getCount();
        }

        public RemoteViews getViewAt(int position) {
            if(position == AdapterView.INVALID_POSITION || mCursor == null || !mCursor.moveToPosition(position)){
                return null;
            }

            RemoteViews rv = new RemoteViews(getPackageName(), R.layout.list_item_quote);
            String symbol = mCursor.getString(Contract.Quote.POSITION_SYMBOL);
            Float price = mCursor.getFloat(Contract.Quote.POSITION_PRICE);
            Float absoluteChange = mCursor.getFloat(Contract.Quote.POSITION_ABSOLUTE_CHANGE);

            DecimalFormat dollarFormat = (DecimalFormat) NumberFormat.getCurrencyInstance(Locale.US);
            DecimalFormat dollarFormatWithPlus = (DecimalFormat) NumberFormat.getCurrencyInstance(Locale.US);
            dollarFormatWithPlus.setPositivePrefix("+");
            dollarFormatWithPlus.setMaximumFractionDigits(2);
            dollarFormat.setMaximumFractionDigits(2);
            dollarFormat.setMinimumFractionDigits(2);
            dollarFormatWithPlus.setMinimumFractionDigits(2);

            rv.setTextViewText(R.id.symbol, symbol);
            rv.setTextViewText(R.id.price, dollarFormat.format(price));
            rv.setTextViewText(R.id.change,dollarFormatWithPlus.format(absoluteChange));

            return rv;
        }

        public RemoteViews getLoadingView() {
            return null;
        }

        public int getViewTypeCount() {
            return 1;
        }

        public long getItemId(int position) {
            if(mCursor.moveToPosition(position)){
                return mCursor.getLong(Contract.Quote.POSITION_ID);
            } else {
                return position;
            }
        }

        public boolean hasStableIds() {
            return true;
        }

        public void onDataSetChanged() {
            if (mCursor != null) {
                mCursor.close();
            }
            //Clear calling identity to be able to call the provider
            final long identityToken = Binder.clearCallingIdentity();
            mCursor = getContentResolver().query(Contract.Quote.URI,
                    Contract.Quote.QUOTE_COLUMNS.toArray(new String[]{}),
                    null,
                    null,
                    Contract.Quote.COLUMN_SYMBOL);
            //Restore the calling identity after calling the provider
            Binder.restoreCallingIdentity(identityToken);
        }
    }

}
