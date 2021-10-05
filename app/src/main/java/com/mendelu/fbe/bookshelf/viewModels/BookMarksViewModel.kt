package com.mendelu.fbe.bookshelf.viewModels

import androidx.lifecycle.LiveData
import com.mendelu.fbe.bookshelf.model.Book
import com.mendelu.fbe.bookshelf.model.BookMark
import com.mendelu.fbe.bookshelf.repository.IBookMarkMarkRepository
import com.mendelu.fbe.bookshelf.repository.IBooksRepository
import cz.mendelu.fbe.mytodo.architecture.BaseViewModel

class BookMarksViewModel(private val bookRepository: IBooksRepository, private val bookMarksRepository: IBookMarkMarkRepository) : BaseViewModel() {

    suspend fun findBookById(id: Long): Book
    {
        return bookRepository.findById(id)
    }
    fun getAllBookmarks(): LiveData<MutableList<BookMark>>
    {
        return bookMarksRepository.getAll()
    }
    suspend fun delete(id: Long)
    {
        bookMarksRepository.delete(bookMarksRepository.findById(id))
    }
}