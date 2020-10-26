package com.example.afinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.net.Uri;
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
        String temp = inp.getText().toString();
        if (temp.equals("")) {
            Toast.makeText(getApplicationContext(), "请粘贴文本", Toast.LENGTH_SHORT).show();
        }else {
            link = getLink(temp);
            code = getCode(temp);
            tv1= findViewById(R.id.textView);
            tv1.setText(link);
            tv2= findViewById(R.id.textView2);
            tv2.setText(code);
        }

    }

    public void enter(View view){
        tv1= findViewById(R.id.textView);
        String myurl = tv1.getText().toString();
        if(!myurl.equals("false")&&!myurl.equals("")) {
            Intent intent = new Intent();
            intent.setAction("android.intent.action.VIEW");
            Uri content_url = Uri.parse(myurl);
            intent.setData(content_url);
            startActivity(intent);
        }
        else {
            Toast.makeText(getApplicationContext(), "链接获取失败", Toast.LENGTH_SHORT).show();
        }
    }

    public void copy1(View view){
        tv1= findViewById(R.id.textView);
        String text = tv1.getText().toString();
        if(!text.equals("false")&&!text.equals("")){
            ClipboardManager myClipboard;
            myClipboard = (ClipboardManager)getSystemService(CLIPBOARD_SERVICE);
            ClipData myClip;
            myClip = ClipData.newPlainText("text", text);
            myClipboard.setPrimaryClip(myClip);
        }
        else {
            Toast.makeText(getApplicationContext(), "链接获取失败", Toast.LENGTH_SHORT).show();
        }
    }

    public void copy2(View view){
        tv2= findViewById(R.id.textView2);
        String text = tv2.getText().toString();
        if(!text.equals("false")&&!text.equals("")){
            ClipboardManager myClipboard;
            myClipboard = (ClipboardManager)getSystemService(CLIPBOARD_SERVICE);
            ClipData myClip;
            myClip = ClipData.newPlainText("text", text);
            myClipboard.setPrimaryClip(myClip);
        }
        else {
            Toast.makeText(getApplicationContext(), "提取码获取失败", Toast.LENGTH_SHORT).show();
        }
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
        }else {
            str = "false";
        }
        return str;
    }


}