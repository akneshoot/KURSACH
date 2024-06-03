package com.example.happyenglish
import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore // Добавлено

class Reg : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var firestore: FirebaseFirestore // Добавлено

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reg)

        auth = FirebaseAuth.getInstance()
        firestore = FirebaseFirestore.getInstance() // Инициализация Firestore

        findViewById<Button>(R.id.button4).setOnClickListener {
            val email = findViewById<EditText>(R.id.editTextTextPersonName).text.toString().trim()
            val password =
                findViewById<EditText>(R.id.editTextTextPersonName3).text.toString().trim()
            val fullname =
                findViewById<EditText>(R.id.editTextTextPersonName4).text.toString().trim()

            if (validateInput(fullname, email, password)) {
                registerUser(email, password, fullname) // Добавлена передача параметра fullname
            }
        }


        findViewById<Button>(R.id.backButton).setOnClickListener {
            val intent = Intent(this, Vhod::class.java)
            startActivity(intent)
        }
    }

    private fun validateInput(name: String, email: String, password: String): Boolean {
        if (name.length < 2) {
            Toast.makeText(this, "Name must be at least 2 characters", Toast.LENGTH_SHORT).show()
            return false
        }
        if (!isValidEmail(email)) {
            Toast.makeText(
                this,
                "Please enter a valid email from mail.ru, gmail.com, or yandex.ru",
                Toast.LENGTH_SHORT
            ).show()
            return false
        }
        if (password.length < 6) {
            Toast.makeText(this, "Password must be at least 6 characters", Toast.LENGTH_SHORT)
                .show()
            return false
        }
        return true
    }

    private fun isValidEmail(email: String): Boolean {
        val emailPattern = Patterns.EMAIL_ADDRESS
        val allowedDomains = listOf("mail.ru", "gmail.com", "yandex.ru")
        if (!emailPattern.matcher(email).matches()) {
            return false
        }
        val domain = email.substringAfter('@')
        return allowedDomains.contains(domain)
    }

    private fun registerUser(email: String, password: String, fullname: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    val userData = hashMapOf(
                        "fullName" to fullname,
                        "email" to email,
                        "password" to password //
                    )

                    // Добавление данных в коллекцию "users"
                    firestore.collection("Users").document(user!!.uid)
                        .set(userData)
                        .addOnSuccessListener {
                            Toast.makeText(
                                baseContext,
                                "Registration successful",
                                Toast.LENGTH_SHORT
                            ).show()
                            val intent = Intent(this, MainActivity::class.java)
                            startActivity(intent)
                            finish()
                        }
                        .addOnFailureListener { e ->
                            Toast.makeText(
                                baseContext,
                                "Error writing document: $e",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                } else {

                    Toast.makeText(
                        baseContext,
                        "Registration failed: ${task.exception?.message}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
    }
}