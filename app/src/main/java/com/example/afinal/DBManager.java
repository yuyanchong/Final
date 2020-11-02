package com.example.afinal;
import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DBManager {
    private DBHelper dbHelper;
    private String TBNAME;

    public DBManager(Context context) {
        dbHelper = new DBHelper(context);
        TBNAME = DBHelper.TB_NAME;
    }

    public void add(RecordItem item){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("link", item.getLink());
        values.put("code", item.getCode());
        db.insert(TBNAME, null, values);
        db.close();
    }

    public void deleteAll(){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete(TBNAME,null,null);
        db.close();
    }

    public List<RecordItem> listAll(){
        List<RecordItem> reList = null;
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query(TBNAME, null, null, null, null, null, null);
        if(cursor!=null){
            reList = new ArrayList<RecordItem>();
            while(cursor.moveToNext()){
                RecordItem item = new RecordItem();
                item.setId(cursor.getInt(cursor.getColumnIndex("ID")));
                item.setLink(cursor.getString(cursor.getColumnIndex("LINK")));
                item.setCode(cursor.getString(cursor.getColumnIndex("CODE")));
                reList.add(item);
            }
            cursor.close();
        }
        db.close();
        return reList;

    }
}
