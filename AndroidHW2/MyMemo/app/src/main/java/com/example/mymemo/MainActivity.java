package com.example.mymemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EditText editText=(EditText) findViewById(R.id.editText);
        editText.setCustomSelectionActionModeCallback(new ActionMode.Callback() {
            @Override
            public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
                return true;
            }

            @Override
            public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
                return false;
            }

            @Override
            public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
                int start=editText.getSelectionStart();
                int end=editText.getSelectionEnd();
                String str=editText.getText().toString();
                str.substring(start,end);
                switch (menuItem.getItemId()){
                    case android.R.id.shareText:
                        Intent intent=new Intent();
                        if((editText.getText()).toString().contains("http")){
                            intent.setData(Uri.parse(str));
                            intent.setAction("android.intent.action.VIEW");
                            intent.addCategory("android.intent.category.BROWSABLE");
                            intent.putExtra("url", str);
                        }
                        else{
                            intent.setAction("android.intent.action.MAIN");
                            intent.addCategory("android.intent.category.APP_CALCULATOR");
                            intent.putExtra("operation", str);
                        }
                        startActivity(intent);
                        break;
                }
                return true;
            }

            @Override
            public void onDestroyActionMode(ActionMode actionMode) {

            }
        });
    }
}