# PlainNewsApp

**PlainNewsApp** is an app easy to use. You can save news and delete it. Also, you can search news.

## Setup

Clone the repository on your machine.
Open the project in your IDE and add your api key from [newsapi](https://newsapi.org) and everything
will be setup

## About
* PlainNewsApp brings you breaking news
* PlainNewsApp can save your favourite news stories and delete them when you want
* PlainNewsApp can find you news by query

## Screenshots

<img src="https://github.com/sichchurov/PlainNewsApp/assets/71126152/60246cbc-d60d-4c0c-bb01-f7d794e9d5c4" width="20%" height="20%" alt="breaking_news" />
<img src="https://github.com/sichchurov/PlainNewsApp/assets/71126152/522d6e3e-b306-4b74-b336-21499e37ef7b" width="20%" height="20%" alt="searching_news" />
<img src="https://github.com/sichchurov/PlainNewsApp/assets/71126152/0dd38716-876c-4a5e-a2f2-dc8193e7bd99" width="20%" height="20%" alt="articlle_webview" />
<img src="https://github.com/sichchurov/PlainNewsApp/assets/71126152/87161994-2cad-4f74-8018-5f786bd1a13c" width="20%" height="20%" alt="saved_news" />

## Built With
- [Kotlin](https://kotlinlang.org/) - Official programming language for Android development.
- [Coroutines](https://kotlinlang.org/docs/reference/coroutines-overview.html) - For asynchronous and more..
- [Android Architecture Components](https://developer.android.com/topic/libraries/architecture) - Collection of libraries that help you design robust, testable, and maintainable apps.
  - [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) - Stores UI-related data that isn't destroyed on UI changes. 
  - [ViewBinding](https://developer.android.com/topic/libraries/view-binding) - Generates a binding class for each XML layout file present in that module and allows you to more easily write code that interacts with views.
  - [Room](https://developer.android.com/training/data-storage/room) - Room is an android library which is an ORM which wraps android's native SQLite database
  - [Navigation](https://developer.android.com/guide/navigation) - Android Jetpack's Navigation component helps you implement navigation
- [Dagger 2](https://dagger.dev/dev-guide/) - Is a fully static, compile-time dependency injection framework for Java, Kotlin, and Android
- [Retrofit](https://square.github.io/retrofit/) - A type-safe HTTP client for Android and Java.
- [GSON](https://github.com/google/gson) - A modern JSON library for Kotlin and Java.
- [GSON Converter](https://github.com/square/retrofit/tree/master/retrofit-converters/gson) - A Converter which uses Moshi for serialization to and from JSON.
- [Glide](https://github.com/bumptech/glide) - An image loading and caching library for Android focused on smooth scrolling

## Architecture
This app uses [***MVVM (Model View View-Model)***](https://developer.android.com/jetpack/docs/guide#recommended-app-arch) architecture.

![](https://developer.android.com/topic/libraries/architecture/images/final-architecture.png)
