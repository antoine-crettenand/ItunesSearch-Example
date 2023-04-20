# Mobile Exercise

## iTunes Store Lookup App

### Functionality 
* Search through the iTunes catalog by selecting a media category (Movie, Podcast, EBook...) and inputing a search term. By default, only the swiss (CH) catalog is accessible.


## 
* Minimal design : One activity with two fragments : Search & Details.
    * GET requests are managed by Retrofit library. Format : [Apple's iTunes Store API](https://affiliate.itunes.apple.com/resources/documentation/itunes-store-web-service-search-api/).
* There is a single activity with three mandatory inputs : keywords, media type (music, audiobook, movie...) and country code (used to choose the right store, default is US).
* The results are 'clickable' and redirect to their respective preview page on the Itunes Store.
* The number of results is limited to 50 for each query.
* Basic input text error handling (no linebreaks allowed...)