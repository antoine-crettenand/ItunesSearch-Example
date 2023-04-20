# Mobile Exercise: iTunes Store Lookup App

An Android application, written in Kotlin, that allows users to search through the iTunes catalog by media category and
search term. The app utilizes the Apple's iTunes Store API and Retrofit library for GET requests.

## Features

* Search through the iTunes catalog by selecting a media category (Movie, Podcast, EBook, etc.) and entering a search
  term (String)
* Access to the Swiss (CH) iTunes catalog by default
* Results displayed in a list format
* Clickable results that redirect users to their respective preview page on the iTunes Store
* Limit of 50 results per query
* Basic input text error handling (no line breaks allowed, etc.)
* **Requires internet access permission to function**

## Design

* Minimalistic design with one activity containing two fragments: Search & Details
* Shared ViewModel between the two fragments

## Getting Started

### Installation

1. Clone the repository:

```
git clone https://github.com/antoine-crettenand/ItunesSearch-Example.git
```

2. Open the project in Android Studio and wait for Gradle sync to complete.

3. Run the app on an emulator or physical device.

## Built With

* [Kotlin](https://kotlinlang.org/) - Programming language
* [Android Studio](https://developer.android.com/studio/) - IDE for Android app development
* [Retrofit](https://square.github.io/retrofit/) - Networking library for Android
* [Apple's iTunes Store API](https://affiliate.itunes.apple.com/resources/documentation/itunes-store-web-service-search-api/) -
  API for iTunes Store search

### Dependencies

* [AppCompat](https://developer.android.com/jetpack/androidx/releases/appcompat) - AndroidX AppCompat library
* [Google Material Design](https://material.io/design/) - Material Design UI components
* [ConstraintLayout](https://developer.android.com/training/constraint-layout/) - Flexible layout manager for Android
  views
* [Core KTX](https://developer.android.com/kotlin/ktx/) - Kotlin extensions for Android framework APIs
* [RecyclerView](https://developer.android.com/jetpack/androidx/releases/recyclerview) - RecyclerView for displaying
  lists and grids in Android apps
* [Glide](https://github.com/bumptech/glide) - Image loading and caching library
* [OkHttp Logging Interceptor](https://github.com/square/okhttp/tree/master/okhttp-logging-interceptor) - HTTP request
  and response logging for Retrofit
* [CardView](https://developer.android.com/guide/topics/ui/layout/cardview) - Material Design UI component for
  displaying content in a card format
* [Navigation](https://developer.android.com/guide/navigation/) - Android Jetpack's Navigation component for in-app
  navigation

## License

This project is licensed under the MIT License - see
the [LICENSE.md](https://github.com/antoine-crettenand/ItunesSearch-Example.git/blob/main/LICENSE.md) file for details.