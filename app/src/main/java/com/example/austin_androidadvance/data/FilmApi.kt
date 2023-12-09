package com.example.austin_androidadvance.data

import com.example.austin_androidadvance.data.response.FilmResponse
import com.example.austin_androidadvance.model.Film
import retrofit2.Call
import retrofit2.http.GET

interface FilmApi {
    @GET("movie/popular")
    fun getFilms(): Call<FilmResponse>
}