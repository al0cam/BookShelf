package com.mendelu.fbe.bookshelf.repository

import androidx.lifecycle.LiveData
import com.mendelu.fbe.bookshelf.dao.BookDAO
import com.mendelu.fbe.bookshelf.model.Book

class IBookRepositoryImpl(private val bookDAO: BookDAO) : IBooksRepository {
    override fun getAll(): LiveData<MutableList<Book>> {
        return bookDAO.getAll()
    }

    override suspend fun findById(id: Long): Book {
        return bookDAO.findById(id)
    }

    override suspend fun insert(book: Book): Long {
        return bookDAO.insert(book)
    }

    override suspend fun delete(book: Book) {
        bookDAO.delete(book)
    }

    override suspend fun update(book: Book) {
        bookDAO.update(book)
    }

    override suspend fun checkState(id: Long, isFavourite: Boolean) {
        bookDAO.checkState(id, isFavourite)
    }

    override suspend fun getAllWithoutSuspend():MutableList<Book>
    {
        return bookDAO.getAllWithoutSuspend()
    }
}