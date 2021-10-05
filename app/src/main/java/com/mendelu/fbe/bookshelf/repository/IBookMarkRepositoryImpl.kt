package com.mendelu.fbe.bookshelf.repository

import androidx.lifecycle.LiveData
import com.mendelu.fbe.bookshelf.dao.BookMarkDAO
import com.mendelu.fbe.bookshelf.dao.BookWithBookMarksDAO
import com.mendelu.fbe.bookshelf.model.BookMark

class IBookMarkRepositoryImpl(private val bookMarksDAO: BookMarkDAO) : IBookMarkMarkRepository {
    override fun getAll(): LiveData<MutableList<BookMark>> {
        return bookMarksDAO.getAll()
    }

    override suspend fun findById(id: Long): BookMark {
        return bookMarksDAO.findById(id)
    }

    override suspend fun insert(bookMark: BookMark): Long {
        return bookMarksDAO.insert(bookMark)
    }

    override suspend fun delete(bookMark: BookMark) {
        bookMarksDAO.delete(bookMark)
    }

    override suspend fun update(bookMark: BookMark) {
        bookMarksDAO.update(bookMark)
    }

    override suspend fun getAllWithoutSuspend():MutableList<BookMark>
    {
        return bookMarksDAO.getAllWithoutSuspend()
    }

}