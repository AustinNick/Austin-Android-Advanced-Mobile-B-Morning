package com.example.austin_androidadvance.data

object RetrofitClient {
    private const val BASE_URL = "https://api.themoviedb.org/3/"

    val instance: FilmApi by lazy {
        val retrofit = retrofit2.Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(retrofit2.converter.gson.GsonConverterFactory.create())
            .build()

        retrofit.create(FilmApi::class.java)
    }
}