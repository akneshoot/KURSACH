package com.example.happyenglish;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelProvider;


public class Vhod extends AppCompatActivity {


    Button btnVhod;
    Button btnNext;
    private VhodViewModel viewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vhod);
        viewModel = new ViewModelProvider(this).get(VhodViewModel.class);
        EditText ed1 = findViewById(R.id.editTextTextPersonName2);
        EditText ed2 = findViewById(R.id.editTextTextPassword);
        btnNext = findViewById(R.id.button2);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewModel.loginV(ed1.getText().toString(), ed2.getText().toString()).observe(Vhod.this,aBoolean -> {
                    if (aBoolean) {
                        Intent e = new Intent(Vhod.this, MainActivity.class);
                        startActivity(e);
                    }
                });


            }
        });
        btnVhod = (Button) findViewById (R.id.button3);
        btnVhod.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                Intent e = new Intent(Vhod.this, Reg.class);
                startActivity(e);


            }
        });
    }

}



