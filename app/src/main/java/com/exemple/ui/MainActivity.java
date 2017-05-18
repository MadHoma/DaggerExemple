package com.exemple.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.exemple.App;
import com.exemple.R;

import javax.inject.Inject;

import okhttp3.OkHttpClient;

public class MainActivity extends AppCompatActivity {

    @Inject OkHttpClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        App.get(this).appComponent().activityComponent().inject(this);

        Log.d("Inject","isEmpty"+ (client == null));
    }



}
