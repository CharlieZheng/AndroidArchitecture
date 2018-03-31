package com.iousave.www.repository.dataSource.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import com.iousave.www.pojo.Book

@Database(entities = arrayOf(Book::class), version = 2)
@TypeConverters(Tc::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun daoBook(): DaoBook
}