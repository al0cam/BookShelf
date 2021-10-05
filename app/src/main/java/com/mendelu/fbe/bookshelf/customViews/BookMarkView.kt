package com.mendelu.fbe.bookshelf.customViews

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.drawable.Drawable
import android.text.TextPaint
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.LinearLayout
import com.mendelu.fbe.bookshelf.R
import com.mendelu.fbe.bookshelf.databinding.RowBookmarkBinding
import com.mendelu.fbe.bookshelf.databinding.SampleBookViewBinding
import org.koin.core.KoinApplication.Companion.init

class BookMarkView @kotlin.jvm.JvmOverloads
    constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0)
    : FrameLayout(context, attrs, defStyleAttr) {
    private lateinit var binding: RowBookmarkBinding

    init {
        init(context, attrs, defStyleAttr)
    }

    fun hideLabels()
    {
        binding.bookmark.visibility = View.GONE
        binding.page.visibility = View.GONE
    }
    fun unHideLabels()
    {
        binding.bookmark.visibility = View.VISIBLE
        binding.page.visibility = View.VISIBLE
    }
    fun hideButtons()
    {
        binding.deleteButton.visibility = View.GONE
    }

    fun getBookName(): String
    {
        return binding.bookName.text.toString()
    }
    fun setBookName(text: String)
    {
        binding.bookName.text = text
    }
    fun getBookMark(): String
    {
        return binding.bookmark.text.toString()
    }
    fun setBookMark(text: String)
    {
        binding.bookmark.text = text
    }
    fun getPage(): String
    {
        return binding.page.text.toString()
    }
    fun setPage(text: String)
    {
        binding.page.text = text
    }

    private fun init(context: Context, attrs: AttributeSet?, defStyle: Int?) {
        binding = RowBookmarkBinding.inflate(LayoutInflater.from(context), this, true)
        if (attrs != null && defStyle != null) {
            loadAttributes(attrs, defStyle)
        }
    }

    private fun loadAttributes(attrs: AttributeSet, defStyle: Int?){
        val a = context.obtainStyledAttributes(
            attrs,
            R.styleable.BookView, 0, 0
        )
        a.recycle()
    }

}