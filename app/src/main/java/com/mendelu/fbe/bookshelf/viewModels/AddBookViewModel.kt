package com.mendelu.fbe.bookshelf.viewModels

import androidx.lifecycle.ViewModel
import com.mendelu.fbe.bookshelf.model.Book
import com.mendelu.fbe.bookshelf.repository.IBooksRepository
import cz.mendelu.fbe.mytodo.architecture.BaseViewModel

class AddBookViewModel(private val bookRepository: IBooksRepository) : BaseViewModel(){
    var id: Long? = null
    var book: Book
    init {
        book = Book("")
    }

    suspend fun findById(id:Long):Book
    {
        return bookRepository.findById(id)
    }
     suspend fun insert(book: Book):Long
    {
        return bookRepository.insert(book)
    }



}