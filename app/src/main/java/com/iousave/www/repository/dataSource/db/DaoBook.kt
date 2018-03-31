package com.iousave.www.repository.dataSource.db

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.iousave.www.pojo.Book

@Dao
interface DaoBook {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertBooks(vararg books: Book)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertBooks(bookList: ArrayList<Book>)


    @Query("SELECT * FROM Book")
    fun loadAllBooks(): LiveData<Array<Book>>

    @Query("SELECT * FROM Book  ")
    fun bookSearch(): LiveData<Array<Book>>

}