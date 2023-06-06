package com.example.happyenglish;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.happyenglish.data.User;

public class Reg extends AppCompatActivity {
    Button btnNext;
    private RegViewModel viewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg);
        viewModel = new ViewModelProvider(this).get(RegViewModel.class);
        EditText mail = findViewById(R.id.editTextTextPersonName);
        EditText login = findViewById(R.id.editTextTextPersonName3);
        EditText password = findViewById(R.id.editTextTextPersonName4);
        btnNext = findViewById(R.id.button4);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    viewModel.registration(new User(
                            mail.getText().toString(), password.getText().toString(),login.getText().toString()
                    ));
                    Toast.makeText(getApplicationContext(), "Hello!", Toast.LENGTH_SHORT).show();
                    Toast.makeText(getApplicationContext(), "It's time to learn something new!", Toast.LENGTH_SHORT).show();
                    Intent e = new Intent(Reg.this, MainActivity.class);
                    startActivity(e);
                } catch (User.InvalidEmailException e) {
                    Toast.makeText(Reg.this, "Email is incorrect", Toast.LENGTH_SHORT).show();
                }catch (User.InvalidPasswordException e) {
                    Toast.makeText(Reg.this, "Password is incorrect", Toast.LENGTH_SHORT).show();
                }catch (User.InvalidNameException e) {
                    Toast.makeText(Reg.this, "Name is incorrect", Toast.LENGTH_SHORT).show();
                }




            }
        });
    }
}