package com.example.myapplication.mvvm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class SubscriberFactoryViewModel(private val repository: SubscriberRepositry) : ViewModelProvider.Factory {

    // boilerplate code

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(SubscriberViewModel::class.java)) {
            return SubscriberViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }



}