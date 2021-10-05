package cz.mendelu.fbe.mytodo.di

import com.mendelu.fbe.bookshelf.dao.BookDAO
import com.mendelu.fbe.bookshelf.dao.BookMarkDAO
import com.mendelu.fbe.bookshelf.dao.BookWithBookMarksDAO
import com.mendelu.fbe.bookshelf.database.BooksDatabase
import org.koin.dsl.module

val daoModule = module {

    fun provideBookDao(database: BooksDatabase): BookDAO = database.bookDao()
        fun provideBookMarkDao(database: BooksDatabase): BookMarkDAO = database.bookMarkDao()
    single {
        provideBookDao(get())
    }
    single {
        provideBookMarkDao(get())
    }
}