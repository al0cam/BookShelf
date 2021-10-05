package com.mendelu.fbe.bookshelf.repository

import androidx.lifecycle.LiveData
import com.mendelu.fbe.bookshelf.model.Book

interface IBooksRepository {
    fun getAll(): LiveData<MutableList<Book>>
    suspend fun findById(id: Long): Book
    suspend fun insert(book: Book): Long
    suspend fun delete(book: Book)
    suspend fun update(book: Book)
    suspend fun checkState(id: Long, isFavourite: Boolean)
    suspend fun getAllWithoutSuspend():MutableList<Book>

}