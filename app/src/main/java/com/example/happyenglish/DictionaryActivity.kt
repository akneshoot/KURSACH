package com.example.happyenglish

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
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
    private lateinit var progressBar: ProgressBar
    private var lastSearchWord: String = ""
    private lateinit var handler: Handler
    private lateinit var searchRunnable: Runnable

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
        progressBar = findViewById(R.id.progress_bar)

        apiService = RetrofitClientInstance.getRetrofitInstance().create(DictionaryApiService::class.java)

        resultsRecyclerView.layoutManager = LinearLayoutManager(this)
        progressBar = findViewById(R.id.progress_bar)

        handler = Handler(Looper.getMainLooper())
        searchRunnable = Runnable {
            searchWord(searchEditText.text.toString().trim())
        }
        progressBar.visibility = View.GONE

        searchEditText.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                handler.removeCallbacks(searchRunnable)
                handler.postDelayed(searchRunnable, 3000) // 3 секунды задержки
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

    fun searchWord(word: String) {
        lastSearchWord = word
        // Показываем ProgressBar
        progressBar.visibility = View.VISIBLE

        // Скрываем остальные элементы
        searchButton.alpha = 0f
        resultsRecyclerView.visibility = View.GONE
        noResultsPlaceholder.visibility = View.GONE
        errorPlaceholder.visibility = View.GONE
        clearHistoryButton.alpha = 0f

        val call = apiService.getWordDefinition(word)
        call.enqueue(object : Callback<List<DictionaryResponse>> {
            override fun onResponse(
                call: Call<List<DictionaryResponse>>,
                response: Response<List<DictionaryResponse>>
            ) {
                progressBar.visibility = View.GONE // Скрываем ProgressBar после завершения запроса
                // Показываем остальные элементы после завершения запроса
                searchButton.alpha = 1f
                clearHistoryButton.alpha = 1f

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
                progressBar.visibility = View.GONE // Скрываем ProgressBar при ошибке
                // Показываем остальные элементы при ошибке
                searchButton.alpha = 1f
                clearHistoryButton.alpha = 1f
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
    override fun onSaveInstanceState(outState: Bundle) {
        outState.putString("lastSearchWord", lastSearchWord)
        super.onSaveInstanceState(outState)
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
