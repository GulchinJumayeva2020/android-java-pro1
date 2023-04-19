package com.example.mydictionary;

import static com.example.mydictionary.DataBase.*;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Intent intent;
    EditText searchText;
    TextView searchResult;
    Button searchBtn;
    String searchTextStr, dbEng, dbAze, resultText;
    static SQLiteDatabase db;
    static Cursor cursor;
    boolean check;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        searchText = findViewById(R.id.search_input);
        searchResult = findViewById(R.id.search_result);
        searchBtn = findViewById(R.id.search_btn);

        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resultText = "";
                searchTextStr = searchText.getText().toString().trim().toLowerCase();
                if(searchTextStr.isEmpty()) resultText = getString(R.string.emptyWarning);
                else if(MainActivity.this.getDatabasePath(dbName).exists()){
                    check  = true;
                    db = openOrCreateDatabase(dbName, MODE_PRIVATE, null);
                    cursor = db.rawQuery("select * from " + dbTableName, null);
                    while (cursor.moveToNext()){
                        dbEng = cursor.getString(1).toLowerCase();
                        dbAze = cursor.getString(2).toLowerCase();
                        if (searchTextStr.equals(dbEng)){
                            check = false;
                            resultText += dbAze + ", ";
                        }
                        else if (searchTextStr.equals(dbAze)){
                            check = false;
                            resultText += dbEng + ", ";
                        }
                    }
                    cursor.close();
                    db.close();
                    if(resultText.charAt(resultText.length() - 2) == ',') resultText = resultText.substring(0,resultText.length()-2);
                }
                else{
                    resultText = getString(R.string.dictionaryEmpty);
                }
                if(check) resultText = getString(R.string.notFound);
                searchResult.setText(resultText);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.add:
                intent = new Intent(MainActivity.this, Add.class);
                break;
            case R.id.dictionary_list:
                intent = new Intent(MainActivity.this, DictionaryList.class);
                break;
        }
        startActivity(intent);
        return super.onOptionsItemSelected(item);
    }
}