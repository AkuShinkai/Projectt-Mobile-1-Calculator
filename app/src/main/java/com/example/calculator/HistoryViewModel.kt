
package com.example.calculator

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HistoryViewModel : ViewModel() {
    val historyList: MutableLiveData<MutableList<String>> = MutableLiveData(mutableListOf())

    private fun <T> MutableLiveData<T>.notifyObserver() {
        this.value = this.value
    }
}