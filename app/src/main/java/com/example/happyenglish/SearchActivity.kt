package com.example.happyenglish

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.happyenglish.unsplash.SearchResponse
import com.example.happyenglish.unsplash.UnsplashService
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SearchActivity : AppCompatActivity() {

    private lateinit var searchEditText: EditText
    private lateinit var searchButton: Button
    private lateinit var imageView: ImageView
    private lateinit var backButton: Button

    private val unsplashService: UnsplashService by lazy {
        Retrofit.Builder()
            .baseUrl("https://api.unsplash.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(UnsplashService::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_searchimage)

        searchEditText = findViewById(R.id.searchEditText)
        searchButton = findViewById(R.id.searchButton)
        imageView = findViewById(R.id.imageView)
        backButton = findViewById(R.id.backButton)

        searchButton.setOnClickListener {
            val query = searchEditText.text.toString().trim()
            if (query.isNotEmpty()) {
                searchImages(query)
            } else {
                Toast.makeText(this, "Please enter a search query", Toast.LENGTH_SHORT).show()
            }
        }
        backButton.setOnClickListener {
            finish() // Закрываем текущую активити и возвращаемся к предыдущей (в данном случае MainActivity)
        }
    }

    private fun searchImages(query: String) {
        unsplashService.searchPhotos(query, "mmskAuZ8HUYRhbeqT5f3x11KJ7WBSEpLjHV9P6Myr1g").enqueue(object : Callback<SearchResponse> {
            override fun onResponse(call: Call<SearchResponse>, response: Response<SearchResponse>) {
                if (response.isSuccessful) {
                    val photos = response.body()?.results
                    photos?.forEach { photo ->
                        Log.d("SearchActivity", "Image URL: ${photo.urls.regular}")
                        // Загрузка и отображение изображения с использованием Picasso
                        runOnUiThread {
                            Picasso.get().load(photo.urls.regular).into(imageView)
                        }
                    }
                } else {
                    Log.e("SearchActivity", "Failed to search images: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<SearchResponse>, t: Throwable) {
                Log.e("SearchActivity", "Failed to search images", t)
            }
        })
    }
}


