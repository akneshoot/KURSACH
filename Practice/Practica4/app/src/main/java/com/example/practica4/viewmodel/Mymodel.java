package com.example.practica4.viewmodel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.practica4.R;
import com.example.practica4.model.Arraydata;
import com.example.practica4.model.Repository;


public class Mymodel extends ViewModel {
    private final String TAG = this.getClass().getSimpleName();
    private final Repository<String> repository = new Repository<>(new Arraydata<>());
    private final MutableLiveData<Textchange> text_changer =
            new MutableLiveData(new Textchange(null));


    public LiveData<Textchange> getData(){
        return text_changer;
    }

    public void Change_text(String data) {
        text_changer.setValue(new Textchange(data));
        repository.add(data);
        Log.d(TAG, repository.getAll().toString());
    }
}
