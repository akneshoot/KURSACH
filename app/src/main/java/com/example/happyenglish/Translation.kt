package com.example.happyenglish

import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class Translation : AppCompatActivity() {

    private lateinit var searchInput: EditText
    private lateinit var clearButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_translation)

        searchInput = findViewById(R.id.searchInput)
        clearButton = findViewById(R.id.clearButton)

        // Восстановление состояния поля ввода
        savedInstanceState?.let {
            val searchText = it.getString("searchText")
            searchText?.let { text ->
                searchInput.setText(text)
                if (!TextUtils.isEmpty(text)) {
                    clearButton.visibility = View.VISIBLE
                }
            }
        }

        // Обработчик для поля ввода
        searchInput.setOnFocusChangeListener { view, hasFocus ->
            if (hasFocus && !TextUtils.isEmpty(searchInput.text)) {
                // Показываем кнопку "Очистить"
                clearButton.visibility = View.VISIBLE
            } else {
                // Скрываем кнопку "Очистить"
                clearButton.visibility = View.GONE
            }
        }

        // Обработчик для кнопки "Очистить"
        clearButton.setOnClickListener {
            searchInput.setText("")
            // Скрываем клавиатуру
            val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(searchInput.windowToken, 0)
        }

        // Обработчик для кнопки "Назад"
        findViewById<Button>(R.id.backButton).setOnClickListener { finish() }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("searchText", searchInput.text.toString())
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        savedInstanceState.getString("searchText")?.let { searchText ->
            searchInput.setText(searchText)
            if (!TextUtils.isEmpty(searchText)) {
                clearButton.visibility = View.VISIBLE
            }
        }
    }
}