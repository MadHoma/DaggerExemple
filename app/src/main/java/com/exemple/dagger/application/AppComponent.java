package com.exemple.dagger.application;

import com.exemple.dagger.activity.ActivityComponent;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {
        AppModule.class,
        NetworkModule.class
})
public interface AppComponent {
    ActivityComponent activityComponent();
}
