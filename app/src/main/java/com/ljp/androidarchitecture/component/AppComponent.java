package com.ljp.androidarchitecture.component;

import android.app.Application;

import com.ljp.androidarchitecture.MyApplication;
import com.ljp.androidarchitecture.module.AppModule;
import com.ljp.androidarchitecture.module.MainActivityModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;

@Singleton
@Component(modules = {AndroidInjectionModule.class, AppModule.class, MainActivityModule.class})
public interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application mainActivity);

        AppComponent build();
    }

    void inject(MyApplication mainActivity);

}