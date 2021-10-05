package com.mendelu.fbe.bookshelf.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.mendelu.fbe.bookshelf.model.Book
import com.mendelu.fbe.bookshelf.model.BookMark

@Dao
interface BookMarkDAO {
    @Query("SELECT * FROM bookmarks")
    fun getAll(): LiveData<MutableList<BookMark>>

    @Query("SELECT * FROM bookmarks WHERE id = :id")
    suspend fun findById(id: Long): BookMark

    @Insert
    suspend fun insert(bookMark: BookMark): Long

    @Delete
    suspend fun delete(bookMark: BookMark)

    @Update
    suspend fun update(bookMark: BookMark)


    @Query("SELECT * FROM bookMarks")
    suspend fun getAllWithoutSuspend():MutableList<BookMark>

}