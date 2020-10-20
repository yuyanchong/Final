package com.example.afinal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    TextView inp;
    String link,code;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void getdata(View view){
        inp = (EditText)findViewById(R.id.editTextTextPersonName);
        if(inp.getText().toString()==null) {
            Toast.makeText(this, "please paste the context", Toast.LENGTH_SHORT).show();
        }
        String temp = inp.getText().toString();


    }


}