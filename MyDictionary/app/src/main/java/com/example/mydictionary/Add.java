package com.example.mydictionary;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class Add extends AppCompatActivity {
    EditText eng, aze;
    Button add_btn;
    String engText, azeText;
    DataBase dictionary;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        getSupportActionBar().setTitle("Add");
        eng = findViewById(R.id.add_eng);
        aze = findViewById(R.id.add_aze);
        add_btn = findViewById(R.id.add_btn);
        add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                engText = eng.getText().toString().trim();
                azeText = aze.getText().toString().trim();
                if(engText.isEmpty() || azeText.isEmpty())
                    Toast.makeText(Add.this, R.string.emptyWarning, Toast.LENGTH_SHORT).show();
                else{
                    dictionary =new DataBase(Add.this);
                    dictionary.addDictionary(engText, azeText);
                    eng.setText("");
                    aze.setText("");
                }
            }
        });
    }
}