package com.mendelu.fbe.bookshelf.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.mendelu.fbe.bookshelf.model.Book
import com.mendelu.fbe.bookshelf.model.BookMark
import com.mendelu.fbe.bookshelf.repository.IBookMarkMarkRepository
import com.mendelu.fbe.bookshelf.repository.IBookWithBookMarksRepository
import com.mendelu.fbe.bookshelf.repository.IBooksRepository
import cz.mendelu.fbe.mytodo.architecture.BaseViewModel

class MainPageViewModel(private val bookRepository: IBooksRepository, private val bookMarksRepository: IBookMarkMarkRepository) :  BaseViewModel() {

    suspend fun getAllWithoutSuspend():MutableList<Book>
    {
        return bookRepository.getAllWithoutSuspend()
    }
    suspend fun getAllWithoutSuspendBookMarks():MutableList<BookMark>
    {
        return bookMarksRepository.getAllWithoutSuspend()
    }

    suspend fun findByIdBook(id:Long):Book
    {
        return bookRepository.findById(id)
    }
    suspend fun findByIdBookMark(id:Long):BookMark
    {
        return bookMarksRepository.findById(id)
    }
}