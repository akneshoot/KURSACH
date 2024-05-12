package com.example.happyenglish

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider

class Vhod : AppCompatActivity() {
    var btnVhod: Button? = null
    var btnNext: Button? = null
    private var viewModel: VhodViewModel? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.vhod)
        viewModel = ViewModelProvider(this).get(VhodViewModel::class.java)
        val ed1 = findViewById<EditText>(R.id.editTextTextPersonName2)
        val ed2 = findViewById<EditText>(R.id.editTextTextPassword)
        btnNext = findViewById(R.id.button2)
        btnNext?.setOnClickListener(View.OnClickListener {
            val username = ed1.text.toString().trim { it <= ' ' }
            val password = ed2.text.toString().trim { it <= ' ' }
            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(
                    this@Vhod,
                    "Please enter your username and password.",
                    Toast.LENGTH_SHORT
                ).show()
                return@OnClickListener
            }
            viewModel!!.loginV(ed1.text.toString(), ed2.text.toString())
                .observe(this@Vhod) { aBoolean: Boolean ->
                    if (aBoolean) {
                        Toast.makeText(this@Vhod, "Hello!", Toast.LENGTH_SHORT).show()
                        Toast.makeText(
                            this@Vhod,
                            "It's time to learn something new!",
                            Toast.LENGTH_SHORT
                        ).show()
                        val e = Intent(this@Vhod, MainActivity::class.java)
                        startActivity(e)
                    } else {
                        Toast.makeText(
                            this@Vhod,
                            "There is no such login or password!",
                            Toast.LENGTH_SHORT
                        ).show()
                        Toast.makeText(this@Vhod, "Please, go to registration.", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
        })
        btnVhod = findViewById<View>(R.id.button3) as Button
        btnVhod!!.setOnClickListener {
            val e = Intent(this@Vhod, Reg::class.java)
            startActivity(e)
        }
    }
}



