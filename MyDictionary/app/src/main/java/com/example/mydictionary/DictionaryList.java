package com.example.mydictionary;

import static com.example.mydictionary.DataBase.dbName;
import static com.example.mydictionary.DataBase.dbTableName;
import static com.example.mydictionary.MainActivity.*;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import java.util.ArrayList;

public class DictionaryList extends AppCompatActivity {
    ListView dictionaryList;
    ListAdapter dictionaryAdapter;
    static ArrayList <String> aze, eng;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dictionary_list);
        dictionaryList = findViewById(R.id.dictionary_list);
        listWords();
        dictionaryAdapter = new ListAdapter(DictionaryList.this, aze, eng);
        if(dictionaryAdapter!=null)
        dictionaryList.setAdapter(dictionaryAdapter);
    }

    public void listWords(){
        db = openOrCreateDatabase(dbName, MODE_PRIVATE, null);
        cursor = db.rawQuery("select * from " + dbTableName, null);
        aze =new ArrayList<String>();
        eng =new ArrayList<String>();
        while (cursor.moveToNext()){
            eng.add( cursor.getString(1));
            aze.add(cursor.getString(2));
        }
        cursor.close();
        db.close();
    }
}