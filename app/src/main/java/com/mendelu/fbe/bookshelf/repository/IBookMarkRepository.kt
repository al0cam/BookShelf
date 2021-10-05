package com.mendelu.fbe.bookshelf.repository

import androidx.lifecycle.LiveData
import com.mendelu.fbe.bookshelf.model.BookMark

interface IBookMarkMarkRepository {
    fun getAll(): LiveData<MutableList<BookMark>>
    suspend fun findById(id: Long): BookMark
    suspend fun insert(bookmark: BookMark): Long
    suspend fun delete(bookMark: BookMark)
    suspend fun update(bookMark: BookMark)
    suspend fun getAllWithoutSuspend():MutableList<BookMark>

}