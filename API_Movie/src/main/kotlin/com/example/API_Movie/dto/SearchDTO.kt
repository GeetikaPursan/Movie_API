package com.example.API_Movie.dto

data class SearchDTO(
    val search: String? = null,
    val searchBy: String? = null,
    val sortBy: String? = null,
)

data class MultiSearchDTO(
    val search: List<String>? = null,
    val searchBy: List<String>? = null,
    val sortBy: String? = null,
)