package com.kevin.androidmvvmdemo.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kevin.androidmvvmdemo.model.data.AnimeQuotation
import com.kevin.androidmvvmdemo.model.repo.QuoteRepository
import com.kevin.androidmvvmdemo.util.network.APIResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject


@HiltViewModel
class RandomQuoteViewModel @Inject constructor(private val quoteRepository: QuoteRepository) :
    ViewModel() {

    // backing live data
    private val _quotationLiveData = MutableLiveData<APIResponse<AnimeQuotation?>>()

    // exposed live data
    val quotationLiveData: LiveData<APIResponse<AnimeQuotation?>>
        get() = _quotationLiveData


    fun getQuotation() {
        viewModelScope.launch(Dispatchers.IO) {
            _quotationLiveData.postValue(APIResponse.Loading())
            val response = quoteRepository.getRandomQuotation()
            if (response.isSuccessful) {
                _quotationLiveData.postValue(APIResponse.OnSuccess(response.body()))
            } else {
                _quotationLiveData.postValue(APIResponse.OnError("Some thing went wrong"))
            }
        }
    }

}