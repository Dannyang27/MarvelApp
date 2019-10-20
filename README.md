# Marvel App
This app retrieves the latest comics from developer.marvel.com and it displays its info (diamondCode, upccode, title, release date, summary, authors...).

Android project using Kotlin as the main language, other technologies that have been used in the project were:
- Retrofit
- Coroutines
- Room
- Picasso

Things to bear in mind: <br/>
-First time that the app is launched every day, it tries to retrieve new data from the server (it may take few seconds depending on your internet connection), but the second time will fetch the data from the Database.

-When long pressing a Comic to add it as favourite, you need to refresh the SavedFragment by SwipeRefresh from the top as you usually do when browsing on the web.

Here is some screenshots from the app:
<br/>
<img src="https://github.com/Dannyang27/MarvelApp/blob/master/readme_images/comiclist.png" width="250" height="450">
<img src="https://github.com/Dannyang27/MarvelApp/blob/master/readme_images/comicgrid.png" width="250" height="450">
<img src="https://github.com/Dannyang27/MarvelApp/blob/master/readme_images/comicinfo.png" width="250" height="450">
<br/>
<img src="https://github.com/Dannyang27/MarvelApp/blob/master/readme_images/emptylist.png" width="250" height="450">
<img src="https://github.com/Dannyang27/MarvelApp/blob/master/readme_images/comicsettings.png" width="250" height="450">
