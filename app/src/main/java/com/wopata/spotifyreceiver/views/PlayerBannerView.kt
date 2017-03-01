package com.wopata.spotifyreceiver.views

import android.content.Context
import android.support.v4.content.ContextCompat
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import com.wopata.spotifyreceiver.R
import com.wopata.spotifyreceiver.models.Track

/**
 * Created by stephenvinouze on 23/02/2017.
 */
class PlayerBannerView : RelativeLayout {

    @BindView(R.id.player_banner_source_icon)
    lateinit var sourceImageView: ImageView

    @BindView(R.id.player_banner_status_icon)
    lateinit var statusImageView: ImageView

    @BindView(R.id.player_banner_track_name)
    lateinit var trackView: TextView

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    init {
        LayoutInflater.from(context).inflate(R.layout.view_player_banner, this, true)
        ButterKnife.bind(this, this)

        val padding = resources.getDimensionPixelSize(R.dimen.standard_margin)
        setPadding(padding, padding, padding, padding)
        setBackgroundColor(ContextCompat.getColor(context, R.color.colorDark))
    }

    fun bind(track: Track) {
        val text = "${track.trackName} • ${track.artistName}"
        val spannable = SpannableString(text)
        spannable.setSpan(ForegroundColorSpan(ContextCompat.getColor(context, R.color.colorWhiteAlpha)), "${track.trackName}".length, text.length, 0)
        trackView.text = spannable
    }

    fun updatePlaybackStatus(track: Track) {
        statusImageView.setImageResource(if (track.playing == true) R.drawable.ico_pause else R.drawable.ico_play)
    }

}