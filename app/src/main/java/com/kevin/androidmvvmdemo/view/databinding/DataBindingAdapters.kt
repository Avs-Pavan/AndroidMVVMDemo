package com.kevin.androidmvvmdemo.view.databinding

import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.kevin.androidmvvmdemo.model.data.AnimeQuotation
import com.kevin.androidmvvmdemo.util.network.APIResponse

@BindingAdapter("showOnLoading")
fun ProgressBar.showOnLoading(responseState: APIResponse<Nothing>) {
    visibility = if (responseState is APIResponse.Loading)
        View.VISIBLE
    else
        View.GONE
}

@BindingAdapter("showQuotation")
fun TextView.showQuotation(responseState: APIResponse<AnimeQuotation>) {
    text = if (responseState is APIResponse.OnSuccess) {
        responseState.data.quote
    } else {
        "Click refresh to fetch quotation"
    }
}
