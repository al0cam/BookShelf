package com.mendelu.fbe.bookshelf.fragments

import android.Manifest
import android.app.Activity
import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Build
import android.view.LayoutInflater
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.mendelu.fbe.bookshelf.MainActivity
import com.mendelu.fbe.bookshelf.databinding.AddBookFragmentBinding
import com.mendelu.fbe.bookshelf.viewModels.AddBookViewModel
import cz.mendelu.fbe.mytodo.architecture.BaseFragment
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class AddBookFragment : BaseFragment<AddBookFragmentBinding, AddBookViewModel>(AddBookViewModel::class) {
    override val bindingInflater: (LayoutInflater) -> AddBookFragmentBinding
        get() = AddBookFragmentBinding::inflate

    fun openFile() {
        val intent = Intent()
            .setType("*/*")
            .setAction(Intent.ACTION_GET_CONTENT)
            .setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION or Intent.FLAG_GRANT_WRITE_URI_PERMISSION or Intent.FLAG_GRANT_PERSISTABLE_URI_PERMISSION)

        startActivityForResult(Intent.createChooser(intent, "Select a file"), 123)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 123 && resultCode == RESULT_OK) {
            val selectedfile = data!!.data //The uri with the location of the file
//            val takeFlags: Int = Intent.FLAG_GRANT_READ_URI_PERMISSION  or Intent.FLAG_GRANT_WRITE_URI_PERMISSION
//            var resolver = requireContext().contentResolver
//            resolver.takePersistableUriPermission(selectedfile!!, takeFlags);
            binding.filePath.text = selectedfile!!.toString()
        }
    }


    override fun initViews() {

        binding.loadFile.setOnClickListener{
            openFile()
        }

        binding.saveBook.setOnClickListener{
            val author = binding.author.text.toString()
            val bookName = binding.bookName.text.toString()
            val filePath = binding.filePath.text

            if(author.isNotBlank() && bookName.isNotBlank() && filePath.isNotBlank() && filePath != "Select a file")
            {
                viewModel.book.author = author
                viewModel.book.name = bookName
                viewModel.book.path = filePath as String?
                val sdf = SimpleDateFormat("yyyy-MM-dd")
                viewModel.book.lastReadDate = sdf.format(Date())

                lifecycleScope.launch{
                    val id = viewModel.insert(viewModel.book)
                }.invokeOnCompletion {
                    findNavController().popBackStack()
                }
            }
            else if (author.isEmpty())
            {
                binding.author.setError("must not be empty")
            }
            else if (bookName.isEmpty())
            {
                binding.bookName.setError("must not be empty")
            }
            else if (filePath.isEmpty())
            {
                binding.filePath.setError("must not be empty")
            }
        }
    }



}