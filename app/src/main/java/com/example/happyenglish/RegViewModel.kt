package com.example.happyenglish

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.happyenglish.data.MainRepository
import com.example.happyenglish.data.User

class RegViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = MainRepository(getApplication())

    fun registration(user: User?) {
        repository.registration(user)
    }
}
