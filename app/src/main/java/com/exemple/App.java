package com.exemple;

import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;

import com.exemple.dagger.application.AppComponent;
import com.exemple.dagger.application.AppModule;
import com.exemple.dagger.application.DaggerAppComponent;

public class App extends Application {

    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        initDagger();
    }

    private void initDagger() {
        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }

    @NonNull
    public static App get(@NonNull Context context) {
        return (App) context.getApplicationContext();
    }

    public AppComponent appComponent() {
        return appComponent;
    }
}
