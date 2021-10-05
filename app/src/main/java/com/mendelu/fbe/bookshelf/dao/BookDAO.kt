package com.mendelu.fbe.bookshelf.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.mendelu.fbe.bookshelf.model.Book
import com.mendelu.fbe.bookshelf.model.BookWithBookMarks


@Dao
interface BookDAO {
    @Query("SELECT * FROM books")
    fun getAll(): LiveData<MutableList<Book>>

    @Query("SELECT * FROM books WHERE books.id = :id")
    suspend fun findById(id: Long): Book

    @Insert
    suspend fun insert(book: Book): Long

    @Delete
    suspend fun delete(book: Book)

    @Update
    suspend fun update(book: Book)

    @Transaction
    @Query("SELECT * FROM books")
    fun getAllBookMarks(): LiveData<MutableList<BookWithBookMarks>>

    @Transaction
    @Query("SELECT * FROM books where id = :id")
    fun findByIdBookMark(id: Long): BookWithBookMarks

    @Query("UPDATE books SET isFavourite = :isFavourite WHERE id = :id")
    suspend fun checkState(id: Long, isFavourite: Boolean)

    @Query("SELECT * FROM books")
    suspend fun getAllWithoutSuspend():MutableList<Book>
}