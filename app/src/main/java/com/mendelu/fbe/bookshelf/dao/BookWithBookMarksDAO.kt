package com.mendelu.fbe.bookshelf.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.mendelu.fbe.bookshelf.model.BookWithBookMarks


@Dao
interface BookWithBookMarksDAO {
    @Insert
    suspend fun insert(bookWithBookMarks: BookWithBookMarks): Long

    @Delete
    suspend fun delete(bookWithBookMarks: BookWithBookMarks)

    @Update
    suspend fun update(bookWithBookMarks: BookWithBookMarks)

    @Transaction
    @Query("SELECT * FROM books")
    fun getAll(): LiveData<MutableList<BookWithBookMarks>>

    @Transaction
    @Query("SELECT * FROM books where id = :id")
    fun findById(id: Long): BookWithBookMarks

}