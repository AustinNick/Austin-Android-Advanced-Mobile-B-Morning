package com.example.austin_androidadvance.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Film(
    val id: Int,
    val title: String,
    val vote_average: Int,
    val poster_path: String,
    val genre_ids: List<Genre>
): Parcelable
