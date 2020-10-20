package com.example.afinal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class MainActivity extends AppCompatActivity {
    EditText inp;
    TextView tv1,tv2;
    String link,code;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void getdata(View view){
        inp = findViewById(R.id.editTextTextPersonName);
        if(inp.getText().toString()==null) {
            Toast.makeText(this, "please paste the context", Toast.LENGTH_SHORT).show();
        }

        String temp = inp.getText().toString();
        link = getLink(temp);
        code = getCode(temp);
        if(link==null||code==null){
            Toast.makeText(this, "格式不正确", Toast.LENGTH_SHORT).show();
        }
        tv1= findViewById(R.id.textView);
        tv1.setText(link);
        tv2= findViewById(R.id.textView2);
        tv2.setText(code);

    }

    public static String getLink(String text) {
        Pattern pattern = Pattern.compile("链接:(.*)提取码");
        Matcher m = pattern.matcher(text);
        String str = "";
        if (m.find()) {
            str = m.group(1);
        }
        else {
            str = "false";
        }
        return str;
    }

    public static String getCode(String text) {
        Pattern pattern = Pattern.compile("提取码:(.*)--");
        Matcher m = pattern.matcher(text);
        String str = "";
        if (m.find()) {
            str = m.group(1);
        }
        return str;
    }


}