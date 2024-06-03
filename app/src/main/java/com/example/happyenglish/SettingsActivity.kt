package com.example.happyenglish

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.SwitchCompat

class SettingsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Установите тему в зависимости от текущего состояния
        if (isDarkThemeEnabled()) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }

        setContentView(R.layout.activity_settings)

        val backButton: Button = findViewById(R.id.back_button)
        backButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
        //Переключатель темы
        val themeSwitch = findViewById<SwitchCompat>(R.id.theme_switch)

        themeSwitch.isChecked = isDarkThemeEnabled()

        themeSwitch.setOnCheckedChangeListener { _, isChecked ->
            setDarkThemeEnabled(isChecked)
            AppCompatDelegate.setDefaultNightMode(
                if (isChecked) AppCompatDelegate.MODE_NIGHT_YES else AppCompatDelegate.MODE_NIGHT_NO
            )
        }
    }
    // Функция проверки, включена ли темная тема
    private fun isDarkThemeEnabled(): Boolean {
        val sharedPreferences: SharedPreferences = getSharedPreferences("MySettings", MODE_PRIVATE)
        return sharedPreferences.getBoolean("isDarkThemeEnabled", false)
    }
    // Функция для сохранения состояния темной темы
    private fun setDarkThemeEnabled(isEnabled: Boolean) {
        val sharedPreferences: SharedPreferences = getSharedPreferences("MySettings", MODE_PRIVATE)
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.putBoolean("isDarkThemeEnabled", isEnabled)
        editor.apply()
    }
}
