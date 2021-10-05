package com.mendelu.fbe.bookshelf.fragments

import android.view.LayoutInflater
import androidx.lifecycle.lifecycleScope
import com.mendelu.fbe.bookshelf.databinding.MainPageFragmentBinding
import com.mendelu.fbe.bookshelf.model.Book
import com.mendelu.fbe.bookshelf.model.BookMark
import com.mendelu.fbe.bookshelf.viewModels.MainPageViewModel
import cz.mendelu.fbe.mytodo.architecture.BaseFragment
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class MainPageFragment() : BaseFragment<MainPageFragmentBinding, MainPageViewModel>(MainPageViewModel::class) {

    override val bindingInflater: (LayoutInflater) -> MainPageFragmentBinding
        get() = MainPageFragmentBinding::inflate

    override fun initViews() {
        lifecycleScope.launch {
            binding.favouriteBook.hideButtons()
            binding.lastAddedBook.hideButtons()
            binding.lastBookmark.hideButtons()

            var books = viewModel.getAllWithoutSuspend()
            var bookMarks = viewModel.getAllWithoutSuspendBookMarks()

            if(books.isNotEmpty())
            {
                binding.lastAddedBook.unHideLabels()
                var date = books.first().lastReadDate
                val sdf = SimpleDateFormat("yyyy-MM-dd")
                var date2  = sdf.parse(date)
                var id = books.first().id
                var favourite:Long? = null
                for (item in books)
                {
                    if (date2 < sdf.parse(item.lastReadDate)){
                        date2 = sdf.parse(item.lastReadDate)
                        id = item.id
                    }
                    if (item.isFavourite!!)
                        favourite = item.id!!
                }
                var book = viewModel.findByIdBook(id!!)
                binding.lastAddedBook.setBookName(book.name!!)
                binding.lastAddedBook.seAuthor(book.author!!)

                if (favourite != null)
                {
                    binding.favouriteBook.unHideLabels()
                    book = viewModel.findByIdBook(id!!)
                    binding.favouriteBook.setBookName(book.name!!)
                    binding.favouriteBook.seAuthor(book.author!!)
                }
                else
                {
                    binding.favouriteBook.hideLabels()
                    binding.favouriteBook.seAuthor("No favourite")
                }

            }
            else
            {
                binding.lastAddedBook.hideLabels()
                binding.favouriteBook.hideLabels()
            }

            if(bookMarks.isNotEmpty())
            {
                binding.lastBookmark.unHideLabels()
                var date = bookMarks.first().dateCreated
                val sdf = SimpleDateFormat("yyyy-MM-dd")
                var date2 = sdf.parse(date)

                var id = bookMarks.first().id
                for (item in bookMarks)
                {
                    if (date2 < sdf.parse(item.dateCreated)){
                        date2 = sdf.parse(item.dateCreated)
                        id = item.id
                    }
                }
                var bookMark = viewModel.findByIdBookMark(id!!)
                if(viewModel.findByIdBook(bookMark.bookId!!) != null)
                    binding.lastBookmark.setBookName(viewModel.findByIdBook(bookMark.bookId!!).name!!)

                binding.lastBookmark.setPage(bookMark.page!!.toString())
                binding.lastBookmark.setBookMark(bookMark.bookMarkedText!!)
            }
            else
                binding.lastBookmark.hideLabels()



        }
    }

}