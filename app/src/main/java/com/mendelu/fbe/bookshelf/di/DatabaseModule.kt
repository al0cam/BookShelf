package cz.mendelu.fbe.mytodo.di

import com.mendelu.fbe.bookshelf.database.BooksDatabase
import com.mendelu.fbe.bookshelf.MyApplication
import org.koin.dsl.module

val databaseModule = module {
    fun provideDatabase():BooksDatabase = BooksDatabase.getDatabase(MyApplication.appContext)
    single {
        provideDatabase()
    }
}