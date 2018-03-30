package com.cgf.androidarchitecture.repository.dataSource.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.cgf.androidarchitecture.pojo.Book

@Database(entities = arrayOf(Book::class), version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun daoBook(): DaoBook
}