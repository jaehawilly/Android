package com.example.hw2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.StringTokenizer;

public class MyCalculator extends AppCompatActivity{



    public void onMyButtonClick(View view) {
        TextView textView=(TextView) findViewById(R.id.view1);

        switch (view.getId()) {
            case R.id.buttonWebView:{
                Intent intent=new Intent();
                ComponentName componentName=new ComponentName("com.example.hw2","com.example.hw2.MyWebBrowser");
                intent.setComponent(componentName);
                startActivity(intent);
                break;
            }
            case R.id.button0:{
                textView.setText(textView.getText() + "0"); break;
            }
            case R.id.button1:{
                textView.setText(textView.getText() + "1"); break;
            }
            case R.id.button2:{
                textView.setText(textView.getText() + "2"); break;
            }
            case R.id.button3:{
                textView.setText(textView.getText() + "3"); break;
            }
            case R.id.button4:{
                textView.setText(textView.getText() + "4"); break;
            }
            case R.id.button5:{
                textView.setText(textView.getText() + "5"); break;
            }
            case R.id.button6:{
                textView.setText(textView.getText() + "6"); break;
            }
            case R.id.button7:{
                textView.setText(textView.getText() + "7"); break;
            }
            case R.id.button8:{
                textView.setText(textView.getText() + "8"); break;
            }
            case R.id.button9:{
                textView.setText(textView.getText() + "9"); break;
            }
            case R.id.buttonPlus:{
                textView.setText(textView.getText() + "+"); break;
            }
            case R.id.buttonMinus:{
                textView.setText(textView.getText() + "-"); break;
            }
            case R.id.buttonMul:{
                textView.setText(textView.getText() + "X"); break;
            }
            case R.id.buttonDiv:{
                textView.setText(textView.getText() + "/"); break;
            }
            case R.id.buttonDot:{
                textView.setText(textView.getText() + "."); break;
            }
            case R.id.buttonEqual:{
                double result = calc((String) textView.getText());
                textView.setText(textView.getText() + "=" + result); break;
            }
            default: break;
        }
    }

    public static double calc(String str){
        int idx;
        idx = str.indexOf('+');

        if (idx != -1) {
            return calc(str.substring(0, idx)) + calc(str.substring(idx + 1));
        }
        else {
            idx = str.indexOf('-');
            if (idx != -1) {
                return calc(str.substring(0, idx)) - calc(str.substring(idx + 1));
            }
            else {
                idx = str.indexOf('*');
                if (idx != -1) {
                    return calc(str.substring(0, idx)) * calc(str.substring(idx + 1));
                }
                else {
                    idx = str.indexOf('/');
                    if (idx != -1) {
                        return calc(str.substring(0, idx)) / calc(str.substring(idx + 1));
                    }
                }
            }
        }

        String data = str.trim();
        if (data == null || data.isEmpty()) {
            return 0;
        }

        return Double.parseDouble(data);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_calculator);

        TextView textView=(TextView) findViewById(R.id.view1);
        Intent intent=getIntent();
        if(intent.hasExtra("operation")){
            String s=intent.getStringExtra("operation");
            textView.setText(s);
            Button button=(Button) findViewById(R.id.buttonEqual);
            onMyButtonClick(button);
        }
    }


}