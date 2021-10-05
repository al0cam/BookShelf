package com.mendelu.fbe.bookshelf.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.mendelu.fbe.bookshelf.model.Book
import com.mendelu.fbe.bookshelf.repository.IBooksRepository
import cz.mendelu.fbe.mytodo.architecture.BaseViewModel

class ShelfViewModel(private val repository: IBooksRepository) : BaseViewModel() {

    fun getAll(): LiveData<MutableList<Book>>
    {
        return repository.getAll()
    }
    suspend fun delete(id: Long)
    {
        repository.delete(repository.findById(id))
    }
    suspend fun checkState(id: Long, isFavourit: Boolean)
    {
        repository.checkState(id, isFavourit)
    }
}