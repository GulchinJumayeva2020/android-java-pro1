package com.example.mydictionary;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

class DataBase extends SQLiteOpenHelper {
    public static final String DROP_TABLE_IF_EXISTS = "DROP TABLE IF EXISTS ";
    private Context context;
    public static final String dbName = "MyDictionary";
    private static final int version = 1;
    public static final String dbTableName = "Dictionary";
    private static final String col1 = "id";
    public static final String col2 = "eng";
    public static final String col3 = "aze";


    public DataBase(Context context){
        super(context, dbName, null, version);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+ dbTableName +"("+ col1 +" INTEGER PRIMARY KEY AUTOINCREMENT, "+ col2 +" TEXT NOT NULL, "+ col3 +" TEXT NOT NULL);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL(DROP_TABLE_IF_EXISTS + dbTableName);
        onCreate(db);
    }

    void addDictionary(String vcol2, String vcol3){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(col2, vcol2);
        cv.put(col3, vcol3);
        long result = db.insert(dbTableName, null, cv);
        if (result == -1) Toast.makeText(context, R.string.failWarning, Toast.LENGTH_LONG).show();
        else Toast.makeText(context, R.string.successWarning, Toast.LENGTH_LONG).show();
    }
}
