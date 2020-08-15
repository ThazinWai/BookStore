package com.example.bookstore;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Book>> {

    private static final String REQUEST_URL =
            "https://api.itbook.store/1.0/search/mongodb";
    private BookAdapter mAdapter;
    private static final String LOG_TAG = MainActivity.class.getName();
    private static final int Book_loader_id = -1;
    SwipeRefreshLayout swipeRefreshLayout;
    Toolbar toolbar;


    @Override
    protected void onCreate(final Bundle savedInstanceState) {

        Log.i(LOG_TAG, "TEST: OnCreate method called....");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar)findViewById(R.id.toolbar);
       setSupportActionBar(toolbar);


        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();


        if (networkInfo != null && networkInfo.isConnected()) {

            LoaderManager loaderManager = getLoaderManager();
            loaderManager.initLoader(Book_loader_id, null, this);
        } else {
            Toast.makeText(getApplicationContext(), "No internet connection ", Toast.LENGTH_SHORT).show();
        }

        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipelayout);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                LoaderManager loaderManager = getLoaderManager();
                loaderManager.initLoader(Book_loader_id, null, MainActivity.this);


            }
        });


        ListView bookListView = (ListView) findViewById(R.id.list);
        mAdapter = new BookAdapter(this, new ArrayList<Book>());
        bookListView.setAdapter(mAdapter);

        bookListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Book book = mAdapter.getItem(i);
                Uri bookUri = Uri.parse(book.getUrl());
                Intent webIntent = new Intent(Intent.ACTION_VIEW, bookUri);
                startActivity(webIntent);
            }
        });


    }





    @Override
    public Loader<List<Book>> onCreateLoader(int id, Bundle bundle) {
        Log.i(LOG_TAG, "TEST: onCreateLoader called...");

        return new BookLoader(this, REQUEST_URL);
    }

    @Override
    public void onLoadFinished(Loader<List<Book>> loader, List<Book> books) {

        Log.i(LOG_TAG, "TEST: conLoadFinished called...");
        swipeRefreshLayout.setRefreshing(false);

        mAdapter.clear();

        if (books != null && !books.isEmpty()) {
            mAdapter.addAll(books);
        }
    }

    @Override
    public void onLoaderReset(Loader<List<Book>> loader) {

        Log.i(LOG_TAG, "TEST: onLoadReset is called...");

        mAdapter.clear();

    }

}
