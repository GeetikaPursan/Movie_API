package com.example.API_Movie.entity
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
data class Movie (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long?,
    val runtime: String?,
    val title: String?,
    val movieYear: Int?,
    val rated: String?,
    val released: String?,
    val genre: String?,
    val director: String?,
    val writer: String?,
    val actors: String?,
    val plot: String?,
    val language: String?,
    val country: String?,
    val awards: String?,
    val poster: String?,
    val metascore: Int?,
    val imdbRating: Double?,
    val imdbVotes: Int?,
    val imdbID: String?,
    val type: String?,
    val dvd: String?,
    val boxOffice: String?,
    val production: String?,
)
