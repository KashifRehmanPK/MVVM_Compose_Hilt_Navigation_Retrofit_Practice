package com.neonstudio.mvvm_compose_practice.viewmodels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.neonstudio.mvvm_compose_practice.repository.TweetRepository
import com.neonstudio.mvvmprictice2.models.TweetListItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val repository: TweetRepository,
    private val savedStateHandle: SavedStateHandle) : ViewModel() {

    val tweets: StateFlow<List<TweetListItem>>
        get() = repository.tweets

    init {
        viewModelScope.launch {
            val catagory = savedStateHandle.get<String>("category") ?: "fact"
            repository.getTweets(catagory)
        }
    }
}