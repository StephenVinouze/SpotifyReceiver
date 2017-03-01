package com.wopata.spotifyreceiver

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import butterknife.BindView
import butterknife.ButterKnife
import com.wopata.spotifyreceiver.models.Track
import com.wopata.spotifyreceiver.receivers.SpotifyBroadcastReceiver
import com.wopata.spotifyreceiver.views.PlayerBannerView

class MainActivity : AppCompatActivity(), SpotifyBroadcastReceiver.OnSpotifyEventListener {

    @BindView(R.id.player_banner)
    lateinit var playerBanner: PlayerBannerView

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

    override fun onMetadataChanged(track: Track) {
        playerBanner.bind(track)
    }

    override fun onPlaybackChanged(track: Track) {
        playerBanner.updatePlaybackStatus(track)
    }

    override fun onQueueChanged() {

    }

}