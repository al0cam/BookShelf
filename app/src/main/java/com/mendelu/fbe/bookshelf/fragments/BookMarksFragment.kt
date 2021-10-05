package com.mendelu.fbe.bookshelf.fragments

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mendelu.fbe.bookshelf.databinding.BookMarksFragmentBinding
import com.mendelu.fbe.bookshelf.databinding.RowBookmarkBinding
import com.mendelu.fbe.bookshelf.model.Book
import com.mendelu.fbe.bookshelf.model.BookMark
import com.mendelu.fbe.bookshelf.viewModels.BookMarksViewModel
import cz.mendelu.fbe.mytodo.architecture.BaseFragment
import kotlinx.coroutines.launch

class BookMarksFragment : BaseFragment<BookMarksFragmentBinding, BookMarksViewModel>(BookMarksViewModel::class) {

    override val bindingInflater: (LayoutInflater) -> BookMarksFragmentBinding
        get() = BookMarksFragmentBinding::inflate


    private val bookMarkList: MutableList<BookMark> = mutableListOf()
    private lateinit var adapter: BookAdapter
    private lateinit var layoutManager: LinearLayoutManager


    inner class BookAdapter : RecyclerView.Adapter<BookAdapter.BookViewHolder>() {

        inner class BookViewHolder(val binding: RowBookmarkBinding) : RecyclerView.ViewHolder(binding.root){

        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
            return BookViewHolder(RowBookmarkBinding.inflate(layoutInflater, parent, false))
        }

        override fun getItemCount(): Int {
            return bookMarkList.size
        }

        override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
            val bookMark = bookMarkList.get(position)
            var book: Book = Book("")
            lifecycleScope.launch {
                book = viewModel.findBookById(bookMark.bookId!!)
                if(book != null)
                    holder.binding.bookName.text = book.name
            }

            holder.binding.page.text = bookMark.page.toString()
            holder.binding.bookmark.text = bookMark.bookMarkedText

            holder.binding.deleteButton.setOnClickListener{
                lifecycleScope.launch {
                    viewModel.delete(bookMarkList.get(holder.adapterPosition).id!!)
                }
            }
        }
    }

    inner class BookDiffUtils(private val oldList: MutableList<BookMark>,
                              private val newList: MutableList<BookMark>) : DiffUtil.Callback() {


        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition].id == newList[newItemPosition].id
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition] == newList[newItemPosition]
        }

        override fun getOldListSize() = oldList.size
        override fun getNewListSize() = newList.size

    }

    override fun initViews() {
        adapter = BookAdapter()
        layoutManager = LinearLayoutManager(requireContext())

        binding.bookmarkList.layoutManager = layoutManager
        binding.bookmarkList.adapter = adapter
        viewModel.getAllBookmarks().observe(viewLifecycleOwner, Observer {
            it?.let {
                val diffCallback = BookDiffUtils(bookMarkList, it)
                val diffResult = DiffUtil.calculateDiff(diffCallback)
                diffResult.dispatchUpdatesTo(adapter)
                bookMarkList.clear()
                bookMarkList.addAll(it)
            }
        })
    }

}