package com.example.mydictionary;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import androidx.annotation.NonNull;
import java.util.ArrayList;

public class ListAdapter extends ArrayAdapter <String> {
    private Activity context;
    private ArrayList <String> aze, eng;

    public ListAdapter(@NonNull Activity context, ArrayList <String> aze, ArrayList <String> eng) {
        super(context, R.layout.list_style, aze);
        this.context = context;
        this.aze = aze;
        this.eng = eng;
    }

    @NonNull
    @Override
    public View getView(int index, View row, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View view = inflater.inflate(R.layout.list_style, null, true);

        TextView textAze = view.findViewById(R.id.dictionary_text_aze);
        TextView textEng = view.findViewById(R.id.dictionary_text_eng);

        textAze.setText(aze.get(index));
        textEng.setText(eng.get(index));
        return view;
    }
}
