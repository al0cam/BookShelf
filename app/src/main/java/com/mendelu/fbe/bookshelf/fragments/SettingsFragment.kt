package com.mendelu.fbe.bookshelf.fragments

import android.R
import android.app.Application
import android.view.LayoutInflater
import com.mendelu.fbe.bookshelf.LocaleHelper
import com.mendelu.fbe.bookshelf.databinding.SettingsFragmentBinding
import com.mendelu.fbe.bookshelf.utils.LocaleUtils
import com.mendelu.fbe.bookshelf.viewModels.SettingsViewModel
import cz.mendelu.fbe.mytodo.architecture.BaseFragment
import java.util.*


class SettingsFragment : BaseFragment<SettingsFragmentBinding, SettingsViewModel>(SettingsViewModel::class) {


    override val bindingInflater: (LayoutInflater) -> SettingsFragmentBinding
        get() = SettingsFragmentBinding::inflate

    override fun initViews() {
        binding.langCro.setOnClickListener{
            LocaleUtils.setLocale(Locale.forLanguageTag("hr"))
            LocaleUtils.updateConfiguration(requireContext(), "hr", "hr")
        }
        binding.langEng.setOnClickListener{
            LocaleUtils.setLocale(Locale.forLanguageTag("en"))
            LocaleUtils.updateConfiguration(requireContext(), "en", "en")
        }


    }

}