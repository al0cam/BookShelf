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
import com.mendelu.fbe.bookshelf.databinding.SampleBookViewBinding
import org.koin.core.KoinApplication.Companion.init

class BookView @kotlin.jvm.JvmOverloads
    constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0)
    : FrameLayout(context, attrs, defStyleAttr) {
    private lateinit var binding: SampleBookViewBinding

    init {
        init(context, attrs, defStyleAttr)
        binding.openBookButton.setOnClickListener{
            openBook()
        }
    }

    fun openBook(){}

    fun hideLabels()
    {
        binding.bookName.visibility = View.GONE
    }
    fun unHideLabels()
    {
        binding.bookName.visibility = View.VISIBLE
    }

    fun hideButtons()
    {
        binding.deleteButton.visibility =  View.GONE
        binding.openBookButton.visibility =  View.GONE
        binding.favourite.visibility =  View.GONE
    }

    fun setBookName(string: String)
    {
        binding.bookName.text = string
    }
    fun getBookName(): String
    {
        return binding.bookName.text.toString()
    }
    fun seAuthor(string: String)
    {
        binding.author.text = string
    }
    fun getAuthor(): String
    {
        return binding.author.text.toString()
    }

    private fun init(context: Context, attrs: AttributeSet?, defStyle: Int?) {
        binding = SampleBookViewBinding.inflate(LayoutInflater.from(context), this, true)
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