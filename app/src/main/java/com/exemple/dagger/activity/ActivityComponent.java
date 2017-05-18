package com.exemple.dagger.activity;

import com.exemple.ui.MainActivity;

import dagger.Subcomponent;

@ActivityScope
@Subcomponent(modules = {
        //подключаем модули которые будут для каждого активити
})
public interface ActivityComponent {


    void inject(MainActivity mainActivity);
}
