package com.example.austin_androidadvance.utils

object DataMapper {
    fun mapGenre(genreIds: List<Int?>?): String {
        val genreList = mutableListOf<String>()
        if (genreIds != null) {
            for (id in genreIds) {
                when (id) {
                    28 -> genreList.add("Action")
                    12 -> genreList.add("Adventure")
                    16 -> genreList.add("Animation")
                    35 -> genreList.add("Comedy")
                    80 -> genreList.add("Crime")
                    99 -> genreList.add("Documentary")
                    18 -> genreList.add("Drama")
                    10751 -> genreList.add("Family")
                    14 -> genreList.add("Fantasy")
                    36 -> genreList.add("History")
                    27 -> genreList.add("Horror")
                    10402 -> genreList.add("Music")
                    9648 -> genreList.add("Mystery")
                    10749 -> genreList.add("Romance")
                    878 -> genreList.add("Science Fiction")
                    10770 -> genreList.add("TV Movie")
                    53 -> genreList.add("Thriller")
                    10752 -> genreList.add("War")
                    37 -> genreList.add("Western")
                }
            }
        }
        return genreList.joinToString(", ")
    }
}