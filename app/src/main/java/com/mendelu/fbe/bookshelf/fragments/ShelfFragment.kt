package com.mendelu.fbe.bookshelf.fragments

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.CompoundButton
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mendelu.fbe.bookshelf.databinding.SampleBookViewBinding
import com.mendelu.fbe.bookshelf.databinding.ShelfFragmentBinding
import com.mendelu.fbe.bookshelf.model.Book
import com.mendelu.fbe.bookshelf.viewModels.ShelfViewModel
import cz.mendelu.fbe.mytodo.architecture.BaseFragment
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class ShelfFragment : BaseFragment<ShelfFragmentBinding, ShelfViewModel>(ShelfViewModel::class) {
    override val bindingInflater: (LayoutInflater) -> ShelfFragmentBinding
        get() = ShelfFragmentBinding::inflate

    private val bookList: MutableList<Book> = mutableListOf()
    private lateinit var adapter: BookAdapter
    private lateinit var layoutManager: LinearLayoutManager


    inner class BookAdapter : RecyclerView.Adapter<BookAdapter.BookViewHolder>() {

        inner class BookViewHolder(val binding: SampleBookViewBinding) : RecyclerView.ViewHolder(binding.root){
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
            return BookViewHolder(SampleBookViewBinding.inflate(layoutInflater, parent, false))
        }

        override fun getItemCount(): Int {
            return bookList.size
        }

        override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
            val book = bookList.get(position)

            holder.binding.author.text = book.author
            holder.binding.bookName.text = book.name
            holder.binding.openBookButton.setOnClickListener{
                val action = ShelfFragmentDirections.toReadBookFragment(bookList.get(holder.adapterPosition).id!!)
                findNavController().navigate(action)
            }

            if (book.isFavourite == null)
                book.isFavourite = false
            holder.binding.favourite.isChecked = book.isFavourite!!

            holder.binding.favourite.setOnCheckedChangeListener(object : CompoundButton.OnCheckedChangeListener {
                override fun onCheckedChanged(p0: CompoundButton?, p1: Boolean) {
                    lifecycleScope.launch {
                        viewModel.checkState(bookList.get(holder.adapterPosition).id!!, !book.isFavourite!!)
                    }
                }
            })
            holder.binding.deleteButton.setOnClickListener{
                lifecycleScope.launch {
                    viewModel.delete(bookList.get(holder.adapterPosition).id!!)
                }
            }
        }
    }

    inner class BookDiffUtils(private val oldList: MutableList<Book>,
                                 private val newList: MutableList<Book>) : DiffUtil.Callback() {


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

        binding.shelfView.layoutManager = layoutManager
        binding.shelfView.adapter = adapter
        viewModel.getAll().observe(viewLifecycleOwner, Observer {
            it?.let {
                val diffCallback = BookDiffUtils(bookList, it)
                val diffResult = DiffUtil.calculateDiff(diffCallback)
                diffResult.dispatchUpdatesTo(adapter)
                bookList.clear()
                bookList.addAll(it)
            }
        })
    }

}