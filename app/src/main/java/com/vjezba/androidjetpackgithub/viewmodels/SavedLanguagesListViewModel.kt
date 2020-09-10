package com.vjezba.androidjetpackgithub.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.vjezba.androidjetpackgithub.data.SavedAndAllLanguages
import com.vjezba.androidjetpackgithub.data.SavedLanguagesRepository

class SavedLanguagesListViewModel internal constructor(
    savedLanguages: SavedLanguagesRepository
) : ViewModel() {
    val savedAndAllLanguages: LiveData<List<SavedAndAllLanguages>> =
        savedLanguages.getSavedLanguages()
}
