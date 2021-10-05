package com.mendelu.fbe.bookshelf.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.mendelu.fbe.bookshelf.dao.BookDAO
import com.mendelu.fbe.bookshelf.dao.BookMarkDAO
import com.mendelu.fbe.bookshelf.dao.BookWithBookMarksDAO
import com.mendelu.fbe.bookshelf.model.Book
import com.mendelu.fbe.bookshelf.model.BookMark
import com.mendelu.fbe.bookshelf.model.BookWithBookMarks

@Database(entities = [Book::class, BookMark::class], version = 6, exportSchema = true)
abstract class BooksDatabase : RoomDatabase() {

    abstract fun bookDao(): BookDAO
    abstract fun bookMarkDao(): BookMarkDAO

    companion object {

        private var INSTANCE: BooksDatabase? = null

        fun getDatabase(context: Context): BooksDatabase {
            if (INSTANCE == null) {
                synchronized(BooksDatabase::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(
                            context.applicationContext,
                            BooksDatabase::class.java, "books_database"
                        ).fallbackToDestructiveMigration().build()
                    }
                }
            }
            return INSTANCE!!
        }
    }
}