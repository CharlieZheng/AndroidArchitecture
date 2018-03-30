package com.ljp.androidarchitecture.repository.dataSource.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.ljp.androidarchitecture.pojo.Book

@Database(entities = arrayOf(Book::class), version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun daoBook(): DaoBook
}