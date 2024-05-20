package com.example.happyenglish

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
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
    private lateinit var apiService: DictionaryApiService
    private lateinit var adapter: DictionaryAdapter
    private var lastSearchWord: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dictionary)
        val backButton: Button = findViewById(R.id.back_button)
        backButton.setOnClickListener {
            finish()
        }

        searchEditText = findViewById(R.id.search_edit_text)
        searchButton = findViewById(R.id.search_button)
        resultsRecyclerView = findViewById(R.id.results_recycler_view)
        noResultsPlaceholder = findViewById(R.id.no_results_placeholder)
        noResultsTextView = findViewById(R.id.no_results_text_view)
        noResultsRetryButton = findViewById(R.id.no_results_retry_button)
        errorPlaceholder = findViewById(R.id.error_placeholder)
        errorTextView = findViewById(R.id.error_text_view)
        errorRetryButton = findViewById(R.id.error_retry_button)

        // Создание экземпляра сервиса для работы с API
        apiService = RetrofitClientInstance.getRetrofitInstance().create(DictionaryApiService::class.java)

        // Установка LayoutManager для RecyclerView
        resultsRecyclerView.layoutManager = LinearLayoutManager(this)

        searchButton.setOnClickListener {
            val word = searchEditText.text.toString().trim()
            if (word.isNotEmpty()) {
                searchWord(word)
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

        // Добавляем обработчик события для кнопки "Обновить" в плейсхолдере ошибки
        errorRetryButton.setOnClickListener {
            if (lastSearchWord.isNotEmpty()) {
                hidePlaceholders()
                searchWord(lastSearchWord) // Повторно отправляем последний запрос
            }
        }
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

    private fun showNoResultsPlaceholder() {
        noResultsPlaceholder.visibility = LinearLayout.VISIBLE
        noResultsTextView.visibility = TextView.VISIBLE
        noResultsTextView.text = "Ошибка"
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

    private fun hidePlaceholders() {
        noResultsPlaceholder.visibility = LinearLayout.GONE
        errorPlaceholder.visibility = LinearLayout.GONE
        resultsRecyclerView.visibility = RecyclerView.VISIBLE
    }
}
