package com.example.happyenglish;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
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
                String username = ed1.getText().toString().trim();
                String password = ed2.getText().toString().trim();
                if (username.isEmpty() || password.isEmpty()) {
                    Toast.makeText(Vhod.this, "Please enter your username and password.", Toast.LENGTH_SHORT).show();
                    return;
                }
                viewModel.loginV(ed1.getText().toString(), ed2.getText().toString()).observe(Vhod.this,aBoolean -> {
                    if (aBoolean) {
                        Toast.makeText(Vhod.this, "Hello!", Toast.LENGTH_SHORT).show();
                        Toast.makeText(Vhod.this, "It's time to learn something new!", Toast.LENGTH_SHORT).show();
                        Intent e = new Intent(Vhod.this, MainActivity.class);
                        startActivity(e);
                    }
                    else{
                        Toast.makeText(Vhod.this, "There is no such login or password!", Toast.LENGTH_SHORT).show();
                        Toast.makeText(Vhod.this, "Please, go to registration.", Toast.LENGTH_SHORT).show();
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



