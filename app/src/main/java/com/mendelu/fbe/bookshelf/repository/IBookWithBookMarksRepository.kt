package com.mendelu.fbe.bookshelf.repository

import androidx.lifecycle.LiveData
import com.mendelu.fbe.bookshelf.model.BookWithBookMarks

interface IBookWithBookMarksRepository {
    fun getAll(): LiveData<MutableList<BookWithBookMarks>>
    suspend fun findById(id: Long): BookWithBookMarks
    suspend fun insert(bookWithBookMarks: BookWithBookMarks): Long
    suspend fun delete(bookWithBookMarks: BookWithBookMarks)
    suspend fun update(bookWithBookMarks: BookWithBookMarks)

}