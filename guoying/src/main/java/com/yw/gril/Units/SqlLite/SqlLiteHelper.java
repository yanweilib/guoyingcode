package com.example.androidcode.Units.SqlLite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * Created by 伟 on 2016/5/26.
 */
public class SqlLiteHelper extends SQLiteOpenHelper {
    public static final String CREATE_BOOK="create table Book ("
            +"id integer primary key autoincrement,"
            +"author text"
            +"price real"
            +"pages integer"
            +"name text"
            +"category_id integer";//新添加一列
    public static final String CREATE_Category="create table Category ("
            +"id integer primary key autoincrement,"
            +"category_name text"
            +"category_code real";
    private Context context;

    public SqlLiteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        this.context=context;
    }

    /**
     * 创建数据库
     * @param db
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_BOOK);
        Toast.makeText(context,"Create succeeded", Toast.LENGTH_LONG).show();
    }

    /**
     * 升级数据库
     * @param db
     * @param oldVersion
     * @param newVersion
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        switch (oldVersion)
        {
            case 1:
                db.execSQL(CREATE_Category);
                break;
            case 2:
                db.execSQL("alter table Book add column category_id integer");
            default:
        }
    }

    /**
     * 创建数据库
     */
    private void chaungjian()
    {
        //创建数据库
        SqlLiteHelper sqlLiteHelper=new SqlLiteHelper(context,"BookStore.db",null,1);
        SQLiteDatabase sqLiteDatabase= sqlLiteHelper.getWritableDatabase();
    }

    /**
     * 升级数据库
     */
    private void shengji()
    {
        //仅仅需要把数据库版本提高
        SqlLiteHelper sqlLiteHelper=new SqlLiteHelper(context,"BookStore.db",null,2);
        SQLiteDatabase sqLiteDatabase= sqlLiteHelper.getWritableDatabase();
    }

    /**
     * 添加数据
     */
    private void shuju_tianjia()
    {
        SqlLiteHelper sqlLiteHelper=new SqlLiteHelper(context,"BookStore.db",null,2);
        SQLiteDatabase sqLiteDatabase= sqlLiteHelper.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("name","The Da Vinci Code");
        contentValues.put("author","Dan Brown");
        contentValues.put("pages",454);
        contentValues.put("price",16.96);
        sqLiteDatabase.insert("Book",null,contentValues);
        contentValues.clear();

        contentValues.put("name","The Lost Symbol");
        contentValues.put("author","Dan Brown");
        contentValues.put("pages",510);
        contentValues.put("price",19.95);
        sqLiteDatabase.insert("Book",null,contentValues);
    }

    /**
     * 数据更新
     */
    private void shuju_gengxin()
    {
        SqlLiteHelper sqlLiteHelper=new SqlLiteHelper(context,"BookStore.db",null,2);
        SQLiteDatabase sqLiteDatabase= sqlLiteHelper.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("price",10.6);
        sqLiteDatabase.update("Book",contentValues,"name=",new String[]{"The DaVinci Code"});
    }

    /**
     * 数据删除
     */
    private void shuju_shanchu()
    {
        SqlLiteHelper sqlLiteHelper=new SqlLiteHelper(context,"BookStore.db",null,2);
        SQLiteDatabase sqLiteDatabase= sqlLiteHelper.getWritableDatabase();
        sqLiteDatabase.delete("Book","pages>",new String[]{"500"});
    }

    /**
     * 数据查询
     */
    private void shuju_chaxun()
    {
        SqlLiteHelper sqlLiteHelper=new SqlLiteHelper(context,"BookStore.db",null,2);
        SQLiteDatabase sqLiteDatabase= sqlLiteHelper.getWritableDatabase();
        Cursor cursor=sqLiteDatabase.query("Book",new String[]{"name","author","pages"},"id=",new String[]{"1","2"},null,null,null);
        if(cursor.moveToNext())
        {
            do {
                String name=cursor.getString(cursor.getColumnIndex("name"));
                String author=cursor.getString(cursor.getColumnIndex("author"));
                int pages=cursor.getInt(cursor.getColumnIndex("pages"));
            }while (cursor.moveToNext());
        }
    }

    /**
     * 使用SQL操作
     */
    private void shuju_sql()
    {
        SqlLiteHelper sqlLiteHelper=new SqlLiteHelper(context,"BookStore.db",null,2);
        SQLiteDatabase sqLiteDatabase= sqlLiteHelper.getWritableDatabase();
        //添加数据
        sqLiteDatabase.execSQL("insert into Book(name,author,pages,price)values(wenhao,wenhao,wenhao,wenhao)",new String[]{"The Da Vinci Code","Dan Borwn","454","16.96"});
        //更新数据
        sqLiteDatabase.execSQL("update Book set price=wenhao where name=wenhao",new String[]{"10.99","The Da Vinci Code"});
        //删除数据
        sqLiteDatabase.execSQL("delete from Book where pages>wenhao",new String[]{"500"});
        //查询数据
        Cursor cursor= sqLiteDatabase.rawQuery("select * from Book",null);
    }

    /**
     * 使用事务
     */
    private void shiyong_shiwu()
    {
        SqlLiteHelper sqlLiteHelper=new SqlLiteHelper(context,"BookStore.db",null,2);
        SQLiteDatabase sqLiteDatabase= sqlLiteHelper.getWritableDatabase();
        sqLiteDatabase.beginTransaction();//开启事务
        try {
            sqLiteDatabase.delete("Book",null,null);
            if(true)
            {
                //在这里手动抛出一个异常，让事务失败
                throw new NullPointerException();
            }
            ContentValues contentValues=new ContentValues();
            contentValues.put("name","Game of Thrones");
            contentValues.put("author","George Martin");
            contentValues.put("pages",720);
            contentValues.put("Price",20.85);
            sqLiteDatabase.insert("Book",null,contentValues);
            sqLiteDatabase.setTransactionSuccessful();//事务已经执行成功
        }catch (Exception ex)
        {
            ex.printStackTrace();
        }finally {
            sqLiteDatabase.endTransaction();//结束事务
        }
    }
}
