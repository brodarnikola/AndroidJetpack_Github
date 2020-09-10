/*
 * Copyright 2018 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.vjezba.androidjetpackgithub.utilities

import android.content.Context
import androidx.fragment.app.Fragment
import com.vjezba.androidjetpackgithub.api.GithubRepositoryRetrofit
import com.vjezba.androidjetpackgithub.data.AppDatabase
import com.vjezba.androidjetpackgithub.data.LanguagesRepository
import com.vjezba.androidjetpackgithub.data.SavedLanguagesRepository
import com.vjezba.androidjetpackgithub.data.GithubRepository
import com.vjezba.androidjetpackgithub.viewmodels.GalleryViewModelFactory
import com.vjezba.androidjetpackgithub.viewmodels.LanguagesListViewModelFactory
import com.vjezba.androidjetpackgithub.viewmodels.LanguageDetailsViewModelFactory
import com.vjezba.androidjetpackgithub.viewmodels.SavedLanguagesListViewModelFactory

/**
 * Static methods used to inject classes needed for various Activities and Fragments.
 */
object InjectorUtils {

    private fun getSavedLanguagesRepository(context: Context): SavedLanguagesRepository {
        return SavedLanguagesRepository.getInstance(
            AppDatabase.getInstance(context.applicationContext).savedLanguagesDAO()
        )
    }

    fun provideSavedLanguagesViewModelFactory(context: Context) : SavedLanguagesListViewModelFactory {
        return SavedLanguagesListViewModelFactory(getSavedLanguagesRepository(context = context))
    }

    private fun getLanguagesRepository(context: Context): LanguagesRepository {
        return LanguagesRepository.getInstance(
            AppDatabase.getInstance(context.applicationContext).languagesDAO()
        )
    }

    fun provideLanguagesListViewModelFactory(fragment: Fragment): LanguagesListViewModelFactory {
        return LanguagesListViewModelFactory(getLanguagesRepository(fragment.requireContext()), fragment)
    }


    fun provideLanguageDetailsViewModelFactory(
        context: Context,
        languagesId: String
    ): LanguageDetailsViewModelFactory {
        return LanguageDetailsViewModelFactory(
            getLanguagesRepository(context),
            getSavedLanguagesRepository(context),
            languagesId
        )
    }

    fun provideGalleryViewModelFactory(): GalleryViewModelFactory {
        val repository = GithubRepository(GithubRepositoryRetrofit.create())
        return GalleryViewModelFactory(repository)
    }

}
