package com.example.austin_androidadvance.data

import okhttp3.Interceptor
import okhttp3.OkHttpClient

object RetrofitClient {
    private const val BASE_URL = "https://api.themoviedb.org/3/"
    private const val API_KEY = "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIwNTI3ZDA5NDAxYWI1YjIyN2JiYmY5MWU3MzUwMDU1MyIsInN1YiI6IjY1Njg4NjVkYTQ0ZDA5MDEwZDMzOGI0NCIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.0SQN3gCnqRrCcsVfkZzHLiBZRb_iwJLzlerCKrHLBRA"

    private val interceptor = Interceptor { chain ->
        val request = chain.request().newBuilder()
            .addHeader("Authorization", "Bearer $API_KEY")
            .build()
        chain.proceed(request)
    }

    private val client = OkHttpClient.Builder()
        .addInterceptor(interceptor)
        .build()

    val instance: FilmApi by lazy {
        val retrofit = retrofit2.Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(retrofit2.converter.gson.GsonConverterFactory.create())
            .client(client)
            .build()

        retrofit.create(FilmApi::class.java)
    }
}