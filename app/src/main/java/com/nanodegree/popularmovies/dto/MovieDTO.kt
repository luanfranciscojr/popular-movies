package com.nanodegree.popularmovies.dto

import android.os.Parcel
import android.os.Parcelable
import java.util.*

/**
 * Created by luan_ on 24/08/2017.
 */
data class MovieDTO(var posterPath: String,
                    var isAdult: Boolean,
                    var overview: String,
                    var releaseDate: Date?,
                    var genreId: Array<Int>?,
                    var id: Long,
                    var originalTitle: String,
                    var originalLanguage: String,
                    var title: String,
                    var backdropPath: String?,
                    var popularity: Double,
                    var voteCount: Int,
                    var isVideo: Boolean,
                    var number: Int) : Parcelable {
    constructor(source: Parcel) : this(
            source.readString(),
            1 == source.readInt(),
            source.readString(),
            source.readSerializable() as Date,
            source.createIntArray()?.toTypedArray(),
            source.readLong(),
            source.readString(),
            source.readString(),
            source.readString(),
            source.readString(),
            source.readDouble(),
            source.readInt(),
            1 == source.readInt(),
            source.readInt()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeString(posterPath)
        writeInt((if (isAdult) 1 else 0))
        writeString(overview)
        writeSerializable(releaseDate)
        writeIntArray(genreId?.toIntArray())
        writeLong(id)
        writeString(originalTitle)
        writeString(originalLanguage)
        writeString(title)
        writeString(backdropPath)
        writeDouble(popularity)
        writeInt(voteCount)
        writeInt((if (isVideo) 1 else 0))
        writeInt(number)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<MovieDTO> = object : Parcelable.Creator<MovieDTO> {
            override fun createFromParcel(source: Parcel): MovieDTO = MovieDTO(source)
            override fun newArray(size: Int): Array<MovieDTO?> = arrayOfNulls(size)
        }
    }
}
