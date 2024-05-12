package com.example.happyenglish

import android.content.Intent
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener

class OnBoarding : AppCompatActivity() {
    var introViewPagerAdapter: IntroViewPagerAdapter? = null
    var tabLayout: TabLayout? = null
    var position: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requestWindowFeature(Window.FEATURE_NO_TITLE)
        this.window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        supportActionBar!!.hide()

        setContentView(R.layout.on_boarding)

        val introList: MutableList<Intro> = ArrayList()

        introList.add(
            Intro(
                "Хочешь учить английский быстро и легко?",
                "HappyEnglish!",
                R.drawable.onboarding1
            )
        )
        introList.add(
            Intro(
                "Хочешь проверить свои знания английского?",
                "HappyEnglish!",
                R.drawable.onboarding2
            )
        )
        introList.add(
            Intro(
                "Хочешь узнавать правильное произношение?",
                "HappyEnglish!",
                R.drawable.onboarding3
            )
        )


        val viewPager = findViewById<ViewPager>(R.id.screenPager)
        val next = findViewById<Button>(R.id.button)
        tabLayout = findViewById(R.id.tabIndicator)
        introViewPagerAdapter = IntroViewPagerAdapter(this, introList)
        viewPager.adapter = introViewPagerAdapter
        tabLayout?.setupWithViewPager(viewPager)

        position = viewPager.currentItem


        next.setOnClickListener {
            if (position < introList.size) {
                position++
                viewPager.currentItem = position
            }
            if (position == introList.size) {
                savePrefData()
                Toast.makeText(this@OnBoarding, "Go to registration!", Toast.LENGTH_SHORT).show()
                val intent = Intent(this@OnBoarding, Vhod::class.java)
                startActivity(intent)
            }
        }

        tabLayout?.addOnTabSelectedListener(object : OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                position = tab.position
                if (tab.position == introList.size - 1) {
                    next.text = "Get Started"
                } else {
                    next.text = "Next"
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {
            }

            override fun onTabReselected(tab: TabLayout.Tab) {
            }
        })
    }

    private fun savePrefData() {
        val sharedPreferences = applicationContext.getSharedPreferences("mypref", MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putBoolean("isFristTimeLaunch", true)
        editor.apply()
    }

    private fun restorePref(): Boolean {
        val sharedPreferences = applicationContext.getSharedPreferences("mypref", MODE_PRIVATE)
        return sharedPreferences.getBoolean("isFristTimeLaunch", false)
    }
}
