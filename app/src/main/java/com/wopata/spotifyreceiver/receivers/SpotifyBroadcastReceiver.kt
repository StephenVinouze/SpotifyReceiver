package com.wopata.spotifyreceiver.receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import com.wopata.spotifyreceiver.models.Track


/**
 * Created by stephenvinouze on 22/02/2017.
 */
class SpotifyBroadcastReceiver: BroadcastReceiver() {

    companion object {
        private val SPOTIFY_PACKAGE = "com.spotify.music"
        private val METADATA_CHANGED = SPOTIFY_PACKAGE + ".metadatachanged"
        private val PLAYBACK_STATE_CHANGED = SPOTIFY_PACKAGE + ".playbackstatechanged"
        private val QUEUE_CHANGED = SPOTIFY_PACKAGE + ".queuechanged"

        fun intentFilter(): IntentFilter {
            val intentFilter = IntentFilter()
            intentFilter.addAction(METADATA_CHANGED)
            intentFilter.addAction(PLAYBACK_STATE_CHANGED)
            intentFilter.addAction(QUEUE_CHANGED)
            return intentFilter
        }
    }

    interface OnSpotifyEventListener {
        fun onMetadataChanged(track: Track)
        fun onPlaybackChanged(track: Track)
        fun onQueueChanged()
    }

    private val track = Track()
    var eventCallback: OnSpotifyEventListener? = null

    override fun onReceive(context: Context, intent: Intent) {
        when (intent.action) {
            METADATA_CHANGED -> {
                track.trackId = intent.getStringExtra("id")
                track.artistName = intent.getStringExtra("artist")
                track.albumName = intent.getStringExtra("album")
                track.trackName = intent.getStringExtra("track")
                track.trackLength = intent.getIntExtra("length", 0)
                eventCallback?.onMetadataChanged(track)
            }
            PLAYBACK_STATE_CHANGED -> {
                track.playing = intent.getBooleanExtra("playing", false)
                track.playbackPosition = intent.getIntExtra("playbackPosition", 0)
                eventCallback?.onPlaybackChanged(track)
            }
            QUEUE_CHANGED -> eventCallback?.onQueueChanged()
        }
    }

}