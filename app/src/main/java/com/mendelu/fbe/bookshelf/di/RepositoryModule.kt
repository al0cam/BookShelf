package cz.mendelu.fbe.mytodo.di

import com.mendelu.fbe.bookshelf.dao.BookDAO
import com.mendelu.fbe.bookshelf.dao.BookMarkDAO
import com.mendelu.fbe.bookshelf.repository.IBookMarkMarkRepository
import com.mendelu.fbe.bookshelf.repository.IBookMarkRepositoryImpl
import com.mendelu.fbe.bookshelf.repository.IBookRepositoryImpl
import com.mendelu.fbe.bookshelf.repository.IBooksRepository
import org.koin.dsl.module

val repositoryModule = module {

    fun provideBookRepository (dao: BookDAO) : IBooksRepository{
        return IBookRepositoryImpl(dao)
    }
    fun provideBookMarkRepository (dao: BookMarkDAO) : IBookMarkMarkRepository{
        return IBookMarkRepositoryImpl(dao)
    }

    single{
        provideBookRepository(get())
    }
    single{
        provideBookMarkRepository(get())
    }
}