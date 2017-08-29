package com.nanodegree.popularmovies.model.dto

/**
 * Created by luan_ on 24/08/2017.
 */

class MovieDTO {

    var poster_path: String? = null
    var isAdult: Boolean = false
    var overview: String? = null
    var releaseDate: String? = null
    var genreId: Array<Int>? = null
    var id: Long? = null
    var originalTitle: String? = null
    var originalLanguage: String? = null
    var title: String? = null
    var backdropPath: String? = null
    var popularity: Int? = null
    var voteCount: Int? = null
    var isVideo: Boolean = false
    var number: Int? = null
}
