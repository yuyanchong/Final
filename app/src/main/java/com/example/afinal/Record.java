package com.example.afinal;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ListActivity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Record extends AppCompatActivity implements AdapterView.OnItemClickListener {
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);
        List<String> reList = new ArrayList<String>();
        DBManager dbManager = new DBManager(Record.this);
        for(RecordItem reItem : dbManager.listAll()){
            reList.add("链接:"+ reItem.getLink() + "提取码:" + reItem.getCode()+"--");
        }
        listView = (ListView) findViewById(R.id.mylist);
        ListAdapter adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, reList);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);

    }

    public void exit(View view){
        Intent intent = new Intent();
        intent.setClass(Record.this,Menu.class);
        startActivity(intent);
    }

    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String temp = (String) listView.getItemAtPosition(position);
        String link = getLink(temp);
        String code = getCode(temp);
        ClipboardManager myClipboard;
        myClipboard = (ClipboardManager)getSystemService(CLIPBOARD_SERVICE);
        ClipData myClip;
        myClip = ClipData.newPlainText("text", code);
        myClipboard.setPrimaryClip(myClip);
        Intent intent = new Intent();
        intent.setAction("android.intent.action.VIEW");
        Uri content_url = Uri.parse(link);
        intent.setData(content_url);
        startActivity(intent);

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