package com.kevin.androidmvvmdemo.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.kevin.androidmvvmdemo.databinding.ActivityMainBinding
import com.kevin.androidmvvmdemo.viewmodel.RandomQuoteViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(this.layoutInflater)
    }

    private val viewModel: RandomQuoteViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initViews()

    }

    private fun initViews() {

        viewModel.quotationLiveData.observe(this, Observer { data ->
            binding.tv.text = data.quote
        })

        viewModel.error.observe(this, Observer { error ->
            Toast.makeText(this, error, Toast.LENGTH_LONG).show()
        })

        binding.refreshBtn.setOnClickListener {
            viewModel.getQuotation()
        }
    }
}