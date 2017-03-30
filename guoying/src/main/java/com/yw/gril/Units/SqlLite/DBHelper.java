package com.yw.gril.Units.SqlLite;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by YSB on 2017/1/16.
 */

public class DBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME="jiaxiaotong.db";//数据库名
    private static final int DATABASE_VERSION=1;//数据库版本

    public static final String TABLE_MSG="msg";//表名：消息

    public DBHelper(Context context)
    {
        super(context, DATABASE_NAME,null, DATABASE_VERSION);
    }

//    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
//        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
//    }
//
//    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
//        super(context, DATABASE_NAME, factory, DATABASE_VERSION, errorHandler);
//    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(getMsg());

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    /**
     * 创建消息数据库
     */
    private String getMsg()
    {
        StringBuilder result=new StringBuilder();
        result.append("CREATE TABLE IF NOT EXISTS ");
        result.append(TABLE_MSG);
        result.append(" (");
        result.append("_id INTEGER PRIMARY KEY AUTOINCREMENT ");
        result.append(",");
        result.append("m_id VARCHAR ");
        result.append(",");
        result.append("title VARCHAR ");
        result.append(",");
        result.append("TEXT VARCHAR");
        result.append(",");
        result.append("type VARCHAR ");

        result.append(")");
        return result.toString();
    }
}
