package com.example.happyenglish

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.view.View
import android.widget.Button
import android.widget.EditText
import java.util.Locale

class SPEECH : Activity() {
    var pitch: Float = -1f
    var speechRate: Float = -1f
    var btnBack: Button? = null
    var btnClear: Button? = null
    var ttobj: TextToSpeech? = null
    private var write: EditText? = null


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.speech)

        write = findViewById<View>(R.id.txtInput) as EditText


        ttobj = TextToSpeech(
            applicationContext
        ) { status ->
            if (status != TextToSpeech.ERROR) {
                ttobj!!.setLanguage(Locale.US)
            }
        }



        addButtonListener()
    }

    private fun addButtonListener() {
        btnBack = findViewById<View>(R.id.btnBack) as Button
        btnBack!!.setOnClickListener {
            val e = Intent(this@SPEECH, MainActivity::class.java)
            startActivity(e)
        }

        btnClear = findViewById<View>(R.id.btnClear) as Button
        btnClear!!.setOnClickListener {
            write!!.setText("")
            ttobj!!.stop()
        }
    }


    public override fun onPause() {
        if (ttobj != null) {
            ttobj!!.stop()
        }
        super.onPause()
    }

    fun speakText(view: View?) {
        ttobj!!.setPitch(pitch)
        ttobj!!.setSpeechRate(speechRate)
        val toSpeak = write!!.text.toString()
        ttobj!!.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null)
    }
}
