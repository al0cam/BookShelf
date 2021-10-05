package cz.mendelu.fbe.mytodo.di

import com.mendelu.fbe.bookshelf.viewModels.*
import org.koin.android.experimental.dsl.viewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel{
        AddBookViewModel(get())
    }
    viewModel{
        ShelfViewModel(get())
    }
    viewModel{
        MainPageViewModel(get(),get())
    }
    viewModel{
        ReadBookViewModel(get())
    }
    viewModel{
        SettingsViewModel()
    }
    viewModel{
        BookMarksViewModel(get(),get())
    }
    viewModel{
        AddBookMarkViewModel(get())
    }
}