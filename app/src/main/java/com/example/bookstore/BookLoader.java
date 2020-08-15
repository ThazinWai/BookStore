package com.example.bookstore;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.util.Log;

import java.util.List;

public class BookLoader extends AsyncTaskLoader<List<Book>> {
    private final String LOG_TAG = BookLoader.class.getSimpleName();
    private String mUrl;

    public BookLoader(Context context, String url) {
        super(context);
        mUrl = url;
    }


    @Override
    protected void onStartLoading() {
        Log.i(LOG_TAG,"TEST: onStartLoading is called..");

        forceLoad();
    }

    @Override
    public List<Book> loadInBackground() {

        Log.i(LOG_TAG,"TEST: LoadInBackground is called...");

        if(mUrl==null){
            return null;
        }

        List<Book> result = QueryUtils.fetchBookData(mUrl);
        return result;
    }
}
