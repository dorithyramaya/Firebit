# Firebit
###### Firebase on Android made easier.


### Getting started
Currently, the library can only provide so little :/ bear with us.

1. Open **project-level `build.gradle`** and make sure the `repositories` block includes `jcenter()`.
```
allprojects {
  repositories {
    google()
    jcenter()
  }
}
```
1. Open **app-level `build.gradle`**, usually shown as **build.gradle (Module: app)** under _Gradle Scripts_, and insert the line below to add the library you need to your app.

```
dependencies {
  // ...
  implementation 'com.owellox.firebit:auth:0.0.4'
  implementation 'com.owellox.firebit:content:0.0.1'
  // ...
}
```
