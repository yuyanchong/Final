package com.example.afinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }

    public void openMain(View view){
        Intent intent = new Intent();
        intent.setClass(Menu.this,MainActivity.class);
        startActivity(intent);
    }

    public void openRecord(View view){
        Intent intent = new Intent();
        intent.setClass(Menu.this,Record.class);
        startActivity(intent);
    }


}