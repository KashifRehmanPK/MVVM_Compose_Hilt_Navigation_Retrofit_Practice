package com.neonstudio.mvvm_compose_practice.repository

import com.neonstudio.mvvmprictice2.api.TweetsyAPI
import com.neonstudio.mvvmprictice2.models.TweetListItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject


class TweetRepository @Inject constructor(private val tweetsyAPI: TweetsyAPI) {


    private val _tweets = MutableStateFlow<List<TweetListItem>>(emptyList())
    val tweets: StateFlow<List<TweetListItem>>
        get() = _tweets

    private val _categories = MutableStateFlow<List<String>>(emptyList())
    val categories: StateFlow<List<String>>
        get() = _categories


    suspend fun getTweets (category:String) {
        val result = tweetsyAPI.getTweets("tweets[?(@.category==\"$category\")]")
        if (result.isSuccessful && result.body() != null) {
            _tweets.emit(result.body()!!)
        }
    }


        suspend fun getCategories(){
        val result = tweetsyAPI.getCategories()
        if (result.isSuccessful && result.body() != null) {
            _categories.emit(result.body()!!)

        }
    }
}