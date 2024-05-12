package com.example.happyenglish

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.happyenglish.data.User

class Reg : AppCompatActivity() {
    private lateinit var btnNext: Button
    private lateinit var viewModel: RegViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reg)

        viewModel = ViewModelProvider(this).get(RegViewModel::class.java)

        val mail = findViewById<EditText>(R.id.editTextTextPersonName)
        val login = findViewById<EditText>(R.id.editTextTextPersonName3)
        val password = findViewById<EditText>(R.id.editTextTextPersonName4)
        btnNext = findViewById<Button>(R.id.button4) // Присваиваем значение кнопке здесь

        btnNext.setOnClickListener {
            try {
                viewModel.registration(
                    User(
                        mail.text.toString(), password.text.toString(), login.text.toString()
                    )
                )
                Toast.makeText(applicationContext, "Hello!", Toast.LENGTH_SHORT).show()
                Toast.makeText(
                    applicationContext,
                    "It's time to learn something new!",
                    Toast.LENGTH_SHORT
                ).show()
                val intent = Intent(this@Reg, MainActivity::class.java)
                startActivity(intent)
            } catch (e: User.InvalidEmailException) {
                Toast.makeText(this@Reg, "Email is incorrect", Toast.LENGTH_SHORT).show()
            } catch (e: User.InvalidPasswordException) {
                Toast.makeText(this@Reg, "Password is incorrect", Toast.LENGTH_SHORT).show()
            } catch (e: User.InvalidNameException) {
                Toast.makeText(this@Reg, "Name is incorrect", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
