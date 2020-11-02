package com.example.afinal;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.content.SharedPreferences;
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
    String link="",code="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void exit0(View view){
        Intent intent = new Intent();
        intent.setClass(MainActivity.this,Menu.class);
        startActivity(intent);
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
        if(!link.equals("false")&&!link.equals("")) {
            Intent intent = new Intent();
            intent.setAction("android.intent.action.VIEW");
            Uri content_url = Uri.parse(link);
            intent.setData(content_url);
            startActivity(intent);
        }
        else {
            Toast.makeText(getApplicationContext(), "链接获取失败", Toast.LENGTH_SHORT).show();
        }
    }

    public void copy1(View view){
        if(!link.equals("false")&&!link.equals("")){
            ClipboardManager myClipboard;
            myClipboard = (ClipboardManager)getSystemService(CLIPBOARD_SERVICE);
            ClipData myClip;
            myClip = ClipData.newPlainText("text", link);
            myClipboard.setPrimaryClip(myClip);
            Toast.makeText(getApplicationContext(), "链接复制成功", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(getApplicationContext(), "链接获取失败", Toast.LENGTH_SHORT).show();
        }
    }

    public void copy2(View view){
        if(!code.equals("false")&&!code.equals("")){
            ClipboardManager myClipboard;
            myClipboard = (ClipboardManager)getSystemService(CLIPBOARD_SERVICE);
            ClipData myClip;
            myClip = ClipData.newPlainText("text", code);
            myClipboard.setPrimaryClip(myClip);
            Toast.makeText(getApplicationContext(), "提取码复制成功", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(getApplicationContext(), "提取码获取失败", Toast.LENGTH_SHORT).show();
        }
    }

    public void adding(View view){
        if(!code.equals("false")&&!code.equals("")){
            RecordItem ri = new RecordItem(link , code);
            int tag = 0;
            DBManager dbManager = new DBManager(MainActivity.this);
            for(RecordItem reItem : dbManager.listAll()){
                if(link.equals(reItem.getLink())){
                    Toast.makeText(getApplicationContext(), "记录已存在", Toast.LENGTH_SHORT).show();
                    tag++;
                }
            }
            if(tag==0){
                dbManager.add(ri);
                Toast.makeText(getApplicationContext(), "添加成功", Toast.LENGTH_SHORT).show();
            }

        }
        else {
            Toast.makeText(getApplicationContext(), "添加失败", Toast.LENGTH_SHORT).show();
        }
    }

    public void deleting(View view){
        DBManager dbManager = new DBManager(MainActivity.this);
        dbManager.deleteAll();
        Toast.makeText(getApplicationContext(), "删除完成", Toast.LENGTH_SHORT).show();
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