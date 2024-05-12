package com.example.happyenglish

import android.content.Context
import android.speech.tts.TextToSpeech
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView

class WordArrayAdapter(
    private val context: Context,
    private val values: Array<String>,
    private val ttobj: TextToSpeech
) : ArrayAdapter<String?>(
    context, R.layout.word, values
), View.OnClickListener {
    private val imageResourceMap = HashMap<String, Int>()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val inflater = LayoutInflater.from(context)

        val rowView = inflater.inflate(R.layout.word, parent, false)
        val textView = rowView.findViewById<TextView>(R.id.txt)
        val imageView = rowView.findViewById<ImageView>(R.id.logo)
        textView.text = values[position]

        imageView.setImageResource(R.drawable.speaker)

        rowView.setOnClickListener(this)

        return rowView
    }

    override fun onClick(v: View) {
        val word = (v.findViewById<View>(R.id.txt) as TextView).text.toString()
        playSound(word)
    }

    private fun playSound(word: String) {
        ttobj.speak(word, TextToSpeech.QUEUE_FLUSH, null)
    }
}
