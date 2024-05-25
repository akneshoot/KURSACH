package com.example.happyenglish

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.happyenglish.R
import com.example.happyenglish.SH.SearchHistoryAdapter
import com.example.happyenglish.SH.SearchHistoryManager
import com.example.happyenglish.dict.DictionaryAdapter
import com.example.happyenglish.dict.DictionaryApiService
import com.example.happyenglish.dict.DictionaryResponse
import com.example.happyenglish.dict.RetrofitClientInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DictionaryActivity : AppCompatActivity() {
    private lateinit var searchEditText: EditText
    private lateinit var searchButton: Button
    private lateinit var resultsRecyclerView: RecyclerView
    private lateinit var noResultsPlaceholder: LinearLayout
    private lateinit var noResultsTextView: TextView
    private lateinit var noResultsRetryButton: Button
    private lateinit var errorPlaceholder: LinearLayout
    private lateinit var errorTextView: TextView
    private lateinit var errorRetryButton: Button
    private lateinit var clearHistoryButton: Button
    private lateinit var apiService: DictionaryApiService
    private lateinit var adapter: DictionaryAdapter
    private lateinit var historyAdapter: SearchHistoryAdapter
    private var lastSearchWord: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dictionary)
        val backButton: Button = findViewById(R.id.back_button)
        backButton.setOnClickListener {
            finish()
        }

        searchEditText = findViewById(R.id.search_auto_complete_text_view)
        searchButton = findViewById(R.id.search_button)
        resultsRecyclerView = findViewById(R.id.results_recycler_view)
        noResultsPlaceholder = findViewById(R.id.no_results_placeholder)
        noResultsTextView = findViewById(R.id.no_results_text_view)
        noResultsRetryButton = findViewById(R.id.no_results_retry_button)
        errorPlaceholder = findViewById(R.id.error_placeholder)
        errorTextView = findViewById(R.id.error_text_view)
        errorRetryButton = findViewById(R.id.error_retry_button)
        clearHistoryButton = findViewById(R.id.clear_history_button)

        apiService = RetrofitClientInstance.getRetrofitInstance().create(DictionaryApiService::class.java)

        resultsRecyclerView.layoutManager = LinearLayoutManager(this)



        searchEditText.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                Log.d("DictionaryActivity", "searchEditText получил фокус")
                updateSearchHistoryUI()
            } else {
                hideSearchHistoryUI()
            }
        }

        searchButton.setOnClickListener {
            val word = searchEditText.text.toString().trim()
            if (word.isNotEmpty()) {
                searchWord(word)
                searchEditText.clearFocus()
                SearchHistoryManager.saveSearchQuery(this, word) // Сохраняем поисковый запрос
            }
        }


        noResultsRetryButton.setOnClickListener {
            if (lastSearchWord.isNotEmpty()) {
                hidePlaceholders()
                searchWord(lastSearchWord)
            }
        }

        errorRetryButton.setOnClickListener {
            if (lastSearchWord.isNotEmpty()) {
                hidePlaceholders()
                searchWord(lastSearchWord)
            }
        }

        clearHistoryButton.setOnClickListener {
            SearchHistoryManager.clearSearchHistory(this)
            updateSearchHistoryUI()
        }

        updateSearchHistoryUI()
        hideSearchHistoryUI()
    }

    private fun searchWord(word: String) {
        lastSearchWord = word
        val call = apiService.getWordDefinition(word)
        call.enqueue(object : Callback<List<DictionaryResponse>> {
            override fun onResponse(
                call: Call<List<DictionaryResponse>>,
                response: Response<List<DictionaryResponse>>
            ) {
                if (response.isSuccessful && response.body() != null) {
                    val meanings = response.body()!![0].meanings
                    if (meanings.isEmpty()) {
                        showNoResultsPlaceholder()
                    } else {
                        adapter = DictionaryAdapter(meanings)
                        resultsRecyclerView.adapter = adapter
                        hidePlaceholders()
                    }
                } else {
                    showErrorPlaceholder()
                }
            }

            override fun onFailure(call: Call<List<DictionaryResponse>>, t: Throwable) {
                showErrorPlaceholder()
            }
        })
    }

    fun updateSearchHistoryUI() {
        val fullHistory = SearchHistoryManager.getSearchHistory(this)
        val history = if (fullHistory.size > 10) fullHistory.subList(0, 10) else fullHistory

        val historyRecyclerView: RecyclerView = findViewById(R.id.history_recycler_view)
        if (history.isNotEmpty()) {
            val historyPlaceholder: LinearLayout = findViewById(R.id.history_placeholder)
            historyPlaceholder.visibility = View.VISIBLE

            historyRecyclerView.layoutManager = LinearLayoutManager(this)

            historyAdapter = SearchHistoryAdapter(this, history)
            historyRecyclerView.adapter = historyAdapter

            historyRecyclerView.visibility = View.VISIBLE
        } else {
            historyRecyclerView.visibility = View.GONE
        }
    }






    private fun hideSearchHistoryUI() {
        val historyPlaceholder: LinearLayout = findViewById(R.id.history_placeholder)
        historyPlaceholder.visibility = View.GONE

        val historyRecyclerView: RecyclerView = findViewById(R.id.history_recycler_view)
        historyRecyclerView.visibility = View.GONE
    }

    private fun hidePlaceholders() {
        noResultsPlaceholder.visibility = LinearLayout.GONE
        errorPlaceholder.visibility = LinearLayout.GONE
        resultsRecyclerView.visibility = RecyclerView.VISIBLE
    }

    private fun showNoResultsPlaceholder() {
        noResultsPlaceholder.visibility = LinearLayout.VISIBLE
        noResultsTextView.visibility = TextView.VISIBLE
        noResultsTextView.text = "Нет результатов"
        noResultsRetryButton.visibility = Button.VISIBLE
        resultsRecyclerView.visibility = RecyclerView.GONE
    }

    private fun showErrorPlaceholder() {
        errorPlaceholder.visibility = LinearLayout.VISIBLE
        errorTextView.visibility = TextView.VISIBLE
        errorTextView.text = "Ошибка"
        errorRetryButton.visibility = Button.VISIBLE
        resultsRecyclerView.visibility = RecyclerView.GONE
    }
}
