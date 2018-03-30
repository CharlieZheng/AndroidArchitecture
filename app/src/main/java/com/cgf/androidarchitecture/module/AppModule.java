package com.cgf.androidarchitecture.module;

import android.app.Application;
import android.arch.persistence.room.Room;

import com.cgf.androidarchitecture.repository.dataSource.db.AppDatabase;
import com.cgf.androidarchitecture.repository.dataSource.db.DaoBook;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(includes = ViewModelModule.class)
//创建的Module都可以写在这里被AppModule包括，也可以写在AppComponent中的modules={...}里面与AppMoudle同级
public class AppModule {

    @Singleton
    @Provides
    AppDatabase f1(Application app) {
        return Room.databaseBuilder(app, AppDatabase.class, "DouBan.db").build();
    }

    @Singleton
    @Provides
    DaoBook f2(AppDatabase db) {
        return db.daoBook();
    }

}