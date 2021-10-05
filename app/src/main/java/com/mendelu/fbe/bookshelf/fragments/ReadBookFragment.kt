package com.mendelu.fbe.bookshelf.fragments

import android.view.LayoutInflater
import androidx.core.net.toUri
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.mendelu.fbe.bookshelf.databinding.ReadBookFragmentBinding
import com.mendelu.fbe.bookshelf.viewModels.ReadBookViewModel
import cz.mendelu.fbe.mytodo.architecture.BaseFragment
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*


class ReadBookFragment : BaseFragment<ReadBookFragmentBinding, ReadBookViewModel>(ReadBookViewModel::class) {
    override val bindingInflater: (LayoutInflater) -> ReadBookFragmentBinding
        get() = ReadBookFragmentBinding::inflate

    private val arguments: ReadBookFragmentArgs by navArgs()


    override fun initViews() {
        binding.addBookMark.setOnClickListener{
            findNavController().navigate(ReadBookFragmentDirections.toAddBookMark(viewModel.book.id!!))
        }
        lifecycleScope.launch {
            viewModel.findById(arguments.id)
            val contentResolver = requireContext().contentResolver
//            viewModel.book.lastReadDate = Calendar.getInstance().time.toString()
            val sdf = SimpleDateFormat("yyyy-MM-dd")
            viewModel.book.lastReadDate = sdf.format(Date())

//            val takeFlags: Int = Intent.FLAG_GRANT_READ_URI_PERMISSION or Intent.FLAG_GRANT_WRITE_URI_PERMISSION
//            println("Permissions: " + contentResolver.persistedUriPermissions)
//            contentResolver.takePersistableUriPermission(viewModel.book.path!!.toUri(), takeFlags)
            binding.pdfView.fromStream(contentResolver.openInputStream(viewModel.book.path!!.toUri())).load()


//              pdfTRON
//            val config = ViewerConfig.Builder().openUrlCachePath(requireContext().getCacheDir().getAbsolutePath()).build()
//            val intent = DocumentActivity.IntentBuilder.fromActivityClass(requireContext(), DocumentActivity::class.java)
//                .withUri(viewModel.book.path!!.toUri())
//                .usingConfig(config)
//                .build()
//            startActivity(intent)
        }




    }
}