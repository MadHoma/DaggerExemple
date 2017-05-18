package com.exemple.dagger.application;


import com.exemple.BuildConfig;
import com.exemple.Configuration;
import com.google.gson.Gson;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NetworkModule {

    @Singleton
    @Provides
    OkHttpClient provideOkHttpClient() {
        return getOkHttpBuilder().build();
    }

    OkHttpClient.Builder getOkHttpBuilder() {

        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        if (BuildConfig.DEBUG) {
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        } else {
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.NONE);
        }
        builder.retryOnConnectionFailure(false);
        builder.addInterceptor(loggingInterceptor);
       builder.readTimeout(Configuration.CONNECTION_TIMEOUT, TimeUnit.SECONDS);
        builder.connectTimeout(Configuration.CONNECTION_TIMEOUT, TimeUnit.SECONDS);
        builder.writeTimeout(Configuration.CONNECTION_TIMEOUT, TimeUnit.SECONDS);

        return builder;
    }

    @Singleton
    @Provides
    Retrofit provideRetrofit(OkHttpClient okHttpClient, Gson gson) {
        return new Retrofit.Builder()
                .baseUrl(Configuration.HOSTNAME)
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }

}
