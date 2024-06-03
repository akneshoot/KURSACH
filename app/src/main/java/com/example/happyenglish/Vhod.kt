package com.example.happyenglish

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore // Добавлено

class Vhod : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var firestore: FirebaseFirestore
    var btnVhod: Button? = null
    var btnNext: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.vhod)

        auth = FirebaseAuth.getInstance()
        firestore = FirebaseFirestore.getInstance() // Инициализация Firestore

        val ed1 = findViewById<EditText>(R.id.editTextTextPersonName2)
        val ed2 = findViewById<EditText>(R.id.editTextTextPassword)

        btnNext = findViewById(R.id.button2)
        btnNext?.setOnClickListener(View.OnClickListener {
            val email = ed1.text.toString().trim { it <= ' ' }
            val password = ed2.text.toString().trim { it <= ' ' }

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(
                    this@Vhod,
                    "Please enter your email and password.",
                    Toast.LENGTH_SHORT
                ).show()
                return@OnClickListener
            }

            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(this@Vhod, "Login successful", Toast.LENGTH_SHORT).show()
                        val intent = Intent(this@Vhod, MainActivity::class.java)
                        startActivity(intent)
                        finish()
                    } else {
                        Toast.makeText(this@Vhod, "Login failed: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                    }
                }
        })

        btnVhod = findViewById<View>(R.id.button3) as Button
        btnVhod!!.setOnClickListener {
            val intent = Intent(this@Vhod, Reg::class.java)
            startActivity(intent)
        }
    }
}