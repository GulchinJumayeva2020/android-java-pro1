package com.gulchin.mp3;

import static com.gulchin.mp3.ButtonClicks.*;
import static com.gulchin.mp3.MusicList.*;
import static com.gulchin.mp3.MusicPlayer.*;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import java.io.File;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private final int EXTERNAL_STORAGE_PERMISSION_CODE = 23;
    private static final int REQ_CODE = 548;
    public static ArrayList<File> myMusic;
    static ImageView musicList, img, controllerLeft, controllerCenter, controllerRight;
    static TextView musicName;
    ActionBar bar;
    Intent intent;
    int count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        musicList = findViewById(R.id.music_list);
        img = findViewById(R.id.img);
        controllerLeft = findViewById(R.id.controller_left);
        controllerCenter = findViewById(R.id.controller_center);
        controllerRight = findViewById(R.id.controller_right);
        musicName = findViewById(R.id.music_name);
        bar = getSupportActionBar();
        if (bar != null) {
            bar.hide();
        }
        ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, EXTERNAL_STORAGE_PERMISSION_CODE);

        if (savedInstanceState != null) {
            count = savedInstanceState.getInt("count");
        }

        if(count == 0){
            Toast.makeText(MainActivity.this, "List is creating!", Toast.LENGTH_LONG).show();
            myMusic = findMusic(Environment.getExternalStorageDirectory());
            listMusicName();
        }

        player(MainActivity.this);

        musicList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(MainActivity.this, MusicList.class);
                startActivityForResult(intent, REQ_CODE);
            }
        });

        controllerCenter(controllerCenter);

        controllerLeft(controllerLeft, MainActivity.this);

        controllerRight(controllerRight, MainActivity.this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==REQ_CODE && resultCode==RESULT_OK){
            musicIndex = data.getIntExtra("index", 0);
            controllerCenter.setImageResource(R.drawable.pause_button);
            player(MainActivity.this);
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putInt("count", 1);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        count = savedInstanceState.getInt("count");
        Toast.makeText(getApplicationContext(), count ,Toast.LENGTH_SHORT).show();
        super.onRestoreInstanceState(savedInstanceState);
    }
}