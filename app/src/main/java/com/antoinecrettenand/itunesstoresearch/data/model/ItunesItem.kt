package com.antoinecrettenand.itunesstoresearch.data.model

import android.os.Parcelable
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import androidx.recyclerview.widget.DiffUtil
import kotlinx.parcelize.Parcelize

@Parcelize
data class ItunesItem(
    val artistId: Int?,
    val artistName: String?,
    val artistViewUrl: String?,
    val artworkUrl100: String?,
    val artworkUrl30: String?,
    val artworkUrl60: String?,
    val collectionArtistId: Int?,
    val collectionArtistViewUrl: String?,
    val collectionCensoredName: String?,
    val collectionExplicitness: String?,
    val collectionHdPrice: Double?,
    val collectionId: Int?,
    val collectionName: String?,
    val collectionPrice: Double?,
    val collectionViewUrl: String?,
    val contentAdvisoryRating: String?,
    val copyright: String?,
    val country: String?,
    val currency: String?,
    val description: String?,
    val discCount: Int?,
    val discNumber: Int?,
    val hasITunesExtras: Boolean?,
    val isStreamable: Boolean?,
    val kind: String?,
    val longDescription: String?,
    val previewUrl: String?,
    val primaryGenreName: String?,
    val releaseDate: String?,
    val shortDescription: String?,
    val trackCensoredName: String?,
    val trackCount: Int?,
    val trackExplicitness: String?,
    val trackHdPrice: Double?,
    val trackHdRentalPrice: Double?,
    val trackId: Int?,
    val trackName: String?,
    val trackNumber: Int?,
    val trackPrice: Double?,
    val trackRentalPrice: Double?,
    val trackTimeMillis: Int?,
    val trackViewUrl: String?,
    val wrapperType: String?
) : Parcelable {
    fun getReadableReleaseDate(): String {
        val inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'")
        val outputFormatter = DateTimeFormatter.ofPattern("yyyy")

        val parsedDate = LocalDateTime.parse(releaseDate, inputFormatter)
        return parsedDate.format(outputFormatter)
    }

    fun getReadableType(): String {
        return kind!!.split("-")
            .joinToString(" ") { word ->
                word.replaceFirstChar { char ->
                    if (char.isLowerCase()) char.titlecase() else char.toString()
                }
            }
    }

    fun getReadableShortDescription(): String {
        return if (shortDescription.isNullOrBlank())
            if (longDescription.isNullOrBlank())
                " "
            else
                longDescription
        else
            shortDescription

    }

    fun getReadableLongDescription(): String {
        return if (longDescription.isNullOrBlank())
            "There is no description for this media."
        else
            longDescription
    }

}

class ItunesItemDiffCallback : DiffUtil.ItemCallback<ItunesItem>() {
    override fun areItemsTheSame(oldItem: ItunesItem, newItem: ItunesItem): Boolean {
        return oldItem.trackId == newItem.trackId
    }

    override fun areContentsTheSame(oldItem: ItunesItem, newItem: ItunesItem): Boolean {
        return oldItem == newItem
    }
}
