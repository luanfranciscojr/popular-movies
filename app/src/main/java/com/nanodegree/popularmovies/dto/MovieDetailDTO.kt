package com.nanodegree.popularmovies.dto

import java.util.*

data class MovieDetailDTO(var backdropPath: String?,
                          var budget: Int,
                          var homepage: String,
                          var voteAverage: Double,
                          var releaseDate: Date?,
                          var overview: String?,
                          var runtime: Int?
)