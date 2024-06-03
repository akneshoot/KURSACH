package com.example.happyenglish.dict

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.happyenglish.DictionaryActivity
import com.example.happyenglish.R
import com.example.happyenglish.SH.SearchHistoryManager

class DictionaryAdapter(private val meanings: List<Meaning>) :
    RecyclerView.Adapter<DictionaryAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val partOfSpeechTextView: TextView = itemView.findViewById(R.id.part_of_speech_text_view)
        val definitionTextView: TextView = itemView.findViewById(R.id.definition_text_view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_meaning, parent, false)
        return ViewHolder(view)
    }
    // Привязка данных к ViewHolder
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val meaning = meanings[position]
        holder.partOfSpeechTextView.text = meaning.partOfSpeech
        holder.definitionTextView.text = meaning.definitions.joinToString("\n") { it.definition }

        // Добавляем обработчик нажатия на элемент
        holder.itemView.setOnClickListener {
            val context = holder.itemView.context
            val word = meaning.definitions.firstOrNull()?.definition ?: ""
            SearchHistoryManager.saveSearchQuery(context, word)
            // Обновляем UI истории поиска
            (context as? DictionaryActivity)?.updateSearchHistoryUI()
        }
    }


    override fun getItemCount(): Int {
        return meanings.size
    }
}
