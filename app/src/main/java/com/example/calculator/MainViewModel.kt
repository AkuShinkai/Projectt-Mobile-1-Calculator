package com.example.calculator

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    val expression: MutableLiveData<String> = MutableLiveData()

    init {
        expression.value = ""
    }
}
