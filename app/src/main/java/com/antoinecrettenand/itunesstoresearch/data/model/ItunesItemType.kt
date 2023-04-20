package com.antoinecrettenand.itunesstoresearch.data.model

enum class ItunesItemType(val apiName: String) {
    MOVIE("movie"),
    PODCAST("podcast"),
    MUSIC("music"),
    MUSIC_VIDEO("musicVideo"),
    AUDIO_BOOK("audiobook"),
    SHORT_FILM("shortFilm"),
    TV_SHOW("tvShow"),
    SOFTWARE("software"),
    EBOOK("ebook"),
    ALL("all");

    companion object {
        fun fromString(code: String): ItunesItemType {
            return ItunesItemType.valueOf(code.trim().replace(' ', '_').uppercase())
        }
    }
}