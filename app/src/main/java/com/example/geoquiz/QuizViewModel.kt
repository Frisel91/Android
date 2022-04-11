package com.example.geoquiz

import android.util.Log
import androidx.lifecycle.ViewModel

private const val Tag = "QuizViewModel"
class QuizViewModel : ViewModel() {
    init {
        Log.d(Tag, "ViewModel instance created")
    }

    override fun onCleared() {
        super.onCleared()
        Log.d(Tag, "ViewModel instance about to be destroyed")
    }
}