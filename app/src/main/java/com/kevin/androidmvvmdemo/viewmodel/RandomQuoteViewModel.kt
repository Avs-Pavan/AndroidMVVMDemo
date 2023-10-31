package com.kevin.androidmvvmdemo.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kevin.androidmvvmdemo.model.data.AnimeQuotation
import com.kevin.androidmvvmdemo.model.repo.QuoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject


@HiltViewModel
class RandomQuoteViewModel @Inject constructor(private val quoteRepository: QuoteRepository) :
    ViewModel() {

    // backing live data
    private val _quotationLiveData = MutableLiveData<AnimeQuotation>()

    // exposed live data
    val quotationLiveData: LiveData<AnimeQuotation>
        get() = _quotationLiveData

    // backing error live data
    private val _error = MutableLiveData<String>()

    //
    val error: LiveData<String>
        get() = _error


    fun getQuotation() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = quoteRepository.getRandomQuotation()
                if (response.isSuccessful) {
                    _quotationLiveData.postValue(response.body())
                } else {
                    _error.postValue("Some thing went wrong")
                }
            } catch (e: Exception) {
                e.printStackTrace()
                _error.postValue(e.message)
            }
        }
    }

}