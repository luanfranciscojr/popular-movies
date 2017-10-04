package com.nanodegree.popularmovies.dto

import android.os.Parcel
import android.os.Parcelable
import java.util.*

/**
 * Created by luan_ on 24/08/2017.
 */
data class MovieDTO(var posterPath: String,
                    private var isAdult: Boolean,
                    private var overview: String,
                    private var releaseDate: Date?,
                    private var genreId: Array<Int>?,
                    var id: Long,
                    var originalTitle: String,
                    private var originalLanguage: String,
                    private var title: String,
                    private var backdropPath: String?,
                    private var popularity: Double,
                    private var voteCount: Int,
                    private var isVideo: Boolean,
                    private var number: Int) : Parcelable {
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

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as MovieDTO

        if (posterPath != other.posterPath) return false
        if (isAdult != other.isAdult) return false
        if (overview != other.overview) return false
        if (releaseDate != other.releaseDate) return false
        if (!Arrays.equals(genreId, other.genreId)) return false
        if (id != other.id) return false
        if (originalTitle != other.originalTitle) return false
        if (originalLanguage != other.originalLanguage) return false
        if (title != other.title) return false
        if (backdropPath != other.backdropPath) return false
        if (popularity != other.popularity) return false
        if (voteCount != other.voteCount) return false
        if (isVideo != other.isVideo) return false
        if (number != other.number) return false

        return true
    }

    override fun hashCode(): Int {
        var result = posterPath.hashCode()
        result = 31 * result + isAdult.hashCode()
        result = 31 * result + overview.hashCode()
        result = 31 * result + (releaseDate?.hashCode() ?: 0)
        result = 31 * result + (genreId?.let { Arrays.hashCode(it) } ?: 0)
        result = 31 * result + id.hashCode()
        result = 31 * result + originalTitle.hashCode()
        result = 31 * result + originalLanguage.hashCode()
        result = 31 * result + title.hashCode()
        result = 31 * result + (backdropPath?.hashCode() ?: 0)
        result = 31 * result + popularity.hashCode()
        result = 31 * result + voteCount
        result = 31 * result + isVideo.hashCode()
        result = 31 * result + number
        return result
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<MovieDTO> = object : Parcelable.Creator<MovieDTO> {
            override fun createFromParcel(source: Parcel): MovieDTO = MovieDTO(source)
            override fun newArray(size: Int): Array<MovieDTO?> = arrayOfNulls(size)
        }
    }
}
