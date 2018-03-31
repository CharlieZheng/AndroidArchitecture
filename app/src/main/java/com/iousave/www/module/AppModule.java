package com.iousave.www.module;

import android.app.Application;
import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.migration.Migration;

import com.iousave.www.Constants;
import com.iousave.www.repository.dataSource.db.AppDatabase;
import com.iousave.www.repository.dataSource.db.DaoBook;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(includes = ViewModelModule.class)
//创建的Module都可以写在这里被AppModule包括，也可以写在AppComponent中的modules={...}里面与AppMoudle同级
public class AppModule {
    public static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            // Migration with simple schema changes
            database.execSQL("ALTER TABLE Book "
                    + " ADD COLUMN author TEXT default \"\" NOT NULL");


           /*
            // Migrations with complex schema changes
            // Create the new table
            database.execSQL(
                    "CREATE TABLE users_new (userid TEXT, username TEXT, last_update INTEGER, PRIMARY KEY(userid))");

            // Copy the data
            database.execSQL(
                    "INSERT INTO users_new (userid, username, last_update) SELECT userid, username, last_update FROM users");
            // Remove the old table
            database.execSQL("DROP TABLE users");
            // Change the table name to the correct one
            database.execSQL("ALTER TABLE users_new RENAME TO users");*/
        }
    };

    @Singleton
    @Provides
    AppDatabase f1(Application app) {
        return Room.databaseBuilder(app, AppDatabase.class, Constants.DB_NAME)
                .addMigrations(MIGRATION_1_2)
//                .fallbackToDestructiveMigration() // This operation may will clear data in database
                .build();
    }

    @Singleton
    @Provides
    DaoBook f2(AppDatabase db) {
        return db.daoBook();
    }

}