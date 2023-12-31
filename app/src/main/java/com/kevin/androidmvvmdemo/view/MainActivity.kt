package com.kevin.androidmvvmdemo.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.kevin.androidmvvmdemo.R
import com.kevin.androidmvvmdemo.databinding.ActivityMainBinding
import com.kevin.androidmvvmdemo.util.extensions.hide
import com.kevin.androidmvvmdemo.util.extensions.show
import com.kevin.androidmvvmdemo.util.network.APIResponse
import com.kevin.androidmvvmdemo.viewmodel.RandomQuoteViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val viewModel: RandomQuoteViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        initViews()

    }

    private fun initViews() {

        viewModel.quotationLiveData.observe(this, Observer { data ->

            when (data) {
                is APIResponse.Loading -> {
//                    Log.e("tag", "loading")
//                    binding.loading.show()
                }

                is APIResponse.OnError -> {
//                    binding.loading.hide()
                    Toast.makeText(this, data.error, Toast.LENGTH_LONG).show()
                }

                is APIResponse.OnSuccess -> {
//                    binding.loading.hide()
//                    binding.tv.text = data.data?.quote
                }

                else -> {
                    // do noting
                }
            }
        })

//
//        binding.refreshBtn.setOnClickListener {
//            viewModel.getQuotation()
//        }
    }
}