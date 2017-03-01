# SpotifyReceiver
A demo project to demonstrate how to listen to Spotify events from your own Android app.

## Requirements
To get events from the Spotify app, you must have the application running on your phone as well as activating broadcast from the Settings panel by turning ON **Device Broadcast Status**. Without this toggle checked, the app won't be able to receive any data from Spotify. 

All documentation of how it works can be found [here](https://developer.spotify.com/technologies/spotify-android-sdk/android-media-notifications/).

## Events
Spotify broadcasts some events that allows you to retrieve what track is being played. Here is the list of what you can display in your app:

* The track id (useful for deep link redirection)
* The track artist
* The track album
* The track name
* The track length
* The track status (play/pause)
* The track current position

## Redirections
This demo project allows users to redirect to Spotify track that is currently being played/paused. **We cannot redirect to the artist page as we don't receive the artist id** through our events (only the track id). Moreover, there is a limitation to the redirection. Currently, when a user redirects to the Spotify track, the track resets and starts playing from the beginning. Also the user **loses its current playlist** to be replaced by the track's album.

Redirection documentation can be found [here](https://support.spotify.com/us/using_spotify/share_music/why-do-you-have-two-different-link-formats/).
