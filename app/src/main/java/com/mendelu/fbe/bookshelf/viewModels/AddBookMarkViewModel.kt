package com.mendelu.fbe.bookshelf.viewModels

import androidx.lifecycle.ViewModel
import com.mendelu.fbe.bookshelf.model.Book
import com.mendelu.fbe.bookshelf.model.BookMark
import com.mendelu.fbe.bookshelf.repository.IBookMarkMarkRepository
import com.mendelu.fbe.bookshelf.repository.IBooksRepository
import com.pdftron.pdf.Bookmark
import cz.mendelu.fbe.mytodo.architecture.BaseViewModel

class AddBookMarkViewModel(private var repository: IBookMarkMarkRepository) : BaseViewModel() {
    var bookmark: BookMark
    init {
        bookmark = BookMark("")
    }
    suspend fun insert(bookMark: BookMark)
    {
        repository.insert(bookMark)
    }

}