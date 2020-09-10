/*
 * Copyright 2020 Google LLC
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

package com.vjezba.androidjetpackgithub.viewmodels

import android.view.View
import android.widget.ProgressBar
import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.recyclerview.widget.RecyclerView
import com.vjezba.androidjetpackgithub.data.*
import kotlinx.coroutines.flow.Flow

class GalleryViewModel internal constructor(
    private val repository: GithubRepository
) : ViewModel() {

    private var firstFetchData = 0

    private var currentQueryValue: String? = null
    private var currentSearchResult: Flow<PagingData<RepositoryDetailsResponse>>? = null

    fun searchGithubRepositoryByProgrammingLanguage(queryString: String, progressBarRepos: ProgressBar?, languageListRepository: RecyclerView?): Flow<PagingData<RepositoryDetailsResponse>> {
        currentQueryValue = queryString
        val newResult: Flow<PagingData<RepositoryDetailsResponse>> =
            repository.getSearchRepositoriesResultStream(queryString).cachedIn(viewModelScope)
        currentSearchResult = newResult
        return newResult
    }
}