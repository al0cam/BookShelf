package com.mendelu.fbe.bookshelf.repository

import androidx.lifecycle.LiveData
import com.mendelu.fbe.bookshelf.dao.BookWithBookMarksDAO
import com.mendelu.fbe.bookshelf.model.BookWithBookMarks

class IBookWithBookMarksRepositoryImpl(private val bookWithBookMarksDAO: BookWithBookMarksDAO): IBookWithBookMarksRepository {
    override fun getAll(): LiveData<MutableList<BookWithBookMarks>> {
        return bookWithBookMarksDAO.getAll()
    }

    override suspend fun findById(id: Long): BookWithBookMarks {
        return bookWithBookMarksDAO.findById(id)
    }

    override suspend fun insert(bookWithBookMarks: BookWithBookMarks): Long {
        return bookWithBookMarksDAO.insert(bookWithBookMarks)
    }

    override suspend fun delete(bookWithBookMarks: BookWithBookMarks) {
        bookWithBookMarksDAO.delete(bookWithBookMarks)
    }

    override suspend fun update(bookWithBookMarks: BookWithBookMarks) {
        bookWithBookMarksDAO.update(bookWithBookMarks)
    }

}