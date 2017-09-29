package com.nanodegree.popularmovies.dto
data class GenericDTO<T> (var page: Int,var totalResults: Int, var totalPages: Int,var results: List<T>)
