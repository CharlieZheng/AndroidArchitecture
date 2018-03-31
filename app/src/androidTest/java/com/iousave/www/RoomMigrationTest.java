package com.iousave.www;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.db.framework.FrameworkSQLiteOpenHelperFactory;
import android.arch.persistence.room.testing.MigrationTestHelper;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.iousave.www.module.AppModule;
import com.iousave.www.repository.dataSource.db.AppDatabase;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class RoomMigrationTest {
    @Rule
    public MigrationTestHelper testHelper =
            new MigrationTestHelper(
                    InstrumentationRegistry.getInstrumentation(),
                    AppDatabase.class.getCanonicalName(),
                    new FrameworkSQLiteOpenHelperFactory());

    @Test
    public void useAppContext() throws Exception {
        // Create the database with version 2
        SupportSQLiteDatabase db =
                testHelper.createDatabase(Constants.DB_NAME, 1);
        db = testHelper.runMigrationsAndValidate(Constants.DB_NAME, 2, true, AppModule.MIGRATION_1_2);
    }
}
