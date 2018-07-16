package cRUD;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.strictmode.SqliteObjectLeakedViolation;

import DB.*;
public class dao {
    private PersonSqliteOpenHelper helper;
    public dao (Context context)
    {
        helper = new PersonSqliteOpenHelper(context);

    }

    public void add(String id ,String password)
    {
        SQLiteDatabase db = helper.getWritableDatabase();
        String sql = "insert into np(id,password) values(?,?)";
        //使用占位符来解决网虫的问题
        db.execSQL(sql , new Object[]{id,password});
        db.close();
    }


    public String find (String name)
    {
        String result = null;
        SQLiteDatabase db = helper.getWritableDatabase();
        String sql = "Select * from np where id = ?";
        Cursor cursor = db.rawQuery(sql , new String[]{name});
        if(cursor.moveToNext())
        {
            result = cursor.getString(1);//return password
            return  result ;
        }
        else
            return result ;
    }
}
