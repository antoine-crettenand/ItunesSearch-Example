# recruitment-mobile-exercise

## iTunes Store Lookup App

I decided to create an app that lets you do simple searches using [Apple's iTunes Store API](https://affiliate.itunes.apple.com/resources/documentation/itunes-store-web-service-search-api/).
* There is a single activity with three mandatory inputs : keywords, media type (music, audiobook, movie...) and country code (used to choose the right store, default is US).
* The results are 'clickable' and redirect to their respective preview page on the Itunes Store.
* The number of results is limited to 50 for each query.
* Basic input text error handling (no linebreaks allowed...)