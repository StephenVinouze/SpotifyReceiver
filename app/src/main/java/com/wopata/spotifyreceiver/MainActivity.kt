package com.wopata.spotifyreceiver

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import com.wopata.spotifyreceiver.models.Track
import com.wopata.spotifyreceiver.receivers.SpotifyBroadcastReceiver
import com.wopata.spotifyreceiver.views.PlayerBannerView

class MainActivity : AppCompatActivity(), SpotifyBroadcastReceiver.OnSpotifyEventListener {

    @BindView(R.id.player_banner)
    lateinit var playerBanner: PlayerBannerView

    private var track: Track? = null
    private val spotifyBroadcastReceiver = SpotifyBroadcastReceiver()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ButterKnife.bind(this)

        spotifyBroadcastReceiver.eventCallback = this
    }

    override fun onResume() {
        super.onResume()
        registerReceiver(spotifyBroadcastReceiver, SpotifyBroadcastReceiver.intentFilter())
    }

    override fun onPause() {
        unregisterReceiver(spotifyBroadcastReceiver)
        super.onPause()
    }

    @OnClick(R.id.player_banner)
    fun onRedirectToTrackClick() {
        val trackId = track?.trackId?.split(":")?.get(2)
        if (trackId != null) {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("http://open.spotify.com/track/$trackId")))
        }
    }

    override fun onMetadataChanged(track: Track) {
        this.track = track
        playerBanner.bind(track)
    }

    override fun onPlaybackChanged(track: Track) {
        playerBanner.updatePlaybackStatus(track)
    }

    override fun onQueueChanged() {

    }

}