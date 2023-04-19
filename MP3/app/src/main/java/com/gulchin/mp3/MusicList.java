package com.gulchin.mp3;

import static com.gulchin.mp3.MainActivity.*;
import static com.gulchin.mp3.MusicPlayer.*;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import java.io.File;
import java.util.ArrayList;

public class MusicList extends AppCompatActivity {
    ListView list;
    public static String[] items;
    Intent intent;
    static Uri uri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_list);
        getSupportActionBar().setTitle("My music list");
        list = findViewById(R.id.list);
        if (ContextCompat.checkSelfPermission(MusicList.this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            displayMusic(myMusic);
        }

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                intent = new Intent();
                intent.putExtra("index", position);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }

    public void displayMusic(@NonNull ArrayList <File> myMusic){
        customAdapter customAdapter =new customAdapter();
        list.setAdapter(customAdapter);
    }

    @NonNull
    public static ArrayList <File> findMusic(@NonNull File file){
        ArrayList <File> arrayList = new ArrayList<>();
        File[] files = file.listFiles();
        if(files == null) return arrayList;
        for(File singleFile : files){
            if(singleFile.isDirectory() && !singleFile.isHidden()){
                arrayList.addAll(findMusic(singleFile));
            }
            else{
                if(singleFile.getName().endsWith(".mp3") || singleFile.getName().endsWith(".wav")){
                    arrayList.add(singleFile);
                }
            }
        }
        return arrayList;
    }

    public static void listMusicName(){
        items = new String[myMusic.size()];
        for (int i=0; i<myMusic.size(); i++){
            items[i] = myMusic.get(i).getName().replace(".mp3", "").replace(".wav", "");
        }
    }

    public static void player(Context context){
        if(player != null){
            if(player.isPlaying()) {
                player.stop();
                controllerCenter.setImageResource(R.drawable.play_button);
            }
        }
        uri = Uri.fromFile(myMusic.get(MusicPlayer.musicIndex));
        musicName.setText(items[MusicPlayer.musicIndex]);
        player = MediaPlayer.create(context, uri);
        player.start();
        controllerCenter.setImageResource(R.drawable.pause_button);
    }

    class customAdapter extends BaseAdapter{
        @Override
        public int getCount() {
            return items.length;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            View view1 = getLayoutInflater().inflate(R.layout.list_item, null);
            TextView txtMusic = view1.findViewById(R.id.txtMusic);
            txtMusic.setSelected(true);
            txtMusic.setText(items[i]);
            return view1;
        }
    }
}