package com.gulchin.mp3;

import static com.gulchin.mp3.MusicList.*;
import static com.gulchin.mp3.MusicPlayer.musicIndex;
import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import androidx.annotation.NonNull;

public class ButtonClicks {
    static void controllerLeft(@NonNull ImageView button, Context context){
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                musicIndex--;
                if (musicIndex < 0){
                    musicIndex = musicIndex + items.length;
                }
                player(context);
            }
        });
    }
    static void controllerCenter(ImageView button){
        button.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("UseCompatLoadingForDrawables")
            @Override
            public void onClick(View v) {
                if(MusicPlayer.player.isPlaying()){
                    MusicPlayer.player.pause();
                    button.setImageResource(R.drawable.play_button);
                }
                else if(!MusicPlayer.player.isPlaying()){
                    MusicPlayer.player.start();
                    button.setImageResource(R.drawable.pause_button);
                }
            }
        });
    }
    static void controllerRight(@NonNull ImageView button, Context context){
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                musicIndex++;
                if (musicIndex > items.length){
                    musicIndex = musicIndex - items.length;
                }
                player(context);
            }
        });
    }
}
