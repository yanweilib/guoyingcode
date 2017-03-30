package com.yw.gril.Units.SqlLite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Exchanger;

/**
 * Created by YSB on 2017/1/16.
 */

public class DBManager {
    private DBHelper dbHelper;
    private SQLiteDatabase db;

    public DBManager(Context context)
    {
        dbHelper=new DBHelper(context);
        db=dbHelper.getWritableDatabase();
    }

    public void add(List<MsgModel> msgModels)
    {
        try {
            db.beginTransaction();//开始事务
            for(MsgModel msgModel:msgModels)
            {
                db.execSQL("INSERT INTO msg VALUES(NULL,?,?,?",new Object[]{msgModel.getM_id(),msgModel.getTitle(),msgModel,msgModel.getContent(),msgModel.getType()});
            }
            db.setTransactionSuccessful();//设置事务成功完成
        }
        finally {
            db.endTransaction();
        }
    }

    public void update(MsgModel msgModel)
    {
        ContentValues contentValues=new ContentValues();
        contentValues.put("title",msgModel.getTitle());
        contentValues.put("Content",msgModel.getContent());
        contentValues.put("type",msgModel.getType());
        db.update("msg",contentValues,"m_id=?",new String[]{msgModel.getM_id()+""});
    }

    public void delete(MsgModel msgModel)
    {
        db.delete("msg","m_id>=?",new String[]{msgModel.getM_id()+""});
    }

    /**
     * 查询
     * @return
     */
    public List<MsgModel> query()
    {
        List<MsgModel> msgModels=new ArrayList<>();
        Cursor cursor=queryTheCursor();
        while (cursor.moveToNext())
        {
            MsgModel msgModel=new MsgModel();
            msgModel.setId(cursor.getInt(cursor.getColumnIndex("id")));
            msgModel.setM_id(cursor.getInt(cursor.getColumnIndex("m_id")));
            msgModel.setTitle(cursor.getString(cursor.getColumnIndex("title")));
            msgModel.setContent(cursor.getString(cursor.getColumnIndex("content")));
            msgModel.setType(cursor.getString(cursor.getColumnIndex("type")));
            msgModel.setCreatetime(cursor.getLong(cursor.getColumnIndex("createtime")));
            msgModel.setUpLongtime(cursor.getLong(cursor.getColumnIndex("updatetime")));
        }
        cursor.close();
        return msgModels;
    }

    public Cursor queryTheCursor()
    {
        Cursor cursor=db.rawQuery("SELECT * FROM msg",null);
        return cursor;
    }

    public void closeDB()
    {
        db.close();
    }
}
