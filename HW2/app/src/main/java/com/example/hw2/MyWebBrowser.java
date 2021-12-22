package com.example.hw2;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;

public class MyWebBrowser extends AppCompatActivity {

    public void onButtonClick(View view) {
        WebView webView=(WebView) findViewById(R.id.webView);
        EditText editText=(EditText) findViewById(R.id.editText);

        switch (view.getId()) {
            case R.id.buttonCalculator:{
                Intent intent=new Intent();
                ComponentName componentName=new ComponentName("com.example.hw2","com.example.hw2.MyCalculator");
                intent.setComponent(componentName);
                startActivity(intent);
                break;
            }
            case R.id.buttonBackpage:{
                webView.goBack();
                break;
            }
            case R.id.buttonFrontpage:{
                webView.goForward();
                break;
            }
            default: break;
        }
    }

    final int MY_PERMISSION_INTERNET=1;
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_web_browser);

        if(checkSelfPermission(Manifest.permission.INTERNET) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.INTERNET}, MY_PERMISSION_INTERNET);
        }

        EditText editText=(EditText) findViewById(R.id.editText);
        WebView webView=(WebView) findViewById(R.id.webView);

        Intent intent=getIntent();
        String s=intent.getStringExtra("url");
        editText.setText(s);
        webView.loadUrl(s);

        webView.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageFinished(WebView view, String url) {
                editText.setText(webView.getUrl());
            }
        });
        WebSettings webSettings=webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        editText.setOnKeyListener(new View.OnKeyListener() {

            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                switch (i) {
                    case KeyEvent.KEYCODE_ENTER:
                        webView.loadUrl(String.valueOf(editText.getText()));
                        break;
                }
                return false;
            }
        });
    }
}