package com.kevin.androidmvvmdemo.util.network


sealed class APIResponse<out T> {
    data class OnSuccess<out T>(val data: T) : APIResponse<T>()

    data class OnError(val error: String) : APIResponse<Nothing>()

    class Loading() : APIResponse<Nothing>()
}