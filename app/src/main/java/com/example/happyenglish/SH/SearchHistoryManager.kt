package com.example.happyenglish.SH

import android.content.Context
import android.util.Log
import androidx.core.content.edit

object SearchHistoryManager {
    private const val PREF_NAME = "SearchHistory"
    private const val MAX_HISTORY_SIZE = 10

    fun saveSearchQuery(context: Context, query: String) {
        val sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        val history = getSearchHistory(context).toMutableList()

        history.add(0, query)
        if (history.size > MAX_HISTORY_SIZE) {
            history.removeLast()
        }

        sharedPreferences.edit {
            putStringSet("history", history.toSet())
        }

        Log.d("SearchHistory", "Saved history: $history")
    }

    fun getSearchHistory(context: Context): List<String> {
        val sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        val history = sharedPreferences.getStringSet("history", setOf())?.toList() ?: emptyList()
        Log.d("SearchHistory", "Retrieved history: $history")
        return history
    }

    fun clearSearchHistory(context: Context) {
        val sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        sharedPreferences.edit {
            remove("history")
        }
    }
}
