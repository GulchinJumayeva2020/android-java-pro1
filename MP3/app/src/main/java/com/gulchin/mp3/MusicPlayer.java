package com.gulchin.mp3;

import android.media.MediaPlayer;

public class MusicPlayer {
    static MediaPlayer player;

    public static MediaPlayer getPlayer() {
        if (player == null)
            player = new MediaPlayer();
        return player;
    }

    public static int musicIndex = 0;
}
