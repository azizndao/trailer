package com.example.moviescompose.ui.screen.tv

import android.app.Application
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import androidx.paging.map
import com.example.moviescompose.R
import com.example.moviescompose.data.api.TVApiService
import com.example.moviescompose.data.datasources.TheMoviesPagingSource
import com.example.moviescompose.data.model.TV
import com.example.moviescompose.data.model.toUiState
import kotlinx.coroutines.flow.map

class TVViewModel(
  private val apiService: TVApiService,
  app: Application
) : AndroidViewModel(app) {

  var selectedTabIndex: Int by mutableStateOf(0)
    private set

  val selectedTabData by derivedStateOf { tabViews[tabs[selectedTabIndex]]!! }

  private val pagingConfig = PagingConfig(pageSize = 20, enablePlaceholders = true)

  val tabs = listOf(R.string.popular, R.string.aring_today, R.string.on_tv, R.string.top_rated)

  private var tabViews = mapOf(
    R.string.popular to Pager(pagingConfig) {
      TheMoviesPagingSource { apiService.getPopular(it) }
    }.flow.map { it.map(TV::toUiState) }.cachedIn(viewModelScope),

    R.string.aring_today to Pager(pagingConfig) {
      TheMoviesPagingSource { apiService.getAiringToday(it) }
    }.flow.map { it.map(TV::toUiState) }.cachedIn(viewModelScope),

    R.string.on_tv to Pager(pagingConfig) {
      TheMoviesPagingSource { apiService.getOnTv(it) }
    }.flow.map { it.map(TV::toUiState) }.cachedIn(viewModelScope),

    R.string.top_rated to Pager(pagingConfig) {
      TheMoviesPagingSource { apiService.getTopRated(it) }
    }.flow.map { it.map(TV::toUiState) }.cachedIn(viewModelScope),
  )


  fun selectTab(index: Int) {
    selectedTabIndex = index
  }
}