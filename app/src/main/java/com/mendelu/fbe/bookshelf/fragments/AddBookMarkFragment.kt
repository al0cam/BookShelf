package com.mendelu.fbe.bookshelf.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.mendelu.fbe.bookshelf.R
import com.mendelu.fbe.bookshelf.databinding.AddBookMarkFragmentBinding
import com.mendelu.fbe.bookshelf.databinding.BookMarksFragmentBinding
import com.mendelu.fbe.bookshelf.viewModels.AddBookMarkViewModel
import com.mendelu.fbe.bookshelf.viewModels.AddBookViewModel
import cz.mendelu.fbe.mytodo.architecture.BaseFragment
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class AddBookMarkFragment : BaseFragment<AddBookMarkFragmentBinding, AddBookMarkViewModel>(AddBookMarkViewModel::class) {
    override val bindingInflater: (LayoutInflater) -> AddBookMarkFragmentBinding
        get() = AddBookMarkFragmentBinding::inflate

    private val arguments: ReadBookFragmentArgs by navArgs()

    override fun initViews() {
        binding.saveBookmark.setOnClickListener{
            val bookmark = binding.bookmark.text.toString()
            val pageNumber = binding.pageNumber.text.toString()
            if (!bookmark.isNullOrBlank() and !pageNumber.isNullOrBlank())
            {
                viewModel.bookmark.bookId = arguments.id
                viewModel.bookmark.bookMarkedText = bookmark
                viewModel.bookmark.page = pageNumber.toLong()
                val sdf = SimpleDateFormat("yyyy-MM-dd")
                viewModel.bookmark.dateCreated = sdf.format(Date())

                lifecycleScope.launch{
                    viewModel.insert(viewModel.bookmark)
                }.invokeOnCompletion {
                    findNavController().popBackStack()
                }
            }
            else if(bookmark.isNullOrBlank())
                binding.bookmark.setError("must not be empty")
            else if(pageNumber.isNullOrBlank())
                binding.pageNumber.setError("must not be empty")

        }
    }


}