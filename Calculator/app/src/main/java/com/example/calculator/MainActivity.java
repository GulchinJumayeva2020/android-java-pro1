package com.example.calculator;
//https://dribbble.com/shots/6902536--004-Calculator     style

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Objects;


public class MainActivity extends AppCompatActivity {
    TextView text1;
    TextView text2;
    String buttonText;
    String number;
    String[] numArr;
    String all;
    int sign;
    String[] signArr;
    Button button;
    ImageButton del;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //GELECEKDE BAR ELAVE EDENDE
//        ActionBar bar=this.getSupportActionBar();
//        bar.setDisplayHomeAsUpEnabled(true);
//        bar.setTitle(" ");
        numArr = new String[20];
        signArr = new String[19];
        text1 = findViewById(R.id.action);
        text2 = findViewById(R.id.action_result);
        del=findViewById(R.id.delet);
        sign=1;
        ac();

        del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!all.equals("")){
                    all=all.substring( 0, all.length()-1);
                }
                if (sign==1){
                    for (int i=1; i<19; i++){
                        if (signArr[i]==""){
                            signArr[i-1]="";
                        }
                    }
                    sign=0;
                }
                if(sign == 0 && !number.equals("")){
                    number=number.substring( 0, number.length()-1);
                    if(number.length()==0)
                        sign=1;
                    else
                        sign=0;
                }
                text2.setText(all);
            }
        });
    }

    public void buttonsClick(View view) {

        if (!numArr[19].equals("") || !signArr[18].equals("")){
            Toast.makeText(this, "ERROR", Toast.LENGTH_SHORT).show();
            ac();
        }

        button= (Button) view;
        buttonText= button.getText().toString();

        if(buttonText.equals( "0") || (buttonText.equals( ".") && !number.equals("") ) || buttonText.equals( "1") || buttonText.equals( "2") || buttonText.equals( "3") || buttonText.equals( "4") || buttonText.equals( "5") ||
            buttonText.equals( "6") || buttonText.equals( "7") || buttonText.equals( "8") || buttonText.equals( "9") || (buttonText.equals( "-") && sign==1)){

            sign=0;
            number+=buttonText;
            all+=buttonText;
            number=number.replace("--","-");
            all=all.replace("--", "-");

        }
        else if((buttonText.equals( "+") || buttonText.equals( "-") || buttonText.equals( "/") || buttonText.equals( "x") || buttonText.equals( "%") || buttonText.equals( "=")) &&sign==0){
            int index=-1;
            for (int i=0; i<20; i++){
                if(numArr[i].equals( "")){
                    index=-1;
                    numArr[i]=number;
                    number="";
                    break;
                }
            }
            if(!number.equals( "")){
//                error
                text1.setText("");
                Toast.makeText(this, "ERROR", Toast.LENGTH_SHORT).show();
                ac();
            }
            if(sign==0 && !buttonText.equals( "=")){
                for(int i=0; i<19; i++){
                    if(signArr[i].equals("")){
                        index=i;
                        signArr[i]=buttonText;
                        all+=buttonText;
                        buttonText="";
                        sign=1;
                        break;
                    }
                }
                if(!buttonText.equals("")){
//                error
                    text1.setText("");
                    Toast.makeText(this, "ERROR", Toast.LENGTH_SHORT).show();
                    ac();
                }
            }
            else if(!buttonText.equals( "-")&& sign==1) {
                signArr[index]=buttonText;
                all=all.substring( 0, all.length()-1);
                all+=buttonText;
            }

        }
        else if(buttonText.equals( "AC")){
            ac();
        }
        if(buttonText.equals( "=")){
            if(findIndex(numArr)-1!=findIndex(signArr)){
                ac();
                Toast.makeText(this, "ERROR", Toast.LENGTH_SHORT).show();
            }
            while (!empt(signArr)){

                for (int i = 0; i<19; i++){
                    if (signArr[i].equals("x")) {
                        numArr[i] = Double.toString((change(numArr[i]) * change(numArr[i + 1])));
                        removeItemNumArr(numArr, i + 1);
                        removeItemNumArr(signArr, i);
                        sign = 0;
                        i--;
                    } else if (signArr[i].equals("/")) {
                        if (change(numArr[i + 1]) == 0.0){
                            Toast.makeText(this, "ERROR", Toast.LENGTH_SHORT).show();
                            ac();
                            break;
                        }
                        numArr[i] = Double.toString((change(numArr[i]) / change(numArr[i + 1])));
                        removeItemNumArr(numArr, i + 1);
                        removeItemNumArr(signArr, i);
                        sign = 0;
                        i--;
                    } else if (signArr[i].equals("%")) {
                        if (change(numArr[i + 1]) == 0.0){
                            Toast.makeText(this, "ERROR", Toast.LENGTH_SHORT).show();
                            ac();
                            break;
                        }
                        numArr[i] = Double.toString((change(numArr[i]) % change(numArr[i + 1])));
                        removeItemNumArr(numArr, i + 1);
                        removeItemNumArr(signArr, i);
                        sign = 0;
                        i--;
                    }
                }

                for (int i = 0; i<19; i++){
                    if (signArr[i].equals("-")) {
                        numArr[i] = Double.toString((change(numArr[i]) - change(numArr[i + 1])));
                        removeItemNumArr(numArr, i + 1);
                        removeItemNumArr(signArr, i);
                        sign = 0;
                        i--;
                    } else if (signArr[i].equals("+")) {
                        numArr[i] = Double.toString((change(numArr[i]) + change(numArr[i + 1])));
                        removeItemNumArr(numArr, i + 1);
                        removeItemNumArr(signArr, i);
                        sign = 0;
                        i--;
                    }
                }

            }
            text1.setText(all);
            if(change(numArr[0])%1==0)
               text2.setText(String.valueOf((int)change(numArr[0])/1));
            else
                text2.setText(numArr[0]);
        }else{
            text2.setText(all);
        }


    }

    public boolean empt( String[] signArr){
        for(int i=0; i<19; i++){
            if(!signArr[i].equals(""))
                return false;
        }
        return true;
    }

    public void removeItemNumArr(String[] arr, int index){
        for (int i=index; i< arr.length-1; i++){
            arr[i]=arr[i+1];
            arr[i+1]="";

        }
    }

    public int findIndex(String[] arr){
        for(int i=0; i<arr.length; i++){
            if(arr[i].equals("")){
                return i-1;
            }
        }
        return 0;
    }

    public double change(String num){
        return Double.parseDouble(num);
    }

    public void ac(){
        all="";
        number="";
        sign=1;
        buttonText="";
        for(int i=0; i<19; i++){
            signArr[i]="";
        }
        text1.setText("");
        text2.setText("0");
        for (int i=0; i<20; i++){
            numArr[i]="";
        }
    }




    /*
BAR ELAVE EDENDE MENU DA LAZIM OLSA
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_calc,menu);
        return super.onCreateOptionsMenu(menu);
    }
*/
}