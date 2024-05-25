package com.example.happyenglish.SH

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.happyenglish.DictionaryActivity
import com.example.happyenglish.R

class SearchHistoryAdapter(private val context: Context, private val history: List<String>) : RecyclerView.Adapter<SearchHistoryAdapter.ViewHolder>() {

    private val handler = Handler(Looper.getMainLooper())

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.search_history_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = history[position]
        holder.bind(item)
        Log.d("SearchHistoryAdapter", "Binding item at position $position: $item")
    }

    override fun getItemCount(): Int {
        return history.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val queryTextView: TextView = itemView.findViewById(R.id.query_text_view)

        fun bind(query: String) {
            queryTextView.text = query

            itemView.setOnClickListener {
                val editText = (context as? DictionaryActivity)?.findViewById<EditText>(R.id.search_auto_complete_text_view)
                editText?.setText(query)
                editText?.setSelection(editText.text.length)

                // Удаляем любые предыдущие запланированные запросы
                handler.removeCallbacksAndMessages(null)

                // Планируем новый запрос через 2 секунды
                handler.postDelayed({
                    if (context is DictionaryActivity) {
                        context.searchWord(query)
                    }
                }, 500)
            }
        }
    }
}



