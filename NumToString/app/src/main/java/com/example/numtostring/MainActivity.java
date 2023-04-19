package com.example.numtostring;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Locale;


public class MainActivity extends AppCompatActivity {
    TextView number;
    Button btn;
    TextView result;
    String str ;
    int[] parts={-1,-1,-1};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        number = findViewById(R.id.number);
        btn = findViewById(R.id.change);
        result = findViewById(R.id.result);
        str = new String();
    }
    public void change (View v){
        str="";
        int num = Integer.parseInt(number.getText().toString());
        if (num==0){
            str="sfır";
            result.setText(str);
            System.exit(0);
        }else if (num<0) {
            str = str + "mənfi ";
            num = -1 * num;
        }


        parts[0]=num/1000000;
        parts[1]=num%1000000/1000;
        parts[2]=num%1000;

        for (int i=0; i<3;i++){
            if(parts[i]!=0){
                if(i==0) {
                    partOfNumber(parts[i]);
                    str = str + "milyon ";
                }
                else if(i==1){
                    partOfNumber(parts[i]);
                    str = str + "min ";
                }
                else
                partOfNumber(parts[i]);
            }
        }

        result.setText(str.substring(0,1).toUpperCase()+str.substring(1));
    }

    public void digit(int j){
        switch (j){
            case 0:
                break;
            case 1:
                str=str+"bir ";
                break;
            case 2:
                str=str+"iki ";
                break;
            case 3:
                str=str+"üç ";
                break;
            case 4:
                str=str+"dörd ";
                break;
            case 5:
                str=str+"beş ";
                break;
            case 6:
                str=str+"altı ";
                break;
            case 7:
                str=str+"yeddi ";
                break;
            case 8:
                str=str+"səkkiz ";
                break;
            case 9:
                str=str+"doqquz ";
                break;
        }
    }
    public void partOfNumber(int part){

            if(part/100!=1){
                digit(part/100);

            }
            if(part/100!=0)
                str=str+"yüz ";
            switch (part%100/10){
                case 0:
                    break;
                case 1:
                    str=str+"on ";
                    break;
                case 2:
                    str=str+"iyirmi ";
                    break;
                case 3:
                    str=str+"otuz ";
                    break;
                case 4:
                    str=str+"qırx ";
                    break;
                case 5:
                    str=str+"əlli ";
                    break;
                case 6:
                    str=str+"altmış ";
                    break;
                case 7:
                    str=str+"yetmiş ";
                    break;
                case 8:
                    str=str+"səksən ";
                    break;
                case 9:
                    str=str+"doxsan ";
                    break;
            }
            digit(part%10);
    }
}