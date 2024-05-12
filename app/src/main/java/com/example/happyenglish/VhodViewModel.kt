package com.example.happyenglish

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.happyenglish.data.MainRepository

class VhodViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = MainRepository(getApplication())


    fun loginV(login: String?, pass: String?): LiveData<Boolean> {
        return repository.login(login, pass)
    }
}
