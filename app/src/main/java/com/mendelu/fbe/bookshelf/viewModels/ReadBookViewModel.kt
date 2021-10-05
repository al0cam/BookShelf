package com.mendelu.fbe.bookshelf.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mendelu.fbe.bookshelf.model.Book
import com.mendelu.fbe.bookshelf.repository.IBooksRepository
import cz.mendelu.fbe.mytodo.architecture.BaseViewModel
import cz.mendelu.fbe.mytodo.di.repositoryModule

class ReadBookViewModel(private val bookRepository: IBooksRepository) :  BaseViewModel(){
    var book: Book
    init {
        book = Book("")
    }
    suspend fun findById(id: Long): Book
    {
        book = bookRepository.findById(id)
        return book
    }
}